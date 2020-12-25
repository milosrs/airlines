package htec.airlines.dto;

import java.time.LocalDateTime;

public class CommentDto {
	private Long id;
	private Long createdBy;
	private Long cityId;
	private LocalDateTime createdOn;
	private LocalDateTime modifiedOn;
	private String description;
	
	public CommentDto(Long id, Long createdBy, Long cityId, LocalDateTime createdOn, LocalDateTime modifiedOn, String description) {
		super();
		this.id = id;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
		this.modifiedOn = modifiedOn;
		this.cityId = cityId;
		this.description = description;
	}

	public CommentDto() {
		super();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

	public LocalDateTime getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(LocalDateTime modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}
}
