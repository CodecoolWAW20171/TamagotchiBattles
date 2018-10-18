package com.codecool.tamagotchi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
public class TamagotchiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TamagotchiApplication.class, args);
	}

	@RequestMapping("/")
	public String home() {
		return "index";
	}

}
