package htec.airlines.dto;

import java.util.Collection;

public class CityDto {
	private Long id;
	private String name;
	private Long country;
	private String description;
	private Collection<CommentDto> comments;
	
	public CityDto() {
		super();
	}

	public CityDto(Long id, String name, Long country, String description, Collection<CommentDto> comments) {
		super();
		this.id = id;
		this.name = name;
		this.country = country;
		this.description = description;
		this.comments = comments;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getCountry() {
		return country;
	}
	public void setCountry(Long country) {
		this.country = country;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Collection<CommentDto> getComments() {
		return comments;
	}
	public void setComments(Collection<CommentDto> comments) {
		this.comments = comments;
	}
}
