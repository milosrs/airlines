package htec.airlines.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import htec.airlines.bom.Airport;
import htec.airlines.bom.City;
import htec.airlines.bom.Country;
import htec.airlines.bom.Route;
import htec.airlines.bom.User;
import htec.airlines.dto.DataImportDto;
import htec.airlines.graph.Graph;
import htec.airlines.repository.AirportRepository;
import htec.airlines.repository.CityRepository;
import htec.airlines.repository.CountryRepository;
import htec.airlines.repository.RouteRepository;
import htec.airlines.repository.UserRepository;
import htec.airlines.service.DataImportService;
import htec.airlines.utility.CSVReader;

@Service
public class DataImportServiceImpl implements DataImportService {
	@Autowired
	private Factory fact;
	@Autowired
	private AirportRepository airportRepository;
	@Autowired
	private CountryRepository countryRepository;
	@Autowired
	private CityRepository cityRepository;
	@Autowired
	private UserRepository userRepository;

	public boolean importDataToSystem(DataImportDto dataDto, String username) throws IOException, Exception {
		final Optional<User> user = userRepository.findByUserName(username);
		
		if(dataDto.getAirportFile() != null && dataDto.getRoutesFile() != null && user.isPresent()) {
			List<List<String>> airportTokens = CSVReader.readCSVTokens(dataDto.getAirportFile().getInputStream());
			List<List<String>> routesTokens = CSVReader.readCSVTokens(dataDto.getRoutesFile().getInputStream());
			
			final List<Country> countries = fact.createObjectsFromTokens(airportTokens, user, Country.class);
			final List<City> cities = fact.createObjectsFromTokens(airportTokens, user, City.class);
			final List<Airport> airports = fact.createObjectsFromTokens(airportTokens, user, Airport.class);
			final List<Route> routes = fact.createObjectsFromTokens(routesTokens, user, Route.class);
			
			CompletableFuture<Void> linkAllData = CompletableFuture.runAsync(() -> {
				linkAllData(airports, countries, cities, routes);
				Graph.getInstance().createAdjacencyList(routes);
			});
			
			return !routes.isEmpty() && !airports.isEmpty() && !cities.isEmpty() && !countries.isEmpty();
		}

		return false;
	}
	
	public void linkAllData(List<Airport> airports, List<Country> countries, List<City> cities, List<Route> routes) {
		airports.parallelStream().forEach(airport -> {
			List<Route> newRoutes = routes.parallelStream().filter(r -> r.getRefSourceAirport().getId().equals(airport.getId())).collect(Collectors.toList());
			airport.getRoutes().addAll(newRoutes);
		});
		
		airportRepository.saveAll(airports);
		
		cities.parallelStream().forEach(c -> c.getAirports().addAll(airports.parallelStream().filter(a -> a.getCity().getId().equals(c.getId())).collect(Collectors.toList())));
		cityRepository.saveAll(cities);
		
		countries.parallelStream().forEach(c -> c.getCities().addAll(cities.parallelStream().filter(city -> city.getCountry().getId().equals(c.getId())).collect(Collectors.toList())));
		countryRepository.saveAll(countries);
	}
}
