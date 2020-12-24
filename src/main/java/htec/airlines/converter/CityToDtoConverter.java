package htec.airlines.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import htec.airlines.bom.City;
import htec.airlines.dto.CityDto;

@Component
public class CityToDtoConverter implements Converter<City, CityDto> {

	@Override
	public CityDto convert(City source) {
		CityDto cityDto = new CityDto();
		
		cityDto.setCountry(source.getCountry().getId());
		cityDto.setDescription(source.getDescription());
		cityDto.setName(source.getName());
		cityDto.setId(source.getId());
		
		return cityDto;
	}

}
