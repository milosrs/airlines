package htec.airlines.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import htec.airlines.bom.Airport;
import htec.airlines.bom.City;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {

	@Query("SELECT a FROM Airport a"
			+ " WHERE a.city = :city") 
	List<Airport> findAllByCity(@Param("city") City city);
}
