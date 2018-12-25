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
			
			
			profileRepository.save(new Profile("crm", "crmapp", "srp99999lx", "prd", "CRM-1"));
			profileRepository.save(new Profile("crm", "crmapp", "srp74943lx", "uat", "CRM-1"));
			profileRepository.save(new Profile("crm", "crmapp", "srp10577lx", "sit", "CRM-1"));
			profileRepository.save(new Profile("crm", "crmapp", "srp34766lx", "dev", "CRM-1"));
			profileRepository.save(new Profile("ear", "earapp", "srp76591lx", "prd", "EAR-1"));
			profileRepository.save(new Profile("ear", "earapp", "srp73684lx", "uat", "EAR-1"));
			profileRepository.save(new Profile("ear", "earapp", "srp15437lx", "sit", "EAR-1"));
			profileRepository.save(new Profile("ear", "earapp", "srp17464lx", "dev", "EAR-1"));
			profileRepository.save(new Profile("jbt", "jbtapp", "srp75635lx", "prd", "JBT-1"));
			profileRepository.save(new Profile("jbt", "jbtapp", "srp72673lx", "uat", "JBT-1"));
			profileRepository.save(new Profile("jbt", "jbtapp", "srp74673lx", "sit", "JBT-1"));
			profileRepository.save(new Profile("jbt", "jbtapp", "srp17633lx", "dev", "JBT-1"));
			
		};
	}	
}
