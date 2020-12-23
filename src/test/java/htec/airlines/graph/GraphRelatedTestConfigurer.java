package htec.airlines.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.util.Pair;

import htec.airlines.bom.Airport;
import htec.airlines.bom.City;
import htec.airlines.bom.Country;
import htec.airlines.bom.Route;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public abstract class GraphRelatedTestConfigurer {
	
	protected static List<City> cities;
	protected static Country country;
	protected static List<Airport> airports;
	protected static List<Route> routes;
	protected static Map<Airport, List<Pair<Airport,Double>>> expected;
	protected static Map<Airport, List<Pair<Airport,Double>>> actual;
	
	@BeforeAll
	public static void prepareTestData() {
		country  = new Country(1L, "Srbija", true, null, null, null, new ArrayList<>());
		
		City beograd = new City(1L, true, null, null, "Beograd", null, country, null, new ArrayList<>());
		City kraljevo = new City(2L, true, null, null, "Kraljevo", null, country, null, new ArrayList<>());
		City vrsac = new City(3L, true, null, null, "Vrsac", null, country, null, new ArrayList<>());
		City macvanskaMitrovica = new City(4L, true, null, null, "Macvanska Mitrovica center of Universe", null, country, null, new ArrayList<>());
		
		Airport nikolaTesla = new Airport(1L, true, null, null, "Nikola Tesla", "1ca", "a23f", null, null, null, null, null, null, null, null, null, beograd, country, new ArrayList<>());
		Airport batajnica = new Airport(2L, true, null, null, "Batajnica", "2ca", "b23f", null, null, null, null, null, null, null, null, null, beograd, country, new ArrayList<>());
		Airport morava = new Airport(3L, true, null, null, "Morava", "3ca", "c23f", null, null, null, null, null, null, null, null, null, kraljevo, country, new ArrayList<>());
		Airport aeroVrsac = new Airport(4L, true, null, null, "Aerodrom Vrsac", "4ca", "d23f", null, null, null, null, null, null, null, null, null, vrsac, country, new ArrayList<>());
		
		Route ntb = new Route(1L, true, null, null, null, null, nikolaTesla.getName(), batajnica.getName(), false, 1, null, 200.00, nikolaTesla, batajnica, null);
		Route ntae = new Route(2L, true, null, null, null, null, nikolaTesla.getName(), aeroVrsac.getName(), false, 1, null, 500.00, nikolaTesla, aeroVrsac, null);
		Route bm = new Route(3L, true, null, null, null, null, batajnica.getName(), morava.getName(), false, 1, null, 300.00, batajnica, morava, null);
		Route bv = new Route(4L, true, null, null, null, null, batajnica.getName(), aeroVrsac.getName(), false, 1, null, 600.00, batajnica, aeroVrsac, null);
		Route vm = new Route(5L, true, null, null, null, null, aeroVrsac.getName(), morava.getName(), false, 1, null, 50.00, aeroVrsac, morava, null);
		Route mnt = new Route(5L, true, null, null, null, null, morava.getName(), nikolaTesla.getName(), false, 1, null, 100.00, morava, nikolaTesla, null);
		
		setLatitudeLongitude(nikolaTesla, 72.5641897496, -106.9179993);
		setLatitudeLongitude(batajnica, 47.50939941,-94.93370056);
		setLatitudeLongitude(morava, -58.105899810800004, 10D);
		setLatitudeLongitude(aeroVrsac, 40.16109848022461, 94.80919647216797);
		
		ntb.setStops(0);
		ntae.setStops(2);
		bm.setStops(5);
		bv.setStops(1);
		vm.setStops(0);
		mnt.setStops(9);
		
		nikolaTesla.getRoutes().addAll(List.of(ntae, ntb));
		batajnica.getRoutes().addAll(List.of(bm, bv));
		morava.getRoutes().addAll(List.of(mnt));
		aeroVrsac.getRoutes().addAll(List.of(vm));
		
		beograd.getAirports().add(nikolaTesla);
		beograd.getAirports().add(batajnica);
		kraljevo.getAirports().add(morava);
		vrsac.getAirports().add(aeroVrsac);
		
		cities = List.of(beograd, kraljevo, vrsac, macvanskaMitrovica);
		airports = List.of(nikolaTesla, batajnica, morava, aeroVrsac);
		routes = List.of(ntb, ntae, bm, bv, vm, mnt);
		country.getCities().addAll(cities);
		
		expected = new HashMap<>();
		expected.put(nikolaTesla, List.of(Pair.of(batajnica, 200.00), Pair.of(aeroVrsac, 500.00)));
		expected.put(batajnica, List.of(Pair.of(morava, 300.00), Pair.of(aeroVrsac, 600.00)));
		expected.put(morava, List.of(Pair.of(nikolaTesla, 100.00)));
		expected.put(aeroVrsac, List.of(Pair.of(morava, 50.00)));
		
		actual = Graph.getInstance().createAdjacencyList(routes);
	}
	
	private static void setLatitudeLongitude(Airport a, double lat, double longi) {
		a.setLatitude(lat);
		a.setLongitude(longi);
	}
}
