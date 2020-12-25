package htec.airlines.service;

import htec.airlines.dto.CommentDto;

public interface CommentService {

	CommentDto createComment(CommentDto commentDto, String username);
	
	CommentDto updateComment(CommentDto commentDto, String username);

	Boolean deleteComment(Long commentId, String username);
}
