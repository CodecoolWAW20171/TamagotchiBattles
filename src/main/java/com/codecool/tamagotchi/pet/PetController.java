package com.codecool.tamagotchi.pet;

import com.codecool.tamagotchi.controller.ManagePet;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.LinkedHashMap;


@Controller
public class PetController {


    @GetMapping("/createPet")
    public String petForm(Model model) {
        model.addAttribute("pet", new Pet());
        return "createPet";
    }

    @PostMapping("/createPet")
    public String petSubmit(@ModelAttribute Pet pet, OAuth2Authentication authentication) {
        ManagePet mp = new ManagePet();
        if (authentication != null) {
            LinkedHashMap<String, Object> properties =
                    (LinkedHashMap<String, Object>) authentication.getUserAuthentication().getDetails();
            System.out.println(properties.get("id"));
            pet.setId(Long.parseLong(properties.get("id").toString()));
        }
        System.out.println(pet);
        mp.addPet(pet);
        return "pet";
    }
}
