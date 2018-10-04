package com.codecool.tamagotchi.dao;

import com.codecool.tamagotchi.model.tamagotchi.Pet;
import com.codecool.tamagotchi.model.tamagotchi.classes.*;
import com.codecool.tamagotchi.model.tamagotchi.enumerations.Action;

import java.util.Random;

public class BattleDaoImpl implements BattleDao {

    private final double WEAKER_ATTACK = 0.75;
    private final double STRONGER_ATTACK = 1.25;

    private int playersTwoDefencePoints;
    private Pet playerOne;
    private Pet playerTwo;

    public BattleDaoImpl(Pet playerOne, Pet playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }

    @Override
    public void primaryAttack(Pet player) {
        if (!checkIfEvaded()) {
            int attackPower = player.getAttack();
            setPlayersTwoDefencePoints(getSecondPlayersDefence(player));
            attackPower = checkPrimaryTypes(attackPower);
            attackPower = attackPower - getPlayersTwoDefencePoints();
            if (attackPower > 0) player.setHealth(player.getHealth() - attackPower);
            System.out.println("first attack attackPower " + attackPower);
        }
    }

    @Override
    public int checkPrimaryTypes(int power) {
        if (playerOne.getClass() == playerTwo.getClass()) {
            return power;
        }
        if (playerOne instanceof Fire && playerTwo instanceof Water
                || playerOne instanceof Water && playerTwo instanceof Earth
                || playerOne instanceof Earth && playerTwo instanceof Fire) {
            return (int) (power * WEAKER_ATTACK);
        } else {
            return (int) (power * STRONGER_ATTACK);
        }
    }

    @Override
    public void secondaryAttack(Pet player) {
        if (!checkIfEvaded()) {
            double SECONDARY_ATTACK_REDUCTION = 0.75;
            setPlayersTwoDefencePoints(getSecondPlayersDefence(player));
            int attackPower = (int) (player.getAttack() * SECONDARY_ATTACK_REDUCTION);
            attackPower = checkSecondaryTypes(attackPower);
            attackPower = attackPower - getPlayersTwoDefencePoints();
            if (attackPower > 0) player.setHealth(player.getHealth() - attackPower);
            System.out.println("secondaryAttack attackPower " + attackPower);
        }
    }

    @Override
    public int checkSecondaryTypes(int power) {
        if (playerOne.getClass() == playerTwo.getClass()) {
            return (int) (power * WEAKER_ATTACK);
        }
        if (playerOne instanceof Fire && playerTwo instanceof Water
                || playerOne instanceof Water && playerTwo instanceof Earth
                || playerOne instanceof Earth && playerTwo instanceof Fire) {
            return (int) (power * STRONGER_ATTACK);
        } else {
            return power;
        }
    }

    @Override
    public int getSecondPlayersDefence(Pet player) {
        if (player.getState().equals(Action.DEFEND)) {
            // defending player has doubled his defence
            return 2 * player.getDefence();
        } else {
            return player.getDefence();
        }
    }

    @Override
    public boolean checkIfEvaded() {
        double LOWER_LIMIT = 0.75;
        double UPPER_LIMIT = 1.25;
        Random rand = new Random();

        if (!playerOne.getState().equals(Action.EVADE)) {
            return playerOne.getSpeed() * (LOWER_LIMIT + rand.nextDouble() * (UPPER_LIMIT - LOWER_LIMIT))
                    - playerTwo.getSpeed() * (LOWER_LIMIT + rand.nextDouble() * (UPPER_LIMIT - LOWER_LIMIT)) > 0;
        } else {
            return false;
        }
    }

    public int getPlayersTwoDefencePoints() {
        return playersTwoDefencePoints;
    }

    public void setPlayersTwoDefencePoints(int playersTwoDefencePoints) {
        this.playersTwoDefencePoints = playersTwoDefencePoints;
    }

    @Override
    public String toString() {
        return playerOne.toString() + "\n" + playerTwo.toString();
    }
}
