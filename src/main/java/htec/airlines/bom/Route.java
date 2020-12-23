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
@Table(name = "Route")
public class Route {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdRoute")
	private Long id;
	
	@Column(name = "IsActive")
	private boolean isActive;
	
	@Column(name = "DateTimeCreatedOn")
	private LocalDateTime dateTimeCreatedOn;
	
	@Column(name = "DateTimeModifiedOn")
	private LocalDateTime dateTimeModifiedOn;
	
	@Column(name = "Airline")
	private String airline;
	
	@Column(name = "IdAirline")
	private Long airlineId;
	
	@Column(name = "SourceAirport")
	private String sourceAirport;
	
	@Column(name = "DestinationAirport")
	private String destinationAirport;
	
	@Column(name = "CodeShare")
	private boolean codeShare;
	
	@Column(name = "Stops")
	private Integer stops;
	
	@Column(name = "Equipment")
	private String equipment;
	
	@Column(name = "Price")
	private Double price;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Airport.class)
	@JoinColumn(name = "RefAirportSource")
	private Airport refSourceAirport;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Airport.class)
	@JoinColumn(name = "RefAirportDestination")
	private Airport refDestinationAirport;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
	@JoinColumn(name = "RefUser")
	private User createdBy;
	
	public Route() {
		super();
	}

	public Route(Long id, boolean isActive, LocalDateTime dateTimeCreatedOn, LocalDateTime dateTimeModifiedOn,
			String airline, Long airlineId, String sourceAirport, String destinationAirport, boolean codeShare,
			Integer stops, String equipment, Double price, Airport refSourceAirport, Airport refDestinationAirport,
			User createdBy) {
		super();
		this.id = id;
		this.isActive = isActive;
		this.dateTimeCreatedOn = dateTimeCreatedOn;
		this.dateTimeModifiedOn = dateTimeModifiedOn;
		this.airline = airline;
		this.airlineId = airlineId;
		this.sourceAirport = sourceAirport;
		this.destinationAirport = destinationAirport;
		this.codeShare = codeShare;
		this.stops = stops;
		this.equipment = equipment;
		this.price = price;
		this.refSourceAirport = refSourceAirport;
		this.refDestinationAirport = refDestinationAirport;
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

	public String getAirline() {
		return airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}

	public Long getAirlineId() {
		return airlineId;
	}

	public void setAirlineId(Long airlineId) {
		this.airlineId = airlineId;
	}

	public String getSourceAirport() {
		return sourceAirport;
	}

	public void setSourceAirport(String sourceAirport) {
		this.sourceAirport = sourceAirport;
	}

	public String getDestinationAirport() {
		return destinationAirport;
	}

	public void setDestinationAirport(String destinationAirport) {
		this.destinationAirport = destinationAirport;
	}

	public boolean isCodeShare() {
		return codeShare;
	}

	public void setCodeShare(boolean codeShare) {
		this.codeShare = codeShare;
	}

	public Integer getStops() {
		return stops;
	}

	public void setStops(Integer stops) {
		this.stops = stops;
	}

	public String getEquipment() {
		return equipment;
	}

	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Airport getRefSourceAirport() {
		return refSourceAirport;
	}

	public void setRefSourceAirport(Airport sourceAirport) {
		this.refSourceAirport = sourceAirport;
	}

	public Airport getRefDestinationAirport() {
		return refDestinationAirport;
	}

	public void setRefDestinationAirport(Airport destinationAirport) {
		this.refDestinationAirport = destinationAirport;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}
}
