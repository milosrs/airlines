package htec.airlines.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.util.Pair;

import htec.airlines.bom.Airport;
import htec.airlines.bom.City;
import htec.airlines.bom.Route;

public class Graph {
	private Map<Airport, List<Pair<Airport, Double>>> adjacencyList;
	
	private static Graph instance = new Graph();
	
	private Graph() {
		adjacencyList = new HashMap<Airport, List<Pair<Airport,Double>>>();
	}
	
	public static Graph getInstance() {
		return instance;
	}
	
	public Map<Airport, List<Pair<Airport,Double>>> createAdjacencyList(List<Route> routes) {
		for(Route route : routes) {
			final Double price = route.getPrice();
			final Airport source = route.getRefSourceAirport();
			final Airport dst = route.getRefDestinationAirport();
			List<Pair<Airport,Double>> pointer = adjacencyList.get(source);
			
			if(pointer == null) {
				pointer = new ArrayList<>();
			}
			
			pointer.add(Pair.of(dst, price));
			adjacencyList.put(source, pointer);
		}
		
		return adjacencyList;
	}
	
	public Map<Airport, List<Pair<Airport, Double>>> getAdjacencyList() {
		return adjacencyList;
	}
	
	public String getAdjacencyListRepresentation() {
		StringBuilder builder = new StringBuilder();
		
		if(adjacencyList != null && !adjacencyList.isEmpty()) {
			for(Airport source : adjacencyList.keySet()) {
				builder.append(source.getName() + " -> ");
				
				for(Pair<Airport, Double> dst : adjacencyList.get(source)) {
					builder.append("[" + dst.getFirst().getName() + "|" + dst.getSecond() + "] -> ");
				}
				
				builder.append("\n");
			}
		}
		
		
		return builder.toString();
	}
	
	public String getAdjacencyListRepresentation(Map<Airport, List<Pair<Airport, Double>>> adjacencyList) {
		StringBuilder builder = new StringBuilder();
		
		if(adjacencyList != null && !adjacencyList.isEmpty()) {
			for(Airport source : adjacencyList.keySet()) {
				builder.append(source.getName() + " -> ");
				
				for(Pair<Airport, Double> dst : adjacencyList.get(source)) {
					builder.append("[" + dst.getFirst().getName() + "|" + dst.getSecond() + "] -> ");
				}
				
				builder.append("\n");
			}
		}
		
		
		return builder.toString();
	}
	
	public void printAdjacencyList() {
		System.out.println(getAdjacencyListRepresentation());
	}
	
	public void printAdjacencyList(Map<Airport, List<Pair<Airport, Double>>> adjacencyList) {
		System.out.println(getAdjacencyListRepresentation());
	}
	
	public String getRouteString(Airport source, List<Pair<Airport, Double>> nodes) {
		StringBuilder builder = new StringBuilder();
		
		builder.append(source.getName() + " -> ");
		
		for(Pair<Airport, Double> dst : adjacencyList.get(source)) {
			builder.append("[" + dst.getFirst().getName() + "|" + dst.getSecond() + "] -> ");
		}
		
		return builder.toString();
	}
	
	public void printRouteString(Airport source, List<Pair<Airport, Double>> nodes) {
		System.out.println(getRouteString(source, nodes));
	}
}
