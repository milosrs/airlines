package htec.airlines.builder;

import java.time.LocalDateTime;

import htec.airlines.bom.Airport;
import htec.airlines.bom.Route;
import htec.airlines.bom.User;

public class RouteBuilder {
	private Route route;

	public RouteBuilder() {
		route = new Route();
	}

	public RouteBuilder setId(Long id) {
		route.setId(id);
		return this;
	}
	
	public RouteBuilder setId(String id) {
		try {
			route.setId(Long.parseLong(id));
		} catch(NumberFormatException ex) {
			route.setId(null);
		}
		
		return this;
	}

	public RouteBuilder setActive(boolean isActive) {
		route.setActive(isActive);
		return this;
	}

	public RouteBuilder setDateTimeCreatedOn(LocalDateTime dateTimeCreatedOn) {
		route.setDateTimeCreatedOn(dateTimeCreatedOn);
		return this;
	}

	public RouteBuilder setDateTimeModifiedOn(LocalDateTime dateTimeModifiedOn) {
		route.setDateTimeModifiedOn(dateTimeModifiedOn);
		return this;
	}

	public RouteBuilder setCreatedBy(User createdBy) {
		route.setCreatedBy(createdBy);
		return this;
	}

	public RouteBuilder setAirline(String airline) {
		route.setAirline(airline);
		return this;
	}

	public RouteBuilder setAirlineId(Long airlineId) {
		route.setAirlineId(airlineId);
		return this;
	}
	
	public RouteBuilder setAirlineId(String airlineId) {
		try {
			route.setAirlineId(Long.parseLong(airlineId));
		} catch(NumberFormatException ex) {
			route.setAirlineId(null);
		}

		return this;
	}

	public RouteBuilder setSourceAirport(String sourceAirport) {
		route.setSourceAirport(sourceAirport);
		return this;
	}
	
	public RouteBuilder setDestinationAirport(String destinationAirport) {
		route.setDestinationAirport(destinationAirport);
		return this;
	}
	
	public RouteBuilder setRefSourceAirport(Airport sourceAirport) {
		route.setRefSourceAirport(sourceAirport);
		return this;
	}
	
	public RouteBuilder setRefDestinationAirport(Airport destinationAirport) {
		route.setRefDestinationAirport(destinationAirport);
		return this;
	}

	public RouteBuilder setCodeShare(boolean codeShare) {
		route.setCodeShare(codeShare);
		return this;
	}
	
	public RouteBuilder setCodeShare(String codeShare) {
		route.setCodeShare(!codeShare.equals(null) && codeShare.equals("Y"));
		return this;
	}

	public RouteBuilder setStops(Integer stops) {
		route.setStops(stops);
		return this;
	}
	
	public RouteBuilder setStops(String stops) {
		try {
			route.setStops(Integer.parseInt(stops));
		} catch(NumberFormatException ex) {
			route.setStops(null);
		}
		
		return this;
	}

	public RouteBuilder setEquipment(String equipment) {
		route.setEquipment(equipment);
		return this;
	}

	public RouteBuilder setPrice(Double price) {
		route.setPrice(price);
		return this;
	}
	
	public RouteBuilder setPrice(String price) {
		route.setPrice(Double.parseDouble(price));
		return this;	
	}
	
	public Route build() {
		route.setActive(true);
		route.setDateTimeCreatedOn(LocalDateTime.now());
		route.setDateTimeModifiedOn(null);
		
		return route;
	}
}
