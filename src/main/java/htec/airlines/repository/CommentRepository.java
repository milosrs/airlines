package htec.airlines.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import htec.airlines.bom.City;
import htec.airlines.bom.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>, CustomizedCommentRepository {

	@Query("SELECT c FROM Comment c"
			+ " WHERE c.refCity = :city")
	List<Comment> findAllByRefCity(@Param("city") City city);
}
