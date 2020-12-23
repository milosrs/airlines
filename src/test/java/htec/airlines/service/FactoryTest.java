package htec.airlines.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import htec.airlines.bom.Airport;
import htec.airlines.bom.City;
import htec.airlines.bom.Country;
import htec.airlines.bom.Route;
import htec.airlines.repository.AirportRepository;
import htec.airlines.repository.CityRepository;
import htec.airlines.repository.CountryRepository;
import htec.airlines.repository.RouteRepository;
import htec.airlines.service.impl.Factory;
import htec.airlines.utility.CSVReader;
import htec.airlines.utility.Check;

@ExtendWith(value =  MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class FactoryTest {
	private static String AIRPORTS_FILENAME = "/resources/airports.txt";
	private static String ROUTES_FILENAME = "/resources/routes.txt";
	
	@InjectMocks
	private Factory factory;
	@Mock
	private CountryRepository countryRepository;
	@Mock
	private CityRepository cityRepository;
	@Mock
	private AirportRepository airportRepository;
	@Mock
	private RouteRepository routeRepository;
	
	private AutoCloseable closeable;

    @BeforeEach
    public void openMocks() {
    	setupMocks();
    	closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void releaseMocks() throws Exception {
        closeable.close();
    }

	@Test
	public void createObjectsFromTokens_airports_shouldNotThrowException() throws Exception {
		assertDoesNotThrow(() -> {
			final InputStream resource = getClass().getResourceAsStream(AIRPORTS_FILENAME);
			final List<List<String>> tokens = CSVReader.readCSVTokens(resource);
			
			factory.createObjectsFromTokens(tokens, Optional.empty(), Airport.class);
		});
	}
	
	@Test
	public void createObjectsFromTokens_routes_shouldNotThrowException() throws Exception {
		assertDoesNotThrow(() -> {
			final InputStream resource = getClass().getResourceAsStream(ROUTES_FILENAME);
			final List<List<String>> tokens = CSVReader.readCSVTokens(resource);
			
			factory.createObjectsFromTokens(tokens, Optional.empty(), Route.class);
		});
	}
	
	@Test
	public void createObjectsFromTokens_airports_shouldCreate_sameNumberOfObjects_asLinesWithCity() throws Exception {
		final InputStream resource = getClass().getResourceAsStream(AIRPORTS_FILENAME);
		final List<List<String>> tokens = CSVReader.readCSVTokens(resource);
		long linesWithCities = tokens.stream()
				.filter(l -> Check.isValueNotEmpty(l.get(2)))
				.count();
		
		assertNotEquals(linesWithCities, tokens.size());
		
		List<Object> objects = factory.createObjectsFromTokens(tokens, Optional.empty(), Airport.class);
		
		assertEquals(objects.size(), linesWithCities);
	}
	
	private void setupMocks() {
		Country testCountry = new Country(1L, "Rixondija", true, LocalDateTime.now(), null, null, new ArrayList<City>());
        City testCity = new City(1L, true, LocalDateTime.now(), null, "Test", "desc", testCountry, null, new ArrayList<Airport>());
        Airport testAirport = new Airport();
        
        testAirport.setId(1L);
        testAirport.setCity(testCity);
        testCity.setCountry(testCountry);
        testCountry.getCities().add(testCity);
        testAirport.setCity(testCity);
        testAirport.setCountry(testCountry);
        
        Mockito.when(routeRepository.save(Mockito.any())).thenReturn(null);
        Mockito.when(routeRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(new Route()));
        Mockito.when(cityRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(testCity));
        Mockito.when(countryRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(testCountry));
        Mockito.when(airportRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(testAirport));
	}
}
