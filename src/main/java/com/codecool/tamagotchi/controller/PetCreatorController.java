package com.codecool.tamagotchi.controller;

import com.codecool.tamagotchi.model.tamagotchi.Pet;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PetCreatorController {

    @GetMapping("/createPet")
    public String petForm(Model model) {
        model.addAttribute("pet", new Pet());
        return "createPet";
    }

    @PostMapping("/createPet")
    public String petSubmit(@ModelAttribute Pet pet) {
        System.out.println(pet.toString());
        return "pet";
    }
}