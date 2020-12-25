package htec.airlines.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import htec.airlines.bom.Airport;
import htec.airlines.bom.City;
import htec.airlines.dto.PathDto;

public interface PathFindService {
	
	Collection<PathDto> findPath(Long sourceCityId, Long destinationCityId) throws Exception;

	Collection<PathDto> findPath(City sourceCity, City destinationCity) throws Exception;
	
	Collection<Airport> AStar(Airport sourceAirport, Airport destination) throws Exception;
}
