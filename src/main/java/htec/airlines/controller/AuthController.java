package htec.airlines.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import htec.airlines.dto.UserDto;

@RestController
@RequestMapping("/api/login")
public class AuthController {

	@PostMapping("/register")
	public ResponseEntity<?> register(UserDto userDto) {
		
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(UserDto userDto) {
		
	}
}
