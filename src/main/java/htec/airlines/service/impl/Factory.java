package htec.airlines.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import htec.airlines.bom.Airport;
import htec.airlines.bom.City;
import htec.airlines.bom.Country;
import htec.airlines.bom.Route;
import htec.airlines.bom.User;
import htec.airlines.builder.AirportBuilder;
import htec.airlines.builder.RouteBuilder;
import htec.airlines.repository.AirportRepository;
import htec.airlines.repository.CityRepository;
import htec.airlines.repository.CountryRepository;
import htec.airlines.repository.RouteRepository;
import htec.airlines.utility.Check;

@Service
public class Factory {
	
	@Autowired
	private CountryRepository countryRepository;
	@Autowired
	private CityRepository cityRepository;
	@Autowired
	private AirportRepository airportRepository;
	@Autowired
	private RouteRepository routeRepository;

	public List createObjectsFromTokens(List<List<String>> tokens, Optional<User> userCreatedBy, Class objectType) {
		if(objectType.equals(Airport.class)) {
			return createAirports(tokens, userCreatedBy);
		} else if(objectType.equals(Route.class)) {
			return createRoutes(tokens, userCreatedBy);
		} else if(objectType.equals(City.class)) {
			return createCities(tokens, userCreatedBy);
		} else if(objectType.equals(Country.class)) {
			return createCountries(tokens, userCreatedBy);
		} else {
			throw new IllegalArgumentException("Provided object type not supported: " + objectType.getTypeName());
		}
	}

	@Transactional
	private List<Country> createCountries(List<List<String>> tokens, Optional<User> userCreatedBy) {
		final List<Country> countries = new ArrayList<>();
		
		for(List<String> tokenList : tokens) {
			final String countryStr = tokenList.get(3);
			final Optional<Country> countryOptional = countries.parallelStream().filter(c -> c.getName().equals(countryStr)).findFirst();
			
			if(!countryOptional.isPresent() && Check.isValueNotEmpty(countryStr)) {
				Country country = new Country();
				
				country.setActive(true);
				country.setCreatedBy(userCreatedBy.isPresent() ? userCreatedBy.get() : null);
				country.setDateTimeCreatedOn(LocalDateTime.now());
				country.setDateTimeModifiedOn(null);
				country.setName(countryStr);
				countries.add(country);
			}
		}
		
		countryRepository.saveAll(countries);
		
		return countries;
	}

	@Transactional
	private List<City> createCities(List<List<String>> tokens, Optional<User> userCreatedBy) {
		final List<City> cities = new ArrayList<>();

		for(List<String> tokenList : tokens) {
			final String countryStr = tokenList.get(3);
			final String cityStr = tokenList.get(2);
			final Optional<City> cityOptional = cities.parallelStream().filter(c -> c.getName().equals(cityStr) && c.getCountry().getName().equals(countryStr)).findFirst();
			
			if(Check.isValueNotEmpty(cityStr) && !cityOptional.isPresent()) {
				City city = new City();
				city.setCountry(Check.isValueNotEmpty(countryStr) ? countryRepository.findByName(countryStr).get() : null);
				city.setActive(true);
				city.setCreatedBy(userCreatedBy.isPresent() ? userCreatedBy.get() : null);
				city.setDateTimeCreatedOn(LocalDateTime.now());
				city.setDateTimeModifiedOn(null);
				city.setDescription(null);
				city.setName(cityStr);
				cities.add(city);
			}
		}
		
		cityRepository.saveAll(cities);
		
		return cities;
	}

	@Transactional
	private List<Airport> createAirports(List<List<String>> tokens, Optional<User> userCreatedBy) {
		final List<Airport> airports = new ArrayList<>();
		
		for(List<String> tokenList : tokens) {
			final String countryStr = tokenList.get(3);
			final String cityStr = tokenList.get(2);
			
			if(Check.isValueNotEmpty(cityStr)) {
				final AirportBuilder airportBuilder = new AirportBuilder();
				final Optional<City> city = cityRepository.findByNameAndCountry(cityStr, countryStr);
				final Optional<Country> country = countryRepository.findByName(countryStr);
				
				airportBuilder
					.setCity(city.isEmpty() ? null : city.get())
					.setCountry(country.isEmpty() ? null : country.get())
					.setId(tokenList.get(0))
					.setName(tokenList.get(1))
					.setIATA(tokenList.get(4))
					.setICAO(tokenList.get(5))
					.setLatitude(tokenList.get(6))
					.setLongitude(tokenList.get(7))
					.setAltitude(tokenList.get(8))
					.setTimezone(tokenList.get(9))
					.setDST(tokenList.get(10))
					.setTzOlson(tokenList.get(11))
					.setType(tokenList.get(12))
					.setSource(tokenList.get(13));
				
				if(userCreatedBy.isPresent()) {
					airportBuilder.setCreatedBy(userCreatedBy.get());
				}
				
				final Airport airport = airportBuilder.build();
				airports.add(airport);
			}
		}
		
		airportRepository.saveAll(airports);
		
		return airports;
	}

	@Transactional
	private List<Route> createRoutes(List<List<String>> tokens, Optional<User> userCreatedBy) {
		final List<Route> routes = new ArrayList<>();
		
		for(List<String> tokenList : tokens) {
			final String sourceAirport = tokenList.get(3);
			final String destinationAirport = tokenList.get(5);
			RouteBuilder routeBuilder = new RouteBuilder();
			Route route = null;
			
			if(Check.isValueNotEmpty(sourceAirport) && Check.isValueNotEmpty(destinationAirport)) {
				final Optional<Airport> src = airportRepository.findById(Long.parseLong(sourceAirport));
				final Optional<Airport> dst = airportRepository.findById(Long.parseLong(destinationAirport));
				
				if(src.isPresent() && dst.isPresent()) {
					routeBuilder.setAirline(tokenList.get(0))
					.setAirlineId(tokenList.get(1))
					.setSourceAirport(tokenList.get(2))
					.setRefSourceAirport(src.get())
					.setDestinationAirport(tokenList.get(4))
					.setRefDestinationAirport(dst.get())
					.setCodeShare(tokenList.get(6))
					.setStops(tokenList.get(7))
					.setEquipment(tokenList.get(8))
					.setPrice(tokenList.get(9));
					
					if(userCreatedBy.isPresent()) {
						routeBuilder.setCreatedBy(userCreatedBy.get());
					}
					
					route = routeBuilder.build();
					src.get().getRoutes().add(route);
					routes.add(route);
				}
			}
		}
		
		routeRepository.saveAll(routes);
		
		return routes;
	}
}
