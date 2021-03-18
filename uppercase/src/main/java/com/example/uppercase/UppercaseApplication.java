package com.example.uppercase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class UppercaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(UppercaseApplication.class, args);
	}

}

@RestController
@RequestMapping("/uppercase")
class UppercaseRestController {

	@GetMapping
	public String toUpperCase(@RequestParam("value") String value) {
		return value.toUpperCase();
	}
}
