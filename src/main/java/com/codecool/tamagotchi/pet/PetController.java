package com.codecool.tamagotchi.pet;

import com.codecool.tamagotchi.controller.ManagePet;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PetController {


    @GetMapping("/createPet")
    public String petForm(Model model) {
        model.addAttribute("pet", new Pet());
        return "createPet";
    }

    @PostMapping("/createPet")
    public String petSubmit(@ModelAttribute Pet pet) {
        ManagePet mp = new ManagePet();
        mp.addPet(pet);
        System.out.println(pet);
        return "pet";
    }
}
