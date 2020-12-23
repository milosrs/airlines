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
@Table(name = "Country")
public class Country {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdCountry")
	private Long id;
	
	@Column(name="Name")
	private String name;
	
	@Column(name = "IsActive")
	private boolean isActive;
	
	@Column(name = "DateTimeCreatedOn")
	private LocalDateTime dateTimeCreatedOn;
	
	@Column(name = "DateTimeModifiedOn")
	private LocalDateTime dateTimeModifiedOn;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
	@JoinColumn(name = "RefUser")
	private User createdBy;
	
	@OneToMany(fetch = FetchType.LAZY, targetEntity = City.class)
	@JoinColumn(name = "RefCity")
	private Collection<City> cities;
	
	public Country() {
		super();
		cities = new ArrayList<City>();
	}

	public Country(Long id, String name, boolean isActive, LocalDateTime dateTimeCreatedOn,
			LocalDateTime dateTimeModifiedOn, User createdBy, Collection<City> cities) {
		super();
		this.id = id;
		this.name = name;
		this.isActive = isActive;
		this.dateTimeCreatedOn = dateTimeCreatedOn;
		this.dateTimeModifiedOn = dateTimeModifiedOn;
		this.createdBy = createdBy;
		this.cities = cities;
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

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public Collection<City> getCities() {
		return cities;
	}

	public void setCities(Collection<City> cities) {
		this.cities = cities;
	}

	
}
