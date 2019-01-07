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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
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
@RequestMapping(value = "/services")
public class EndPoint {

	private static final Logger LOGGER = LoggerFactory.getLogger(EndPoint.class);

	@Autowired
	private ProfileRepository repository;

	@Value("${jenkins.url}")
	private String jenkinsUrl;

	@ApiOperation(value = "Create Jenkins Job")
	@RequestMapping(value = "/createjob", method = RequestMethod.POST)
	public String createPipeline(@ApiParam(value = "Spring boot service.") @RequestBody Profile profile) {

		MDC.put("ticket.id", "CRM-32");
		MDC.put("span.id", "start pipeline");

		LOGGER.info(String.format("start job"));

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();

		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		HttpEntity<String> entity = new HttpEntity<String>("", headers);

		// create folder

		String answer = null;

		try {

			// https://www.baeldung.com/rest-template

			// https://stackoverflow.com/questions/50408059/create-folder-in-jenkins-ui-using-curl

			String json = String.format(
					"{\"name\":\"%s\",\"mode\":\"com.cloudbees.hudson.plugins.folder.Folder\",\"from\":\"\",\"Submit\":\"OK\"}",
					profile.getDomain());

			MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
			map.add("name", profile.getDomain());
			map.add("mode", "com.cloudbees.hudson.plugins.folder.Folder");
			map.add("from", "");
			map.add("json", json);
			map.add("Submit", "OK");

			HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

			String url = String.format("%screateItem", jenkinsUrl);

			ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

		} catch (Exception e2) {
			LOGGER.info(String.format("folder already exists"));
		}

		// get template

		headers.setContentType(MediaType.TEXT_XML);

		ResponseEntity<String> response = null;

		try {
			response = restTemplate.getForEntity(jenkinsUrl + "/job/" + profile.getTemplate() + "/config.xml",
					String.class);

			entity = new HttpEntity<String>(response.getBody(), headers);

		} catch (Exception e1) {
			LOGGER.info(String.format("job template not found"));
		}

		// create new job from template

		try {

			answer = restTemplate.postForObject(
					jenkinsUrl + "/job/" + profile.getDomain() + "/createItem?name="
							+ String.format("%s-%s", profile.getName(), profile.getEnvironment()),
					entity, String.class);

		} catch (Exception e) {
			LOGGER.info(String.format("job already exists"));
		}

		return answer;
	}

	@ApiOperation(value = "Kick off jenkins deployment pipelines")
	@RequestMapping(value = "/startjob", method = RequestMethod.POST)
	public String startPipeline(@ApiParam(value = "Spring boot service.") @RequestBody Profile profile) {

		MDC.put("ticket.id", "CRM-32");
		MDC.put("span.id", "start pipeline");

		LOGGER.info(String.format("start job"));

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();

		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		HttpEntity<String> entity = new HttpEntity<String>("", headers);

		// create folder

		String answer = null;

		try {

			// https://www.baeldung.com/rest-template

			// https://stackoverflow.com/questions/50408059/create-folder-in-jenkins-ui-using-curl

			String json = String.format(
					"{\"name\":\"%s\",\"mode\":\"com.cloudbees.hudson.plugins.folder.Folder\",\"from\":\"\",\"Submit\":\"OK\"}",
					profile.getDomain());

			MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
			map.add("name", profile.getDomain());
			map.add("mode", "com.cloudbees.hudson.plugins.folder.Folder");
			map.add("from", "");
			map.add("json", json);
			map.add("Submit", "OK");

			HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

			String url = String.format("%screateItem", jenkinsUrl);

			ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

		} catch (Exception e2) {
			LOGGER.info(String.format("folder already exists"));
		}

		// get template

		headers.setContentType(MediaType.TEXT_XML);

		ResponseEntity<String> response = null;

		try {
			response = restTemplate.getForEntity(jenkinsUrl + "/job/" + profile.getTemplate() + "/config.xml",
					String.class);

			entity = new HttpEntity<String>(response.getBody(), headers);

		} catch (Exception e1) {
			LOGGER.info(String.format("job template not found"));
		}

		// create new job from template

		try {

			answer = restTemplate.postForObject(
					jenkinsUrl + "/job/" + profile.getDomain() + "/createItem?name="
							+ String.format("%s-%s", profile.getName(), profile.getEnvironment()),
					entity, String.class);

		} catch (Exception e) {
			LOGGER.info(String.format("job already exists"));
		}

		// https://stackoverflow.com/questions/15909650/create-jobs-and-execute-them-in-jenkins-using-rest
		// HttpEntity<String> entity = new HttpEntity<String>("", headers);

		// start job with parameters

		try {
			answer = restTemplate.postForObject(jenkinsUrl + "/job/" + profile.getDomain() + "/job/"
					+ String.format("%s-%s", profile.getName(), profile.getEnvironment())
					+ "/buildWithParameters?service=" + profile.getId(), entity, String.class);

		} catch (Exception e) {
			LOGGER.info(String.format("error starting job"));
		}

		return answer;
	}

	@ApiOperation(value = "List all profiles")
	@RequestMapping(value = "/profiles", method = RequestMethod.GET)
	public Iterable<Profile> getProfiles() {

		MDC.put("transaction.id", "profiles");
		LOGGER.info(String.format("get profiles"));

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		// https://stackoverflow.com/questions/15909650/create-jobs-and-execute-them-in-jenkins-using-rest

		HttpEntity<String> entity = new HttpEntity<String>("", headers);

		// Profile profile = new Profile("name", "host", "environment", );

		List<Profile> profiles = new ArrayList<>();

		// profiles.add(profile);

		return repository.findAll();
	}

	@ApiOperation(value = "add profile")
	@RequestMapping(value = "/addprofile", method = RequestMethod.POST)
	public ResponseEntity<String> addProfile(@RequestBody Profile profile) {

		MDC.put("transaction.id", "profiles");
		LOGGER.info(String.format("add profiles"));

		repository.save(profile);

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

}
