package htec.airlines.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import htec.airlines.bom.Comment;
import htec.airlines.dto.CommentDto;

@Component
public class CommentToDtoConverter implements Converter<Comment, CommentDto> {

	@Override
	public CommentDto convert(Comment source) {
		CommentDto dto = new CommentDto();
		
		//dto.setCreatedBy(source.getCreatedBy());
		dto.setCreatedOn(source.getDateTimeCreatedOn());
		dto.setModifiedOn(source.getDateTimeModifiedOn());
		dto.setDescription(source.getDescription());
		dto.setId(source.getId());
		
		return dto;
	}

}
