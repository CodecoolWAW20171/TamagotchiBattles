package com.codecool.tamagotchi.pet;

import com.codecool.tamagotchi.enumerations.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PetController {

    @Autowired
    private PetRepository repository;

    @GetMapping("/createPet")
    public String petForm(Model model) {
        model.addAttribute("pet", new Pet());
        return "createPet";
    }

    @MessageMapping("/requestAllPets")
    @SendTo("/lobby/pets")
    public Iterable<Pet> petsList() {
        return repository.findAll();
    }

}
