package htec.airlines.graph;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import htec.airlines.bom.Airport;
import htec.airlines.bom.City;
import htec.airlines.bom.Route;
import htec.airlines.dto.PathDto;
import htec.airlines.repository.RouteRepository;
import htec.airlines.service.impl.PathFindServiceImpl;
import htec.airlines.utility.Printer;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@TestMethodOrder(OrderAnnotation.class)
public class PathFindServiceTest extends GraphRelatedTestConfigurer {
	@InjectMocks
	private PathFindServiceImpl pathFind;
	@Mock
	private RouteRepository routeRepository;
	
	@Test
	@Order(1)
	public void pathFind_findPossibleSources_shouldReturnNonEmptyCollection_forConnectedNodes() {
		City bg = cities.parallelStream().filter(c -> c.getName().equals("Beograd")).findFirst().get();
		City kraljevo = cities.parallelStream().filter(c -> c.getName().equals("Kraljevo")).findFirst().get();
		City vrsac = cities.parallelStream().filter(c -> c.getName().equals("Vrsac")).findFirst().get();
		
		//Connected: Batajnica -> Morava
		var sources1 = pathFind.findPossibleSources(bg, kraljevo);
		//Connected: Nikola Tesla -> AeroVrsac | Batajnica -> AeroVrsac
		var sources2 = pathFind.findPossibleSources(bg, vrsac);
		
		assertNotEquals(sources1.size(), 0);
		assertNotEquals(sources2.size(), 0);
		assertNotEquals(0, bg.getAirports().size());
	}
	
	@Test
	@Order(2)
	public void pathFind_findPossibleSources_shouldReturnEmptyCollection_forNonConnectedNodes() {
		City bg = cities.parallelStream().filter(c -> c.getName().equals("Beograd")).findFirst().get();
		City mm = cities.parallelStream().filter(c -> c.getName().equals("Macvanska Mitrovica center of Universe")).findFirst().get();
		
		var sources1 = pathFind.findPossibleSources(bg, mm);
		
		assertEquals(sources1.size(), 0);
		assertNotEquals(0, bg.getAirports().size());
	}
	
	@Test
	@Order(3)
	public void pathFind_findPath_shouldNotThrowException() {
		City bg = cities.parallelStream().filter(c -> c.getName().equals("Beograd")).findFirst().get();
		City vrsac = cities.parallelStream().filter(c -> c.getName().equals("Vrsac")).findFirst().get();
		Airport nikolaTesla = findAirportByName("Nikola Tesla");
		Airport vrsacAir = findAirportByName("Aerodrom Vrsac");
		Airport batajnica = findAirportByName("Batajnica");
		
		Mockito.when(routeRepository.findByRefSourceAirportAndRefDestinationAirport(nikolaTesla, vrsacAir)).thenReturn(Optional.of(findRoute(nikolaTesla, vrsacAir)));
		Mockito.when(routeRepository.findByRefSourceAirportAndRefDestinationAirport(nikolaTesla, batajnica)).thenReturn(Optional.of(findRoute(nikolaTesla, batajnica)));
		Mockito.when(routeRepository.findByRefSourceAirportAndRefDestinationAirport(batajnica, vrsacAir)).thenReturn(Optional.of(findRoute(batajnica, vrsacAir)));
		
		assertDoesNotThrow(() -> {
			pathFind.findPath(bg, vrsac);
		});
		
		assertNotEquals(0, bg.getAirports().size());
	}
	
	@Test
	@Order(4)
	public void pathFind_findPath_shouldNotReturnNullOrEmpty() throws Exception {
		City bg = cities.parallelStream().filter(c -> c.getName().equals("Beograd")).findFirst().get();
		City vrsac = cities.parallelStream().filter(c -> c.getName().equals("Vrsac")).findFirst().get();
		Airport nikolaTesla = findAirportByName("Nikola Tesla");
		Airport vrsacAir = findAirportByName("Aerodrom Vrsac");
		Airport batajnica = findAirportByName("Batajnica");
		
		Mockito.when(routeRepository.findByRefSourceAirportAndRefDestinationAirport(nikolaTesla, vrsacAir)).thenReturn(Optional.of(findRoute(nikolaTesla, vrsacAir)));
		Mockito.when(routeRepository.findByRefSourceAirportAndRefDestinationAirport(nikolaTesla, batajnica)).thenReturn(Optional.of(findRoute(nikolaTesla, batajnica)));
		Mockito.when(routeRepository.findByRefSourceAirportAndRefDestinationAirport(batajnica, vrsacAir)).thenReturn(Optional.of(findRoute(batajnica, vrsacAir)));
		
		Collection<PathDto> path = pathFind.findPath(bg, vrsac);
		Printer.printPath(path);
		
		assertNotNull(path);
		assertNotEquals(0, path.size());
	}
	
	@Test
	@Order(5)
	public void pathFind_findPath_shouldReturnEmpty() throws Exception {
		City bg = cities.parallelStream().filter(c -> c.getName().equals("Beograd")).findFirst().get();
		City vrsac = cities.parallelStream().filter(c -> c.getName().equals("Vrsac")).findFirst().get();
		City mm = cities.parallelStream().filter(c -> c.getName().equals("Macvanska Mitrovica center of Universe")).findFirst().get();
		Airport nikolaTesla = findAirportByName("Nikola Tesla");
		Airport vrsacAir = findAirportByName("Aerodrom Vrsac");
		Airport batajnica = findAirportByName("Batajnica");
		
		Mockito.when(routeRepository.findByRefSourceAirportAndRefDestinationAirport(nikolaTesla, vrsacAir)).thenReturn(Optional.of(findRoute(nikolaTesla, vrsacAir)));
		Mockito.when(routeRepository.findByRefSourceAirportAndRefDestinationAirport(nikolaTesla, batajnica)).thenReturn(Optional.of(findRoute(nikolaTesla, batajnica)));
		Mockito.when(routeRepository.findByRefSourceAirportAndRefDestinationAirport(batajnica, vrsacAir)).thenReturn(Optional.of(findRoute(batajnica, vrsacAir)));
		
		Collection<PathDto> path = pathFind.findPath(bg, mm);
		Printer.printPath(path);
		
		assertEquals(0, path.size());
	}
	
	private Airport findAirportByName(String name) {
		return airports.parallelStream().filter(a -> a.getName().equalsIgnoreCase(name)).findFirst().get();
	}
	
	private Route findRoute(Airport src, Airport dst) {
		return routes.parallelStream().filter(r -> r.getRefSourceAirport().equals(src) && r.getRefDestinationAirport().equals(dst)).findFirst().get();
	}
}