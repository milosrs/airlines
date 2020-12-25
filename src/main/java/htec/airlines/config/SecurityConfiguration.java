package htec.airlines.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import htec.airlines.dto.UserDto;
import htec.airlines.enums.UserType;
import htec.airlines.service.UserService;
import htec.airlines.service.auth.JWTUserDetailsService;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private JWTUserDetailsService jwtUserDetailsService;
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthEntryPoint;
	@Autowired
	private JWTFilter filter;
	@Autowired
	private UserService userService;


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.cors()
			.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and().authorizeRequests()
			.antMatchers("/api/auth/**").permitAll()
			.antMatchers("/h2-console/**").permitAll()
			.antMatchers("/api/admin/**").hasAnyRole(UserType.ADMINISTRATOR.getType())
			.antMatchers("/api/city/**").hasAnyRole(UserType.ADMINISTRATOR.getType(), UserType.REGULAR.getType())
			.antMatchers("/api/comment/**").hasAnyRole(UserType.ADMINISTRATOR.getType(), UserType.REGULAR.getType())
			.antMatchers("/api/pathfind/**").hasAnyRole(UserType.ADMINISTRATOR.getType(), UserType.REGULAR.getType())
			.anyRequest().authenticated()
			.and().exceptionHandling().authenticationEntryPoint(jwtAuthEntryPoint)
			.and().addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
	    web.ignoring().antMatchers("/api/auth/**", "/h2-console/**");
	}
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider());
    }
	
	@Bean
	public AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authProvider() {
	    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	    authProvider.setUserDetailsService(jwtUserDetailsService);
	    authProvider.setPasswordEncoder(passwordEncoder());
	    return authProvider;
	}
	
	@Bean
	public SimpleAuthorityMapper grantedAuthority() {
        SimpleAuthorityMapper mapper = new SimpleAuthorityMapper();
        mapper.setConvertToUpperCase(true);
        return mapper;
    }
	
	@EventListener(ApplicationReadyEvent.class)
	public void createAdmin() {
		userService.registerAdmin(new UserDto("admin", "admin", "admin", "admin"));
	}
}
