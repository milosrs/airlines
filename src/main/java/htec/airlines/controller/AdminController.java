package htec.airlines.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import htec.airlines.dto.CreateUpdateCityDto;
import htec.airlines.dto.DataImportDto;
import htec.airlines.graph.Graph;
import htec.airlines.service.CityService;
import htec.airlines.service.DataImportService;
import htec.airlines.utility.JWTTokenUtil;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
	@Autowired
	private DataImportService dataImportService;
	@Autowired
	private CityService cityService;
	@Autowired
	private JWTTokenUtil tokenUtil;

	@PostMapping(value = "/import",
			consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<String> importDataFromFiles(@RequestParam("airportFile") MultipartFile airportFile, @RequestParam("routesFile") MultipartFile routesFile, HttpServletRequest request) {
		String username = tokenUtil.getUsernameFromToken(request.getHeader(HttpHeaders.AUTHORIZATION).replace("Bearer ", ""));
		
		try {
			DataImportDto dataDto = new DataImportDto(airportFile, routesFile);
			boolean success = dataImportService.importDataToSystem(dataDto, username);
			return ResponseEntity.ok(Boolean.toString(success));	
		} catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
		}
	}
	
	@GetMapping(value = "/getAdjacencyList")
	public ResponseEntity<String> getAdjacencyList() {
		try {
			return ResponseEntity.ok(Graph.getInstance().getAdjacencyListRepresentation());
		} catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
		}
	}
	
	@PostMapping("/createCity")
	public ResponseEntity<String> createCity(@RequestBody CreateUpdateCityDto city, HttpServletRequest request) {
		String username = tokenUtil.getUsernameFromToken(request.getHeader(HttpHeaders.AUTHORIZATION).replace("Bearer ", ""));
		boolean createSuccess = cityService.createNewCity(city, username);
		
		
		if(createSuccess) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
