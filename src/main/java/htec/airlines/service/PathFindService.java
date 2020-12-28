package htec.airlines.service;

import java.util.Collection;

import htec.airlines.bom.Airport;
import htec.airlines.bom.City;
import htec.airlines.dto.FindPathResponseDto;

public interface PathFindService {
	
	FindPathResponseDto findPath(Long sourceCityId, Long destinationCityId) throws Exception;

	FindPathResponseDto findPath(City sourceCity, City destinationCity) throws Exception;
	
	Collection<Airport> AStar(Airport sourceAirport, Airport destination) throws Exception;
}
