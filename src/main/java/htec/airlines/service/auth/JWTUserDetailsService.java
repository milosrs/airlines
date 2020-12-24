package htec.airlines.service.auth;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import htec.airlines.repository.UserRepository;

@Service
public class JWTUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<htec.airlines.bom.User> user = userRepository.findByUserName(username);
		
		if (user.isPresent()) {
			return new User(user.get().getUserName(), user.get().getPassword(), List.of(new SimpleGrantedAuthority(user.get().getType().toString())));
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}

}