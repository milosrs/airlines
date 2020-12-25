package htec.airlines.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import htec.airlines.bom.Airport;
import htec.airlines.bom.City;
import htec.airlines.dto.PathDto;
import htec.airlines.graph.Graph;
import htec.airlines.repository.CityRepository;
import htec.airlines.service.PathFindService;

@Service
public class PathFindServiceImpl implements PathFindService {
	private static Double EARTH_R = 6372.8;
	
	@Autowired
	private CityRepository cityRepository;
	
	@Override
	public Collection<PathDto> findPath(Long sourceCityId, Long destinationCityId) throws Exception {
		final City srcCity = cityRepository.findById(sourceCityId).get();
		final City dstCity = cityRepository.findById(destinationCityId).get();
		
		return findPath(srcCity, dstCity);
	}
	
	@Override
	public Collection<PathDto> findPath(City sourceCity, City destinationCity) throws Exception {
		Collection<Airport> sourceCityAirports = sourceCity.getAirports();
		Collection<Airport> destinationCityAirports = destinationCity.getAirports();
		Collection<Collection<Airport>> possiblePaths = new ArrayList<>();
		
		for(Airport possibleSrc : sourceCityAirports) {
			for(Airport possibleDst : destinationCityAirports) {
				possiblePaths.add((List<Airport>) AStar(possibleSrc, possibleDst));
			}
		}
		
		return findCheapestOfPossiblePaths(possiblePaths);
	}

	private Collection<PathDto> findCheapestOfPossiblePaths(Collection<Collection<Airport>> possiblePaths) {
		List<PathDto> pathDtos = new ArrayList<>();
		Double minPrice = Double.POSITIVE_INFINITY;
		
		for(Collection<Airport> path : possiblePaths) {
			if(path.isEmpty()) {
				continue;
			}
			List<PathDto> pathRepresentation = new ArrayList<PathDto>();
			Airport source = ((List<Airport>)path).get(0);
			Double pathPrice = 0D;
			
			for(int i = 1; i < path.size(); ++i) {
				final List<Pair<Airport, Double>> adjacentToSource = Graph.getInstance().getListOfPairsForAirport(source.getId());
				final Airport dst = ((List<Airport>)path).get(i);
				Pair<Airport, Double> nextStop = adjacentToSource.stream().filter(p -> p.getFirst().getId().equals(dst.getId())).findFirst().get();
				pathRepresentation.add(new PathDto(source.getName(), dst.getName(), nextStop.getSecond()));
				source = dst;
				pathPrice += nextStop.getSecond();
			}
			
			if(pathPrice < minPrice) {
				pathDtos = pathRepresentation;
				minPrice = pathPrice;
			}
			
			pathPrice = 0D;
		}
		
		return pathDtos;
	}

	@Override
	public Collection<Airport> AStar(final Airport sourceAirport, final Airport destination) throws Exception {
		List<Airport> openSet = new ArrayList<>(List.of(sourceAirport));
		List<Airport> closedSet = new ArrayList<>();
		Map<Airport, Double> gScoreMap = new HashMap<>(Map.of(sourceAirport, 0D));
		Map<Airport, Airport> parents = new HashMap<>(Map.of(sourceAirport, sourceAirport));
		
		while(!openSet.isEmpty()) {
			Airport currentAirport = null;
			
			for(int i = 0; i < openSet.size(); ++i) {
				Airport v = openSet.get(i);
				
				if(currentAirport == null) {
					currentAirport = v;
				} else {
					Double fScoreV = calculateFScore(v, destination, gScoreMap);
					Double fScoreN = calculateFScore(currentAirport, destination, gScoreMap);
					
					if(fScoreV < fScoreN) {
						currentAirport = v;
					}
				}
				
				if(currentAirport == null) {
					return null;
				}
				
				if(currentAirport.getId().equals(destination.getId())) {
					List<Airport> reconstructedPath = new ArrayList<Airport>();
					
					while(!parents.get(currentAirport).equals(currentAirport)) {
						reconstructedPath.add(currentAirport);
						currentAirport = parents.get(currentAirport);
					}
					
					reconstructedPath.add(sourceAirport);
					Collections.reverse(reconstructedPath);
					return reconstructedPath;
				}
				
				for(Pair<Airport, Double> neibghour : Graph.getInstance().getListOfPairsForAirport(currentAirport.getId())) {
					Airport neibghourAirport = neibghour.getFirst();
					Double weight = neibghour.getSecond();
					Double newWeight = gScoreMap.get(currentAirport) + weight;
					
					if(!openSet.contains(neibghourAirport) && !closedSet.contains(neibghourAirport)) {
						openSet.add(neibghourAirport);
						parents.put(neibghourAirport, currentAirport);
						gScoreMap.put(neibghourAirport, newWeight);
					} else {
						if(gScoreMap.get(neibghourAirport) > newWeight) {
							gScoreMap.replace(neibghourAirport, newWeight);
							parents.put(neibghourAirport, currentAirport);
							
							if(closedSet.contains(neibghourAirport)) {
								closedSet.remove(neibghourAirport);
								openSet.add(neibghourAirport);
							}
						}
					}
				}
			}
			
			openSet.remove(currentAirport);
			closedSet.add(currentAirport);
		}
		
		return new ArrayList<>();
	}
	
	/**
	 * Evaluation function: f(n) = g(n) + h(n)
	 * @param n				current airport
	 * @param gScoreMap		scoreMap for airports
	 * @return				f(n)
	 * @throws Exception 
	 */
	private Double calculateFScore(final Airport n, final Airport goal, final Map<Airport, Double> gScoreMap) throws Exception {
		return gScoreMap.get(n) + heuristic(n, goal);
	}
	
	private Double heuristic(final Airport n, final Airport goal) throws Exception {
		Double haversine = computeHaversine(n, goal);
		
		return haversine;
	}
	
	/**
	 * https://en.wikipedia.org/wiki/Haversine_formula
	 * @param n			Source
	 * @param goal		Destination
	 * @return			Haversine distance
	 */
	private Double computeHaversine(final Airport n, final Airport goal) {
		Double haversine = Double.POSITIVE_INFINITY;
		
		if(goal.getLatitude() != null && n.getLatitude() != null && goal.getLongitude() != null && n.getLongitude() != null) {
			final Double dLat = Math.toRadians(goal.getLatitude() - n.getLatitude());
			final Double dLon = Math.toRadians(goal.getLongitude() - n.getLongitude());
			final Double nLat = Math.toRadians(n.getLatitude());
			final Double gLat = Math.toRadians(goal.getLatitude());
			final Double h = Math.pow(Math.sin(dLat/2), 2) + Math.pow(Math.sin(dLon / 2), 2) * Math.cos(nLat) * Math.cos(gLat);
			final Double c = 2 * Math.asin(Math.sqrt(h));
			
			haversine = EARTH_R * c;
		}
		
		return haversine;
	}
}
