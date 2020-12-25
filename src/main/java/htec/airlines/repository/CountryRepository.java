package htec.airlines.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import htec.airlines.bom.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

	@Query("SELECT c FROM Country c"
			+ " WHERE c.name = :countryName")
	Optional<Country> findByName(@Param("countryName") String name);
}
