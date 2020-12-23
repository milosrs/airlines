package htec.airlines.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	public SimpleAuthorityMapper grantedAuthority() {
        SimpleAuthorityMapper mapper = new SimpleAuthorityMapper();
        mapper.setConvertToUpperCase(true);
        return mapper;
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.cors()
			.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and().authorizeRequests().antMatchers("/**").permitAll()			//Uncomment for requests to be allowed!
			//.and().authorizeRequests().antMatchers("/api/admin/**").hasRole(UserType.ADMIN.name())
			//.and().authorizeRequests().antMatchers("/api/manager/**").hasAnyRole(UserType.MANAGER.name(), UserType.ADMIN.name())
			//.and().authorizeRequests().antMatchers("/api/worker/**").hasAnyRole(UserType.WORKER.name(), UserType.MANAGER.name(), UserType.ADMIN.name())
			.anyRequest().authenticated();
		
		http.headers().frameOptions().disable();
	}
}
