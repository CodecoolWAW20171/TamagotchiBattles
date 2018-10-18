package com.codecool.tamagotchi.pet;

import com.codecool.tamagotchi.user.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PetController {

    private final PetRepository repository;

    @Autowired
    public PetController(PetRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/addNewPet")
    public String addNewPet(OAuth2Authentication authentication, @ModelAttribute Pet pet) {
        UserController uc = new UserController();
        String username = uc.getUsername(authentication);
        String userId = pet.getUser_id();
        pet.setUser_id(userId);
        pet.setName(username);
        repository.save(pet);
        return "redirect:/";
    }

    @MessageMapping("/requestAllPets")
    @SendTo("/lobby/pets")
    public Iterable<Pet> petsList() {
        return repository.findAll();
    }

}
