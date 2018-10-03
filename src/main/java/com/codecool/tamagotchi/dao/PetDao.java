package com.codecool.tamagotchi.dao;

import com.codecool.tamagotchi.model.Pet;

import java.util.List;

public interface PetDao {

    void primaryAttack(Pet playerOne, Pet playerTwo);
    int checkPrimaryTypes(Pet playerOne, Pet playerTwo, int power);
    void secondaryAttack(Pet playerOne, Pet playerTwo);
    int checkSecondaryTypes(Pet playerOne, Pet playerTwo, int power);
    List<Pet> selectAllPets();
}
