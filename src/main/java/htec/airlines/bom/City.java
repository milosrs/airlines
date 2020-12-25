package htec.airlines.bom;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "City")
public class City {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdCity")
	private Long id;
	
	@Column(name = "IsActive")
	private boolean isActive;
	
	@Column(name = "DateTimeCreatedOn")
	private LocalDateTime dateTimeCreatedOn;
	
	@Column(name = "DateTimeModifiedOn")
	private LocalDateTime dateTimeModifiedOn;
	
	@Column(name = "Name")
	private String name;
	
	@Column(name = "Description")
	private String description;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Country.class)
	@JoinColumn(name = "RefCountry")
	private Country country;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
	@JoinColumn(name = "RefUser")
	private User createdBy;
	
	@OneToMany(fetch = FetchType.LAZY, targetEntity = Airport.class)
	@JoinColumn(name = "RefAirport")
	private Collection<Airport> airports;
	
	@OneToMany(fetch = FetchType.LAZY, targetEntity = Comment.class)
	@JoinColumn(name = "RefComment")
	private Collection<Comment> comments;
	
	public City() {
		super();
		airports = new ArrayList<>();
	}

	public City(Long id, boolean isActive, LocalDateTime dateTimeCreatedOn, LocalDateTime dateTimeModifiedOn,
			String name, String description, Country country, User createdBy, Collection<Airport> airports) {
		super();
		this.id = id;
		this.isActive = isActive;
		this.dateTimeCreatedOn = dateTimeCreatedOn;
		this.dateTimeModifiedOn = dateTimeModifiedOn;
		this.name = name;
		this.description = description;
		this.country = country;
		this.createdBy = createdBy;
		this.airports = airports;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public Collection<Airport> getAirports() {
		return airports;
	}

	public void setAirports(Collection<Airport> airports) {
		this.airports = airports;
	}
	
	
}
