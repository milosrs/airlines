package htec.airlines.dto;

public class GetCityRequestDto {
	private Long id;
	private String cityName;
	private Integer commentNumber;
	
	
	public GetCityRequestDto() {
		super();
	}

	public GetCityRequestDto(Long id, String cityName, Integer commentNumber) {
		super();
		this.id = id;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
