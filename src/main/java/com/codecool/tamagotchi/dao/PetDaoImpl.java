package com.codecool.tamagotchi.dao;

import com.codecool.tamagotchi.model.tamagotchi.Pet;
import com.codecool.tamagotchi.model.tamagotchi.enumerations.Action;
import com.codecool.tamagotchi.model.tamagotchi.enumerations.Type;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("DB")
public class PetDaoImpl implements PetDao {
    private double WEAKER_ATTACK = 0.75;
    private double STRONGER_ATTACK = 1.25;
    private Action state;

    DataBaseConnection dataBaseConnection;

    @Override
    public List<Pet> selectAllPets() {
        List<Pet> stringList = new ArrayList<>();

        dataBaseConnection = new DataBaseConnection();
        dataBaseConnection.connectDB();
        for (Object pet : dataBaseConnection.runQuery("from Pet")) {
            stringList.add((Pet)pet);
        }
        return stringList;
    }

    @Override
    public void primaryAttack(Pet playerOne, Pet playerTwo) {
        int attackPower = playerOne.getAttack();
        attackPower = checkPrimaryTypes(playerOne, playerTwo, attackPower);
        attackPower = attackPower - playerTwo.getDefence();
        playerTwo.setHealth(playerTwo.getHealth() - attackPower);
    }

    @Override
    public int checkPrimaryTypes(Pet playerOne, Pet playerTwo, int power) {
        if (playerOne.getClass() == playerTwo.getClass()) {
            return power;
        }
        if (playerOne.getType() == Type.FIRE && playerTwo.getType() == Type.WATER
                || playerOne.getType() == Type.WATER && playerOne.getType() == Type.EARTH
                || playerOne.getType() == Type.EARTH && playerOne.getType() == Type.FIRE) {
            return (int) (power * WEAKER_ATTACK);
        } else {
            return (int) (power * STRONGER_ATTACK);
        }
    }

    @Override
    public void secondaryAttack(Pet playerOne, Pet playerTwo) {
        double SECONDARY_ATTACK_REDUCTION = 0.75;
        int attackPower = (int) (playerOne.getAttack() * SECONDARY_ATTACK_REDUCTION);
        attackPower = checkSecondaryTypes(playerOne, playerTwo, attackPower);
        attackPower = attackPower - playerTwo.getDefence();
        playerTwo.setHealth(playerTwo.getHealth() - attackPower);
    }

    @Override
    public int checkSecondaryTypes(Pet playerOne, Pet playerTwo, int power) {
        if (playerOne.getClass() == playerTwo.getClass()) {
            return (int) (power * WEAKER_ATTACK);
        }
        if (playerOne.getType() == Type.FIRE && playerTwo.getType() == Type.WATER
                || playerOne.getType() == Type.WATER && playerOne.getType() == Type.EARTH
                || playerOne.getType() == Type.EARTH && playerOne.getType() == Type.FIRE) {
            return (int) (power * STRONGER_ATTACK);
        } else {
            return power;
        }
    }
}
