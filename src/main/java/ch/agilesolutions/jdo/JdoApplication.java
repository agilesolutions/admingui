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
			profileRepository.save(new Profile("ear", "srp76591lx", "prd"));
			profileRepository.save(new Profile("ear", "srp73684lx", "uat"));
			profileRepository.save(new Profile("ear", "srp15437lx", "sit"));
			profileRepository.save(new Profile("ear", "srp17464lx", "dev"));
			profileRepository.save(new Profile("jbt", "srp75635lx", "prd"));
			profileRepository.save(new Profile("jbt", "srp72673lx", "uat"));
			profileRepository.save(new Profile("jbt", "srp74673lx", "sit"));
			profileRepository.save(new Profile("jbt", "srp17633lx", "dev"));
			
		};
	}	
}
