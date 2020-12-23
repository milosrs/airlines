package htec.airlines.dto;

public class GetCityRequestDto {
	private String cityName;
	private Integer commentNumber;
	
	
	public GetCityRequestDto() {
		super();
	}

	public GetCityRequestDto(String cityName, Integer commentNumber) {
		super();
		this.cityName = cityName;
		this.commentNumber = commentNumber;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Integer getCommentNumber() {
		return commentNumber;
	}

	public void setCommentNumber(Integer commentNumber) {
		this.commentNumber = commentNumber;
	}
}
