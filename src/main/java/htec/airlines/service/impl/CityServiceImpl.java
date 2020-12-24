package htec.airlines.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import htec.airlines.bom.City;
import htec.airlines.bom.Comment;
import htec.airlines.converter.CityToDtoConverter;
import htec.airlines.converter.CommentToDtoConverter;
import htec.airlines.dto.CityDto;
import htec.airlines.dto.CommentDto;
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
	@Autowired
	private CommentToDtoConverter commentToDtoConverter;
	@Autowired
	private CityToDtoConverter cityToDtoConverter;
	
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
		
		for(City c : cities) {
			List<Comment> comments = new ArrayList<>();
			List<CommentDto> commentDtos = new ArrayList<>();
			
			if(getCityRequest.getCommentNumber() > 0 && getCityRequest.getCommentNumber() != null) {
				comments = commentRepository.getMaxNumberOfNewestCommentsForCity(getCityRequest);
			} else {
				comments = commentRepository.findAllByRefCity(c);
			}
			
			commentDtos.add(comments.parallelStream().map(com -> commentToDtoConverter.convert(com)).findFirst().get());
			
			final CityDto dto = cityToDtoConverter.convert(c);
			
			dto.setComments(commentDtos);
			citiesDto.add(dto);
		}
		
		return citiesDto;
	}
}
