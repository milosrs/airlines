package htec.airlines.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import htec.airlines.bom.Comment;
import htec.airlines.converter.CityToDtoConverter;
import htec.airlines.converter.CommentToDtoConverter;
import htec.airlines.dto.CommentDto;
import htec.airlines.repository.CommentRepository;
import htec.airlines.service.CommentService;

public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	private CommentToDtoConverter commentToDtoConverter;

	@Override
	public CommentDto createComment(CommentDto commentDto) {
		Comment comment = new Comment();
		
		comment.setActive(true);
		//comment.setCreatedBy(commentDto.getCreatedBy());
		comment.setDateTimeCreatedOn(LocalDateTime.now());
		comment.setDateTimeModifiedOn(null);
		comment.setDescription(commentDto.getDescription());
		
		commentRepository.save(comment);
		return commentToDtoConverter.convert(comment);
	}

	@Override
	public CommentDto updateComment(CommentDto commentDto) {
		Comment comment = new Comment();
		
		comment.setActive(true);
		//comment.setCreatedBy(commentDto.getCreatedBy());
		comment.setDateTimeCreatedOn(LocalDateTime.now());
		comment.setDateTimeModifiedOn(null);
		comment.setDescription(commentDto.getDescription());
		comment.setId(commentDto.getId());
		
		commentRepository.save(comment);
		
		return commentToDtoConverter.convert(comment);
	}

	@Override
	public Boolean deleteComment(Long commentId) {
		Optional<Comment> comment = commentRepository.findById(commentId);
		
		if(comment.isPresent()) {
			comment.get().setActive(false);
		}
		
		return comment.isPresent();
	}
	
}
