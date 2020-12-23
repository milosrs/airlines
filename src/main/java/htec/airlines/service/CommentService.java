package htec.airlines.service;

import htec.airlines.dto.CommentDto;

public interface CommentService {

	CommentDto createComment(CommentDto commentDto);
	
	CommentDto updateComment(CommentDto commentDto);

	Boolean deleteComment(Long commentId);
}
