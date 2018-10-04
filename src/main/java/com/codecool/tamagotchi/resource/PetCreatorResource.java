package com.codecool.tamagotchi.resource;

import com.codecool.tamagotchi.model.tamagotchi.Pet;
import com.codecool.tamagotchi.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PetCreatorResource {
    private final PetService petService;

    @Autowired
    public PetCreatorResource(PetService petService) {
        this.petService = petService;
    }

    @GetMapping("/createPet")
    public String petForm(Model model) {
        model.addAttribute("pet", new Pet());
        return "createPet";
    }

    @PostMapping("/createPet")
    public String petSubmit(@ModelAttribute Pet pet) {
        petService.createPet(pet);
        System.out.println(pet.toString());
        return "pet";
    }
}
