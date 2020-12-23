package htec.airlines.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import htec.airlines.bom.City;
import htec.airlines.dto.CityDto;
import htec.airlines.dto.CreateUpdateCityDto;
import htec.airlines.dto.GetCityRequestDto;
import htec.airlines.repository.CityRepository;
import htec.airlines.repository.CommentRepository;
import htec.airlines.repository.CountryRepository;
import htec.airlines.service.CityService;

public class CityServiceImpl implements CityService {

	@Autowired
	private CityRepository cityRepository;
	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	private CountryRepository countryRepository;
	
	@Override
	public Boolean createNewCity(CreateUpdateCityDto city) {
		try {
			City c = new City();
			
			c.setActive(true);
			c.setCountry(countryRepository.getOne(city.getCountry()));
			c.setName(city.getName());
			c.setDescription(city.getDescription());
			
			cityRepository.save(c);
			
			return true;
		} catch(Exception e) {
			return false;
		}
	}

	@Override
	public Collection<CityDto> getAllCities(GetCityRequestDto getCityRequest) {
		List<CityDto> citiesDto = new ArrayList<>();
		List<City> cities = new ArrayList<>();
		
		if(StringUtils.hasText(getCityRequest.getCityName())) {
			cities = (List<City>) cityRepository.findAllByName(getCityRequest.getCityName());
		} else {
			cities = (List<City>) cityRepository.findAll();
		}
		
		return citiesDto;
	}
}
