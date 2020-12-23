package htec.airlines.bom;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Airport")
public class Airport {
	@Id
	@Column(name = "IdAirport")
	private Long id;
	
	@Column(name = "IsActive")
	private boolean isActive;
	
	@Column(name = "DateTimeCreatedOn")
	private LocalDateTime dateTimeCreatedOn;
	
	@Column(name = "DateTimeModifiedOn")
	private LocalDateTime dateTimeModifiedOn;
	
	@Column(name = "Name")
	private String name;
	
	@Column(name = "IATA")
	private String IATA;
	
	@Column(name = "ICAO")
	private String ICAO;
	
	@Column(name = "Latitude")
	private Double latitude;
	
	@Column(name = "Longitude")
	private Double longitude;
	
	@Column(name = "Altitude")
	private Double altitude;
	
	@Column(name = "DST")
	private Character DST;
	
	@Column(name = "Timezone")
	private Double timezone;
	
	@Column(name = "TimezoneOlson")
	private String tzOlson;
	
	@Column(name = "Type")
	private String type;
	
	@Column(name = "Source")
	private String source;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
	@JoinColumn(name = "RefUser")
	private User createdBy;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = City.class)
	@JoinColumn(name = "RefCity")
	private City city;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Country.class)
	@JoinColumn(name = "RefCountry")
	private Country country;
	
	@OneToMany(fetch = FetchType.LAZY, targetEntity = Route.class)
	@JoinColumn(name = "RefRoute")
	private Collection<Route> routes;
	
	
	public Airport() {
		super();
		routes = new ArrayList<Route>();
	}


	public Airport(Long id, boolean isActive, LocalDateTime dateTimeCreatedOn, LocalDateTime dateTimeModifiedOn,
			String name, String iATA, String iCAO, Double latitude, Double longitude, Double altitude, Character dST,
			Double timezone, String tzOlson, String type, String source, User createdBy, City city, Country country, Collection<Route> routes) {
		super();
		this.id = id;
		this.isActive = isActive;
		this.dateTimeCreatedOn = dateTimeCreatedOn;
		this.dateTimeModifiedOn = dateTimeModifiedOn;
		this.name = name;
		IATA = iATA;
		ICAO = iCAO;
		this.latitude = latitude;
		this.longitude = longitude;
		this.altitude = altitude;
		DST = dST;
		this.timezone = timezone;
		this.tzOlson = tzOlson;
		this.type = type;
		this.source = source;
		this.createdBy = createdBy;
		this.city = city;
		this.country = country;
		this.routes = routes;
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


	public String getIATA() {
		return IATA;
	}


	public void setIATA(String iATA) {
		IATA = iATA;
	}


	public String getICAO() {
		return ICAO;
	}


	public void setICAO(String iCAO) {
		ICAO = iCAO;
	}


	public Double getLatitude() {
		return latitude;
	}


	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}


	public Double getLongitude() {
		return longitude;
	}


	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}


	public Double getAltitude() {
		return altitude;
	}


	public void setAltitude(Double altitude) {
		this.altitude = altitude;
	}


	public Character getDST() {
		return DST;
	}


	public void setDST(Character dST) {
		DST = dST;
	}


	public Double getTimezone() {
		return timezone;
	}


	public void setTimezone(Double timezone) {
		this.timezone = timezone;
	}


	public String getTzOlson() {
		return tzOlson;
	}


	public void setTzOlson(String tzOlson) {
		this.tzOlson = tzOlson;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getSource() {
		return source;
	}


	public void setSource(String source) {
		this.source = source;
	}


	public User getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}


	public City getCity() {
		return city;
	}


	public void setCity(City city) {
		this.city = city;
	}


	public Country getCountry() {
		return country;
	}


	public void setCountry(Country country) {
		this.country = country;
	}


	public Collection<Route> getRoutes() {
		return routes;
	}


	public void setRoutes(Collection<Route> routes) {
		this.routes = routes;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
}
