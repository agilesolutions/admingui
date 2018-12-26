package ch.agilesolutions.jdo.rest;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import ch.agilesolutions.jdo.domain.Profile;
import ch.agilesolutions.jdo.domain.ProfileRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


@RestController
@RequestMapping(value="/services")
public class EndPoint {

	private static final Logger LOGGER = LoggerFactory.getLogger(EndPoint.class);

	@Autowired
	private ProfileRepository repository;
	
	@Value("${jenkins.url}")
	private String jenkinsUrl;
	

	@ApiOperation(value = "Create Jenkins Job")
	@RequestMapping(value = "/newjob/{name}", method = RequestMethod.GET)
	public String createJob(@ApiParam(value = "Name of new Jenkins Pipeline Job.") @PathVariable("name") String name) {
		
		MDC.put("transaction.id", name);
		LOGGER.info(String.format("new job create"));

		RestTemplate restTemplate = new RestTemplate();

		// Thread.sleep(5000);
//		ResponseEntity<String> response = restTemplate.getForEntity("http://swagger-ui:8082/job/deploymentpipeline/api/json", String.class);
		ResponseEntity<String> response = restTemplate.getForEntity(jenkinsUrl + "/job/template/config.xml", String.class);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.TEXT_XML);
		
		// https://stackoverflow.com/questions/15909650/create-jobs-and-execute-them-in-jenkins-using-rest
		
		HttpEntity<String> entity = new HttpEntity<String>(response.getBody(), headers);
		String answer = restTemplate.postForObject(jenkinsUrl + "/view/pipelines/createItem?name=" + name, entity, String.class);

		return response.getBody();
	}
	
	@ApiOperation(value = "Kick off jenkins deployment pipelines")
	@RequestMapping(value = "/startjob/{name}", method = RequestMethod.GET)
	public String startJob(@ApiParam(value = "Name of pipeline to start.") @PathVariable("name") String name) {
		
		MDC.put("transaction.id", name);
		LOGGER.info(String.format("start job"));

		RestTemplate restTemplate = new RestTemplate();

		
		HttpHeaders headers = new HttpHeaders()	;
		headers.setContentType(MediaType.TEXT_XML);
		
		// https://stackoverflow.com/questions/15909650/create-jobs-and-execute-them-in-jenkins-using-rest
		
		HttpEntity<String> entity = new HttpEntity<String>("", headers);

		String answer = restTemplate.postForObject(jenkinsUrl + "/view/pipelines/job/" + name + "/buildWithParameters?service=" + name, entity, String.class);

		return answer;
	}
	
	@ApiOperation(value = "List all profiles")
	@RequestMapping(value = "/profiles", method = RequestMethod.GET)
	public Iterable<Profile> getProfiles() {
		
		MDC.put("transaction.id", "profiles");
		LOGGER.info(String.format("get profiles"));

		RestTemplate restTemplate = new RestTemplate();

		
		HttpHeaders headers = new HttpHeaders()	;
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		// https://stackoverflow.com/questions/15909650/create-jobs-and-execute-them-in-jenkins-using-rest
		
		HttpEntity<String> entity = new HttpEntity<String>("", headers);

//		Profile profile = new Profile("name", "host", "environment", );
		
		List<Profile> profiles = new ArrayList<>();
		
//			profiles.add(profile);

		return repository.findAll();
	}

	
	@ApiOperation(value = "add profile")
	@RequestMapping(value = "/addprofile", method = RequestMethod.POST)
	public ResponseEntity < String> addProfile(@RequestBody Profile profile) {
		
		MDC.put("transaction.id", "profiles");
		LOGGER.info(String.format("add profiles"));


		repository.save(profile);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	
}
