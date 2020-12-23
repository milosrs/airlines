package htec.airlines.dto;

public class PathDto {
	private String sourceCity;
	private String destinationCity;
	private Double price;
	
	public PathDto() {
		super();
	}
	public PathDto(String sourceCity, String destinationCity, Double price) {
		super();
		this.sourceCity = sourceCity;
		this.destinationCity = destinationCity;
		this.price = price;
	}
	public String getSourceCity() {
		return sourceCity;
	}
	public void setSourceCity(String sourceCity) {
		this.sourceCity = sourceCity;
	}
	public String getDestinationCity() {
		return destinationCity;
	}
	public void setDestinationCity(String destinationCity) {
		this.destinationCity = destinationCity;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "[" + sourceCity + ", " + destinationCity + "] = " + price;  
	}
}
