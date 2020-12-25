package htec.airlines.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import htec.airlines.bom.City;
import htec.airlines.bom.Comment;
import htec.airlines.bom.Country;
import htec.airlines.bom.User;
import htec.airlines.converter.CityToDtoConverter;
import htec.airlines.converter.CommentToDtoConverter;
import htec.airlines.dto.CityDto;
import htec.airlines.dto.CommentDto;
import htec.airlines.dto.CreateUpdateCityDto;
import htec.airlines.dto.GetCityRequestDto;
import htec.airlines.repository.CityRepository;
import htec.airlines.repository.CommentRepository;
import htec.airlines.repository.CountryRepository;
import htec.airlines.repository.UserRepository;
import htec.airlines.service.CityService;

@Service
public class CityServiceImpl implements CityService {

	@Autowired
	private CityRepository cityRepository;
	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	private CountryRepository countryRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CommentToDtoConverter commentToDtoConverter;
	@Autowired
	private CityToDtoConverter cityToDtoConverter;
	
	
	@Override
	public Boolean createNewCity(CreateUpdateCityDto city, String username) {
		try {
			City c = new City();
			Optional<Country> country = countryRepository.findById(city.getCountry());
			Optional<User> u = userRepository.findByUserName(username);
			
			if(country.isPresent() && u.isPresent()) {
				c.setActive(true);
				c.setCountry(country.get());
				c.setName(city.getName());
				c.setCreatedBy(u.get());
				c.setDescription(city.getDescription());
				c.setDateTimeCreatedOn(LocalDateTime.now());
				cityRepository.save(c);
				
				return true;
			} else {
				return false;
			}
		} catch(Exception e) {
			e.printStackTrace();
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
			
			if(getCityRequest.getCommentNumber() != null) {
				if(getCityRequest.getCommentNumber() > 0) {
					comments = commentRepository.getMaxNumberOfNewestCommentsForCity(getCityRequest);	
				}
			} else {
				comments = commentRepository.findAllByRefCity(c);
			}
			
			if(!comments.isEmpty() ) {
				commentDtos.addAll(comments.parallelStream().map(com -> commentToDtoConverter.convert(com)).collect(Collectors.toList()));	
			}
			
			final CityDto dto = cityToDtoConverter.convert(c);
			
			dto.setComments(commentDtos);
			citiesDto.add(dto);
		}
		
		return citiesDto;
	}
}
