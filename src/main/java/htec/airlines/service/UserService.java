package htec.airlines.service;

import htec.airlines.dto.UserDto;

public interface UserService {
	String login(UserDto userDto) throws Exception;
	
	String register(UserDto userDto);
	
	String registerAdmin(UserDto userDto);
}
