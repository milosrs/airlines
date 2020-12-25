package htec.airlines.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import htec.airlines.bom.City;
import htec.airlines.bom.Comment;
import htec.airlines.converter.CommentToDtoConverter;
import htec.airlines.dto.CommentDto;
import htec.airlines.repository.CityRepository;
import htec.airlines.repository.CommentRepository;
import htec.airlines.repository.UserRepository;
import htec.airlines.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	private CommentToDtoConverter commentToDtoConverter;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CityRepository cityRepository;

	@Override
	public CommentDto createComment(CommentDto commentDto, String username) {
		Optional<City> cityOptional = cityRepository.findById(commentDto.getCityId());
		
		if(cityOptional.isPresent()) {
			Comment comment = new Comment();
			
			comment.setActive(true);
			comment.setCreatedBy(userRepository.findByUserName(username).get());
			comment.setDateTimeCreatedOn(LocalDateTime.now());
			comment.setDateTimeModifiedOn(null);
			comment.setDescription(commentDto.getDescription());
			comment.setCity(cityOptional.get());
			
			commentRepository.save(comment);
			return commentToDtoConverter.convert(comment);
		}
		
		return null;
	}

	@Override
	public CommentDto updateComment(CommentDto commentDto, String username) {
		Optional<Comment> commentOptional = commentRepository.findById(commentDto.getId());
		
		if(commentOptional.isPresent()) {
			final Comment comment = commentOptional.get();
			
			if(comment.getCreatedBy().getUserName().equals(username)) {
				comment.setDateTimeModifiedOn(LocalDateTime.now());
				comment.setDescription(commentDto.getDescription());
				comment.setId(commentDto.getId());
				
				commentRepository.save(comment);
				return commentToDtoConverter.convert(comment);
			}
			
			return null;
		}

		return null;
	}

	@Override
	public Boolean deleteComment(Long commentId, String username) {
		Optional<Comment> commentOptional = commentRepository.findById(commentId);
		
		if(commentOptional.isPresent()) {
			final Comment comment = commentOptional.get();
			
			if(comment.getCreatedBy().getUserName().equals(username)) {
				commentOptional.get().setActive(false);
				commentRepository.save(comment);
			} else {
				return false;
			}
		}
		
		return commentOptional.isPresent();
	}
	
}
