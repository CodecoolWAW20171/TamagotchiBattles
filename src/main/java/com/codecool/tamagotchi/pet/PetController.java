package com.codecool.tamagotchi.pet;

import com.codecool.tamagotchi.controller.ManagePet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PetController {

    @Autowired
    private PetRepository repository;

    @GetMapping("/createPet")
    public String petForm(Model model) {
        model.addAttribute("pet", new Pet());
        System.out.println("OK!!!!!!");
        return "createPet";
    }

    @PostMapping("/createPet")
    public String petSubmit(@ModelAttribute Pet pet) {
        ManagePet mp = new ManagePet();
        mp.addPet(pet);
        System.out.println(pet);
        return "pet";
    }

    @MessageMapping("/requestAllPets")
    @SendTo("/lobby/pets")
    public Iterable<Pet> petsList() {
        return repository.findAll();
    }

}
