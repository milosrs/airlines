package htec.airlines.service;

import java.util.Collection;

import htec.airlines.bom.Airport;
import htec.airlines.bom.City;
import htec.airlines.dto.PathDto;

public interface PathFindService {

	Collection<PathDto> findPath(City sourceCity, City destinationCity) throws Exception;
	
	Collection<Airport> findPossibleSources(City sourceCity, City destinationCity) throws Exception;
	
	Collection<Airport> AStar(Airport sourceAirport, Airport destination) throws Exception;
}
