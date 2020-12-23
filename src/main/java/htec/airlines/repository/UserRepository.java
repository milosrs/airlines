package htec.airlines.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import htec.airlines.bom.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
