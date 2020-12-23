package htec.airlines.dto;

public class CreateUpdateCityDto {
	private String name;
	private Long country;
	private String description;
	
	public CreateUpdateCityDto() {
		super();
	}

	public CreateUpdateCityDto(String name, Long country, String description) {
		super();
		this.name = name;
		this.country = country;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getCountry() {
		return country;
	}

	public void setCountry(Long country) {
		this.country = country;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
