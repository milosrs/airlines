package htec.airlines;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import htec.airlines.service.UserService;

@SpringBootApplication
@EnableJpaRepositories
public class AirlinesApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(AirlinesApplication.class, args);
	}
}
