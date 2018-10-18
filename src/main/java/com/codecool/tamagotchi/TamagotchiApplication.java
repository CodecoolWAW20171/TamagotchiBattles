package com.codecool.tamagotchi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@Controller
public class TamagotchiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TamagotchiApplication.class, args);
	}

	@RequestMapping("/")
	public String home(OAuth2Authentication authentication) {
		if (authentication != null) return "index";
		return "login";
	}

}
