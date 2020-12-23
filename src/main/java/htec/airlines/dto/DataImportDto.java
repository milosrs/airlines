package htec.airlines.dto;

import org.springframework.web.multipart.MultipartFile;

public class DataImportDto {
	private MultipartFile airportFile;
	private MultipartFile routesFile;
	
	public DataImportDto() {
		super();
	}
	
	public DataImportDto(MultipartFile airportFile, MultipartFile routesFile) {
		super();
		this.airportFile = airportFile;
		this.routesFile = routesFile;
	}

	public MultipartFile getAirportFile() {
		return airportFile;
	}

	public void setAirportFile(MultipartFile airportFile) {
		this.airportFile = airportFile;
	}

	public MultipartFile getRoutesFile() {
		return routesFile;
	}

	public void setRoutesFile(MultipartFile routesFile) {
		this.routesFile = routesFile;
	}
}
