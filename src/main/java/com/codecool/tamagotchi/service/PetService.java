package com.codecool.tamagotchi.service;

import com.codecool.tamagotchi.dao.PetDao;
import com.codecool.tamagotchi.model.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {
    private final PetDao petDao;

    @Autowired
    public PetService(@Qualifier("DB") PetDao petDao) {
        this.petDao = petDao;
    }

    public List<Pet> getAllPets() {
        return petDao.selectAllPets();
    }
}
