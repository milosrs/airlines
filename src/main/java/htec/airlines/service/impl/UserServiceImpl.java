package htec.airlines.service.impl;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import htec.airlines.bom.User;
import htec.airlines.dto.UserDto;
import htec.airlines.enums.UserType;
import htec.airlines.repository.UserRepository;
import htec.airlines.service.UserService;
import htec.airlines.service.auth.JWTUserDetailsService;
import htec.airlines.utility.JWTTokenUtil;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JWTUserDetailsService jwtUserDetailsService;
	@Autowired
	private JWTTokenUtil jwtTokenUtil;
	
	
	@Override
	public String login(UserDto userDto) throws Exception {
		authenticate(userDto.getUsername(), userDto.getPassword());

		final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(userDto.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return "Bearer " + token;
	}

	@Override
	public String register(UserDto userDto) {
		final User user = new User();
		final String passwordSalt = BCrypt.gensalt(15);
		final String hashedPassword = BCrypt.hashpw(userDto.getPassword(), passwordSalt);
		final Optional<User> registeredUser = userRepository.findByUserName(userDto.getUsername());
		
		if(registeredUser.isEmpty()) {
			user.setActive(true);
			user.setDateTimeCreatedOn(LocalDateTime.now());
			user.setFirstName(userDto.getFirstName());
			user.setLastName(userDto.getLastName());
			user.setType(UserType.REGULAR);
			user.setUserName(userDto.getUsername());
			user.setSalt(passwordSalt);
			user.setPassword(hashedPassword);
			
			userRepository.save(user);
			
			return "User registered successfully! Welcome: " + user.getUserName();
		}
		
		return "User with this username already exists.";
	}

	@Override
	public String registerAdmin(UserDto userDto) {
		final User user = new User();
		final String passwordSalt = BCrypt.gensalt(15);
		final String hashedPassword = BCrypt.hashpw(userDto.getPassword(), passwordSalt);
		final Optional<User> registeredUser = userRepository.findByUserName(userDto.getUsername());
		
		if(registeredUser.isEmpty()) {
			user.setActive(true);
			user.setDateTimeCreatedOn(LocalDateTime.now());
			user.setFirstName(userDto.getFirstName());
			user.setLastName(userDto.getLastName());
			user.setType(UserType.ADMINISTRATOR);
			user.setUserName(userDto.getUsername());
			user.setSalt(passwordSalt);
			user.setPassword(hashedPassword);
			
			userRepository.save(user);
			
			return "User registered successfully! Welcome: " + user.getUserName();
		}
		
		return "User with this username already exists.";
	}
	
	private void authenticate(String username, String password) throws Exception {
		Objects.requireNonNull(username);
		Objects.requireNonNull(password);
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
