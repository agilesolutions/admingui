package ch.agilesolutions.jdo.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/services")
@Api(value = "onlinestore", description = "Spring Boot Deployment API")
public class EndPoint {

	private static final Logger LOGGER = LoggerFactory.getLogger(EndPoint.class);

	@ApiOperation(value = "Generate new deployment pipeline")

	@ApiResponses(value = {

			@ApiResponse(code = 200, message = "Successfully retrieved list"),

			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),

			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),

			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")

	}

	)
	@RequestMapping(value = "/newjob/{name}", method = RequestMethod.GET)
	public String newJob(@PathVariable String name) {


		MDC.put("transaction.id", name);
		LOGGER.info(String.format("new job create"));

		LOGGER.info(String.format("Deploy Spring Boot package"));

		RestTemplate restTemplate = new RestTemplate();

		// Thread.sleep(5000);
		ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/job/template/api/json",
				String.class);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> entity = new HttpEntity<String>(response.getBody(), headers);
		String answer = restTemplate.postForObject("http://localhost:8080/createItem?name=" + name, entity,
				String.class);

		return response.getBody();
	}

}
