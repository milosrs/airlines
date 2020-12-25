package htec.airlines.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import htec.airlines.bom.Airport;
import htec.airlines.bom.Route;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {

	List<Route> findByRefSourceAirport(Airport refSourceAirport);
	
	List<Route> findByRefDestinationAirport(Airport refDestinationAirport);
	
	List<Route> findByRefSourceAirportAndRefDestinationAirport(Airport refSourceAirport, Airport refDestinationAirport);
}
