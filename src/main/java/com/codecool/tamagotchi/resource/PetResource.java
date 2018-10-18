package com.codecool.tamagotchi.resource;

import com.codecool.tamagotchi.pet.Pet;
import com.codecool.tamagotchi.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pets")
public class PetResource {

    private final PetService petService;

    @Autowired
    public PetResource(PetService petService) {
        this.petService = petService;
    }

    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
//    private List<Pet> getAllPets() {
//        return petService.getAllPets();
//    }

    private List<Pet> getPetById() {
        return petService.getPetById(2);
    }
}
