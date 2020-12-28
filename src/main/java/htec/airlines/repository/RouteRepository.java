package htec.airlines.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import htec.airlines.bom.Airport;
import htec.airlines.bom.Route;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {

	List<Route> findByRefSourceAirport(Airport refSourceAirport);
	
	List<Route> findByRefDestinationAirport(Airport refDestinationAirport);
	
	List<Route> findByRefSourceAirportAndRefDestinationAirport(Airport refSourceAirport, Airport refDestinationAirport);
	
	@Query("SELECT COUNT(r) FROM Route r"
			+ " WHERE r.refSourceAirport.id = :idAirport")
	Integer findCountOfSourceRoutesForAirport(Long idAirport);
	
	@Query("SELECT COUNT(r) FROM Route r"
			+ " WHERE r.refDestinationAirport.id = :idAirport")
	Integer findCountOfDestinationRoutesForAirport(Long idAirport);
	
	@Query("SELECT COUNT(r) FROM Route r"
			+ " WHERE r.refSourceAirport.id = :idAirport OR r.refDestinationAirport.id = :idAirport")
	Integer findTotalCountOfRoutesForAirport(@Param("idAirport") Long idAirport);
}