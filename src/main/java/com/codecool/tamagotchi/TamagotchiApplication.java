package com.codecool.tamagotchi;

import com.codecool.tamagotchi.pet.Pet;
import com.codecool.tamagotchi.pet.PetRepository;
import com.codecool.tamagotchi.user.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@Controller
public class TamagotchiApplication {

	private final PetRepository petRepository;

	@Autowired
	public TamagotchiApplication(PetRepository petRepository) {
		this.petRepository = petRepository;
		System.out.println("main repo: " + petRepository);
	}

	public static void main(String[] args) {
		SpringApplication.run(TamagotchiApplication.class, args);
	}

	@RequestMapping("/")
	public String home(OAuth2Authentication authentication, Model model) {
		if (authentication != null) {
			Long userId = new UserController().getUserId(authentication);

			if(petRepository.findPetById(userId) == null) {
				model.addAttribute("pet", new Pet());
				return "createPet";
			}
			return "index";
		}
		return "login";
	}

	@RequestMapping("/login")
	public String goHomeUAreDrunk() {
		return "/";
	}

}
