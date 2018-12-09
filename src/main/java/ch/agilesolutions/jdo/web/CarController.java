package ch.agilesolutions.jdo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.agilesolutions.jdo.domain.Profile;
import ch.agilesolutions.jdo.domain.ProfileRepository;

@RestController
public class CarController {
	@Autowired
	private ProfileRepository repository;
	
	@RequestMapping("/cars")
	public Iterable<Profile> getCars() {
		return repository.findAll();
	}
}
