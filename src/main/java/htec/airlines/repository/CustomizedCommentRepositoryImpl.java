package htec.airlines.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import htec.airlines.bom.Comment;
import htec.airlines.dto.GetCityRequestDto;

@Repository
public class CustomizedCommentRepositoryImpl implements CustomizedCommentRepository {

	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Comment> getMaxNumberOfNewestCommentsForCity(GetCityRequestDto comment) {
		StringBuilder builder = new StringBuilder();
		
		builder.append("SELECT c FROM Comment c "
				+ "WHERE c.city.id = :cityId "
				+ "ORDER BY c.dateTimeCreatedOn, c.dateTimeModifiedOn");
		
		TypedQuery<Comment> query = entityManager.createNamedQuery(builder.toString(), Comment.class);
		
		query.setParameter(":cityId", comment.getId());
		query.setFirstResult(0);
		query.setMaxResults(comment.getCommentNumber());
		
		return query.getResultList();
	}

}
