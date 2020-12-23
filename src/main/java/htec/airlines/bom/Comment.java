package htec.airlines.bom;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Comment")
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdComment")
	private Long id;
	
	@Column(name = "IsActive")
	private boolean isActive;
	
	@Column(name = "DateTimeCreatedOn")
	private LocalDateTime dateTimeCreatedOn;
	
	@Column(name = "DateTimeModifiedOn")
	private LocalDateTime dateTimeModifiedOn;
	
	@Column(name = "Description")
	private String description;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
	@JoinColumn(name = "RefUser")
	private User createdBy;
	
	public Comment() {
		super();
	}

	public Comment(Long id, boolean isActive, LocalDateTime dateTimeCreatedOn, LocalDateTime dateTimeModifiedOn,
			String description, User createdBy) {
		super();
		this.id = id;
		this.isActive = isActive;
		this.dateTimeCreatedOn = dateTimeCreatedOn;
		this.dateTimeModifiedOn = dateTimeModifiedOn;
		this.description = description;
		this.createdBy = createdBy;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public LocalDateTime getDateTimeCreatedOn() {
		return dateTimeCreatedOn;
	}

	public void setDateTimeCreatedOn(LocalDateTime dateTimeCreatedOn) {
		this.dateTimeCreatedOn = dateTimeCreatedOn;
	}

	public LocalDateTime getDateTimeModifiedOn() {
		return dateTimeModifiedOn;
	}

	public void setDateTimeModifiedOn(LocalDateTime dateTimeModifiedOn) {
		this.dateTimeModifiedOn = dateTimeModifiedOn;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}
}
