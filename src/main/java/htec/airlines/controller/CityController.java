package htec.airlines.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import htec.airlines.dto.CityDto;
import htec.airlines.dto.GetCityRequestDto;
import htec.airlines.service.CityService;

@RestController
@RequestMapping("/api/city")
public class CityController {
	@Autowired
	private CityService cityService;

	@GetMapping("/getCity")
	public ResponseEntity<Collection<CityDto>> getCity(GetCityRequestDto requestDto) {
		try {
			return ResponseEntity.ok(cityService.getAllCities(requestDto));
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
