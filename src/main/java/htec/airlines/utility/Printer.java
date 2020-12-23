package htec.airlines.utility;

import java.util.Collection;

import htec.airlines.dto.PathDto;

public class Printer {

	public static void printPath(Collection<PathDto> path) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("Your path looks like this: ");
		
		for(PathDto dto : path ) {
			sb.append(dto.toString());
			sb.append(" -> ");
		}
		
		sb.append("END");
	}
}
