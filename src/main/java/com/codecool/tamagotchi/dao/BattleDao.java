package com.codecool.tamagotchi.dao;

import com.codecool.tamagotchi.model.tamagotchi.Pet;

public interface BattleDao {

    void primaryAttack(Pet player);
    int checkPrimaryTypes(int power);
    void secondaryAttack(Pet player);
    int checkSecondaryTypes(int power);
    int getSecondPlayersDefence(Pet player);
    boolean checkIfEvaded();
}
