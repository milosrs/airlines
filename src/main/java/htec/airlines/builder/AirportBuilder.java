package htec.airlines.builder;

import java.time.LocalDateTime;

import htec.airlines.bom.Airport;
import htec.airlines.bom.City;
import htec.airlines.bom.Country;
import htec.airlines.bom.User;

public class AirportBuilder {
	private Airport airport;
	
	public AirportBuilder() {
		airport = new Airport();
	}
	
	public AirportBuilder setId(Long id) {
		airport.setId(id);
		return this;
	}

	public AirportBuilder setId(String id) {
		try {
			airport.setId(Long.parseLong(id));
		} catch(NumberFormatException ex) {
			airport.setId(null);
		}
		
		return this;
	}

	public AirportBuilder setName(String name) {
		airport.setName(name);
		return this;
	}

	public AirportBuilder setCity(City city) {
		airport.setCity(city);
		return this;
	}

	public AirportBuilder setCountry(Country country) {
		airport.setCountry(country);
		return this;
	}

	public AirportBuilder setIATA(String iATA) {
		airport.setIATA(iATA);
		return this;
	}

	public AirportBuilder setICAO(String ICAO) {
		airport.setICAO(ICAO);
		return this;
	}

	public AirportBuilder setLatitude(double latitude) {
		airport.setLatitude(latitude);
		return this;
	}
	
	public AirportBuilder setLatitude(String latitude) {
		try {
			airport.setLatitude(Double.parseDouble(latitude));
		} catch(NumberFormatException ex) {
			airport.setLatitude(null);
		}
		
		return this;
	}

	public AirportBuilder setLongitude(double longitude) {
		airport.setLongitude(longitude);
		return this;
	}
	
	public AirportBuilder setLongitude(String longitude) {
		try {
			airport.setLongitude(Double.parseDouble(longitude));
		} catch(NumberFormatException ex) {
			airport.setLongitude(null);
		}
		
		return this;
	}

	public AirportBuilder setAltitude(double altitude) {
		airport.setAltitude(altitude);
		return this;
	}
	
	public AirportBuilder setAltitude(String altitude) {
		try {
			airport.setAltitude(Double.parseDouble(altitude));
		} catch(NumberFormatException ex) {
			airport.setAltitude(null);
		}
		
		return this;
	}

	public AirportBuilder setDST(char DST) {
		airport.setDST(DST);
		return this;
	}
	
	public AirportBuilder setDST(String DST) {
		if(DST.length() == 1) {
			airport.setDST(DST.charAt(0));
		} else {
			airport.setDST(null);
		}
		
		return this;
	}

	public AirportBuilder setTimezone(String timezone) {
		try {
			airport.setTimezone(Double.parseDouble(timezone));
		} catch(NumberFormatException ex) {
			airport.setTimezone(null);
		}
		
		
		return this;
	}

	public AirportBuilder setType(String type) {
		airport.setType(type);
		return this;
	}

	public AirportBuilder setSource(String source) {
		airport.setSource(source);
		return this;
	}
	
	public AirportBuilder setTzOlson(String olson) {
		airport.setTzOlson(olson);
		return this;
	}
	
	public AirportBuilder setCreatedBy(User createdBy) {
		airport.setCreatedBy(createdBy);
		return this;
	}
	
	public Airport build() {
		airport.setActive(true);
		airport.setDateTimeCreatedOn(LocalDateTime.now());
		airport.setDateTimeModifiedOn(null);
		
		return airport;
	}
}
