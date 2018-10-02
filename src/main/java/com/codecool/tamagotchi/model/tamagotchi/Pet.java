package com.codecool.tamagotchi.model.tamagotchi;

import com.codecool.tamagotchi.model.tamagotchi.classes.Fire;
import com.codecool.tamagotchi.model.tamagotchi.classes.Earth;
import com.codecool.tamagotchi.model.tamagotchi.classes.Water;
import com.codecool.tamagotchi.model.tamagotchi.enumerations.Action;

import java.lang.reflect.WildcardType;
import java.util.Random;

public class Pet {
    private String name;
    private int exp = 0;
    private int attack;
    private int defence;
    private int speed;
    private double health = 100;
    private Action state;

    private final double WEAKER_ATTACK = 0.75;
    private final double STRONGER_ATTACK = 1.25;

    private int playersTwoDefencePoints;

    public Pet(String name, int attack, int defence, int speed) {
        this.name = name;
        this.attack = attack;
        this.defence = defence;
        this.speed = speed;
    }

    public void primaryAttack(Pet playerOne, Pet playerTwo) {
        if (!checkIfEvaded(playerOne, playerTwo)) {
            int attackPower = playerOne.getAttack();
            playersTwoDefencePoints = getSecondPlayersDefence(playerTwo);
            attackPower = checkPrimaryTypes(playerOne, playerTwo, attackPower);
            attackPower = attackPower - playersTwoDefencePoints;
            if (attackPower > 0) playerTwo.setHealth(playerTwo.getHealth() - attackPower);
        }
    }

    private int checkPrimaryTypes(Pet playerOne, Pet playerTwo, int power) {
        if (playerOne.getClass() == playerTwo.getClass()) {
            return power;
        } if (playerOne instanceof Fire && playerTwo instanceof Water
                || playerOne instanceof Water && playerTwo instanceof Earth
                || playerOne instanceof Earth && playerTwo instanceof Fire) {
            return (int) (power * WEAKER_ATTACK);
        } else {
            return (int) (power * STRONGER_ATTACK);
        }
    }

    public void secondaryAttack(Pet playerOne, Pet playerTwo) {
        if (!checkIfEvaded(playerOne, playerTwo)) {
            double SECONDARY_ATTACK_REDUCTION = 0.75;
            playersTwoDefencePoints = getSecondPlayersDefence(playerTwo);
            int attackPower = (int) (playerOne.getAttack() * SECONDARY_ATTACK_REDUCTION);
            attackPower = checkSecondaryTypes(playerOne, playerTwo, attackPower);
            attackPower = attackPower - playersTwoDefencePoints;
            if (attackPower > 0) playerTwo.setHealth(playerTwo.getHealth() - attackPower);
        }
    }

    private int checkSecondaryTypes(Pet playerOne, Pet playerTwo, int power) {
        if (playerOne.getClass() == playerTwo.getClass()) {
            return (int) (power * WEAKER_ATTACK);
        } if (playerOne instanceof Fire && playerTwo instanceof Water
                || playerOne instanceof Water && playerTwo instanceof Earth
                || playerOne instanceof Earth && playerTwo instanceof Fire) {
            return (int) (power * STRONGER_ATTACK);
        } else {
            return power;
        }
    }

    private int getSecondPlayersDefence(Pet player) {
        if (player.getState().equals(Action.DEFEND)) {
            // defending player has doubled his defence
            return 2 * player.getDefence();
        } else {
            return player.getDefence();
        }
    }

    private boolean checkIfEvaded(Pet playerOne, Pet playerTwo) {
        double LOWER_LIMIT = 0.75;
        double UPPER_LIMIT = 1.25;
        Random rand = new Random();
        return playerOne.getSpeed() * (LOWER_LIMIT + rand.nextDouble() * (UPPER_LIMIT - LOWER_LIMIT))
                - playerTwo.getSpeed() * (LOWER_LIMIT + rand.nextDouble() * (UPPER_LIMIT - LOWER_LIMIT)) > 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public Action getState() {
        return state;
    }

    public void setState(Action state) {
        this.state = state;
    }
}
