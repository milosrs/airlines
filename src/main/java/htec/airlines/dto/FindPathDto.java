package htec.airlines.dto;

public class FindPathDto {
	private Long srcCity;
	private Long dstCity;
	
	
	public FindPathDto(Long srcCity, Long dstCity) {
		super();
		this.srcCity = srcCity;
		this.dstCity = dstCity;
	}


	public FindPathDto() {
		super();
	}


	public Long getSrcCity() {
		return srcCity;
	}


	public void setSrcCity(Long srcCity) {
		this.srcCity = srcCity;
	}


	public Long getDstCity() {
		return dstCity;
	}


	public void setDstCity(Long dstCity) {
		this.dstCity = dstCity;
	}
}
