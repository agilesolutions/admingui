package ch.agilesolutions.jdo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import ch.agilesolutions.jdo.domain.Profile;
import ch.agilesolutions.jdo.domain.ProfileRepository;

@SpringBootApplication
public class JdoApplication {
	@Autowired	
	private ProfileRepository profileRepository;

	
	public static void main(String[] args) {
		SpringApplication.run(JdoApplication.class, args);
	}
	
	@Bean
	CommandLineRunner runner() {
		return args -> {
			
			
			profileRepository.save(new Profile("crm", "srp99999lx", "prd"));
			profileRepository.save(new Profile("crm", "srp74943lx", "uat"));
			profileRepository.save(new Profile("crm", "srp10577lx", "sit"));
			profileRepository.save(new Profile("crm", "srp34766lx", "dev"));
			
		};
	}	
}
