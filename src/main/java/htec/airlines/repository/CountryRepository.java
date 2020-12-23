package htec.airlines.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import htec.airlines.bom.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

	Optional<Country> findByName(String name);
}
