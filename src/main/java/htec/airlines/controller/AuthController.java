package htec.airlines.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import htec.airlines.dto.UserDto;
import htec.airlines.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody UserDto userDto) {
		return ResponseEntity.ok(userService.register(userDto));
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody UserDto userDto) throws Exception {
		return ResponseEntity.ok(userService.login(userDto));
	}
}
