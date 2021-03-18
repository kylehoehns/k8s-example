package com.example.names;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@SpringBootApplication
public class NamesApplication {

	public static void main(String[] args) {
		SpringApplication.run(NamesApplication.class, args);
	}

	@Bean
	RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
		return restTemplateBuilder.build();
	}

}

@RestController
@RequestMapping("/names")
@RequiredArgsConstructor
@Slf4j
class NamesRestController {

	private final RestTemplate restTemplate;

	@Value("${uppercase.endpoint}")
	private String uppercaseEndpoint;

	@GetMapping
	public String turnNameToUpperCase(@RequestParam("name") String name) {
		var uri = UriComponentsBuilder.fromUriString(uppercaseEndpoint)
			.queryParam("value", name)
			.build()
			.toUri();

		String response = "Hello, ";
		try {
			var uppercaseName  = restTemplate.getForEntity(uri, String.class).getBody();
			response += uppercaseName;
		} catch (HttpClientErrorException e) {
			log.error("Unable to call uppercase service", e);
			response += name;
		}
		return response;
	}


}
