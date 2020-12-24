package htec.airlines.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import htec.airlines.bom.City;
import htec.airlines.bom.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>, CustomizedCommentRepository {

	List<Comment> findAllByRefCity(City city);
}
