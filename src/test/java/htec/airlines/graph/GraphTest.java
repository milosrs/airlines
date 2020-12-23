package htec.airlines.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.data.util.Pair;

import htec.airlines.bom.Airport;
import htec.airlines.bom.City;
import htec.airlines.bom.Country;
import htec.airlines.bom.Route;
import htec.airlines.graph.Graph;

public class GraphTest extends GraphRelatedTestConfigurer {

	@Test
	public void compare_actual_and_expected_graph_representation() {
		String expectedRep = Graph.getInstance().getAdjacencyListRepresentation(expected);
		String actualRep = Graph.getInstance().getAdjacencyListRepresentation();
		
		System.out.println(expectedRep.trim() + " \n\nVS\n\n" + actualRep.trim());
		
		assertEquals(expectedRep.trim(), actualRep.trim());
	}
}
