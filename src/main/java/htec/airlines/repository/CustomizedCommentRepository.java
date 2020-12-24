package htec.airlines.repository;

import java.util.List;

import htec.airlines.bom.Comment;
import htec.airlines.dto.GetCityRequestDto;

public interface CustomizedCommentRepository {
	
	List<Comment> getMaxNumberOfNewestCommentsForCity(GetCityRequestDto comment);

}
