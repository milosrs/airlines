package htec.airlines.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import htec.airlines.bom.City;
import htec.airlines.bom.Country;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
	@Query("SELECT c FROM City c"
			+ " WHERE c.name = :cityName"
			+ " AND c.country.name = :countryName")
	Optional<City> findByNameAndCountry(@Param(value = "cityName") String cityName, @Param(value = "countryName") String countryName);
	
	@Query("SELECT c FROM City c"
			+ " WHERE c.country = :country")
	Optional<City> findByCountry(@Param(value = "country") Country country);
	
	@Query("SELECT c FROM City c"
			+ " WHERE c.name = :cityName")
	Collection<City> findAllByName(@Param("cityName")String cityName);
}
