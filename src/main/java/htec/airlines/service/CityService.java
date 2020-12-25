package htec.airlines.service;

import java.util.Collection;

import htec.airlines.dto.CityDto;
import htec.airlines.dto.CreateUpdateCityDto;
import htec.airlines.dto.GetCityRequestDto;

public interface CityService {

	Collection<CityDto> getAllCities(GetCityRequestDto getCityRequest);
	
	Boolean createNewCity(CreateUpdateCityDto city, String username);
}
