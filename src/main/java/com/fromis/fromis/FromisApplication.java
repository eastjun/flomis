package com.fromis.fromis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class FromisApplication {

	public static void main(String[] args) {
		SpringApplication.run(FromisApplication.class, args);
	}

	@GetMapping("/")
	public  String index(){

		return "index";
	}
}
