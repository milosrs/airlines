package htec.airlines.dto;

import java.util.Collection;

public class FindPathResponseDto {
	private Collection<PathDto> paths;
	private Double totalPrice;
	
	public FindPathResponseDto() {
		super();
	}
	public FindPathResponseDto(Collection<PathDto> cheapestPath) {
		super();
		this.paths = cheapestPath;

		if(cheapestPath != null) {
			totalPrice = cheapestPath.parallelStream().mapToDouble(p -> p.getPrice()).sum();
		}
	}

	public FindPathResponseDto(Collection<PathDto> paths, Double totalPrice) {
		super();
		this.paths = paths;
		this.totalPrice = totalPrice;
	}
	
	public Collection<PathDto> getPaths() {
		return paths;
	}
	public void setPaths(Collection<PathDto> paths) {
		this.paths = paths;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
}
