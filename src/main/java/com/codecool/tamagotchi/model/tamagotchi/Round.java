package com.codecool.tamagotchi.model.tamagotchi;

import com.codecool.tamagotchi.model.tamagotchi.enumerations.Action;
import com.codecool.tamagotchi.model.tamagotchi.enumerations.Type;

import java.util.Random;

public class Round {
    private Pet player1;
    private Pet player2;
    private Pet firstAttacker;
    private Pet secondAttacker;
    private double luckModifier;

    public Round(Pet player1, Pet player2) {
        this.player1 = player1;
        this.player2 = player2;
        setLuckModifier(calculateLuckModifier());
    }

    public void fight() {

        setOrderAttack(player1, player2);

        if (firstAttacker.getState().equals(Action.ATTACK) ||
                firstAttacker.getState().equals(Action.SECONDARY_ATTACK)) {
            double damage = countDamage(firstAttacker);
            secondAttacker.setHealth(secondAttacker.getHealth() - damage) ;
        }

        if (attackerCanAttack(secondAttacker)) {
            double damage = countDamage(secondAttacker);
            firstAttacker.setHealth(firstAttacker.getHealth() - damage);
        }
        showHealth();
    }

    private void showHealth(){
        System.out.println("First attacker " + firstAttacker.getName() + " health: " + firstAttacker.getHealth());
        System.out.println("Second attacker " + secondAttacker.getName() + " health: " + secondAttacker.getHealth());
    }

    private boolean attackerCanAttack(Pet attacker) {
        if(attacker.getHealth() <= 0) {
            return false;
        }
        return true;
    }

    private void endRound(){
        System.out.println("End of round");
    }

    void compareSpeed(Pet player1, Pet player2) {
        if (player1.getSpeed() > player2.getSpeed()) {
            firstAttacker = player1;
            secondAttacker = player2;
        } else if (player1.getSpeed() < player2.getSpeed()) {
            firstAttacker = player2;
            secondAttacker = player1;
        } else {
            calculateLuck(player1, player2);
        }
    }

    double calculateLuckModifier() {
        Random rand = new Random();
        double rangeMin = 0.75;
        double rangeMax = 1.25;
        double luck = rangeMin + (rangeMax - rangeMin) * rand.nextDouble();
        return luck;
    }

    void calculateLuck(Pet player1, Pet player2) {
        if (getLuckModifier() > 1) {
            firstAttacker = player1;
            secondAttacker = player2;
        } else {
            firstAttacker = player2;
            secondAttacker = player1;
        }
    }

    void setOrderAttack(Pet player1, Pet player2) {
        if (player1.getState().equals(Action.ATTACK) ||
                player1.getState().equals(Action.SECONDARY_ATTACK)) {
            if (player2.getState().equals(Action.ATTACK) ||
                    player2.getState().equals(Action.SECONDARY_ATTACK)) {
                compareSpeed(player1, player2);
            } else {
                firstAttacker = player1;
                secondAttacker = player2;
            }
        } else if (player2.getState().equals(Action.ATTACK) ||
                player2.getState().equals(Action.SECONDARY_ATTACK)) {
            if (player1.getState().equals(Action.ATTACK) ||
                    player1.getState().equals(Action.SECONDARY_ATTACK)) {
                compareSpeed(player1, player2);
            } else {
                firstAttacker = player2;
                secondAttacker = player1;
            }
        } else {
            firstAttacker = player1;
            secondAttacker = player2;
        }
    }

    public double countDamage(Pet attacker) {
        double damage = 0;
        Pet defender;
        if(attacker.equals(firstAttacker)){
            defender = secondAttacker;
        } else {
            defender = firstAttacker;
        }
        if (attacker.getState().equals(Action.ATTACK) || attacker.getState().equals(Action.SECONDARY_ATTACK)){
            if( defender.getState().equals(Action.DEFEND)) {
                damage = attacker.getAttack() * countAttackerDefenderModifier() - defender.getDefence() * 2;
            } else if( defender.getState().equals(Action.EVADE)) {
                damage = attacker.getSpeed() * getLuckModifier() - defender.getSpeed() * getLuckModifier();
            } else {
                damage = attacker.getAttack() * countAttackerDefenderModifier() - defender.getDefence();
                System.out.println("Damage: " + damage);
            }
        }

        return Math.abs(damage);
    }

    double countAttackerDefenderModifier() {
        double modifier = 1;

        if (firstAttacker.getType().equals(Type.ROCK)) {
            if (secondAttacker.getType().equals(Type.ROCK)) {
                modifier = 1;
            } else if (secondAttacker.getType().equals(Type.PAPER)) {
                modifier = 0.75;
            } else if (secondAttacker.getType().equals(Type.SCISSORS)) {
                modifier = 1.25;
            }
        }
        return modifier;
    }

    private void showAttackOrder() {
        System.out.println("Luck " + getLuckModifier());
        System.out.println("First attacker is " + firstAttacker.getName());
        System.out.println("Second attacker is " + secondAttacker.getName());
    }

    public Pet getFirstAttacker() {
        return firstAttacker;
    }

    public Pet getSecondAttacker() {
        return secondAttacker;
    }

    public double getLuckModifier() {
        return luckModifier;
    }

    public void setLuckModifier(double luckModifier) {
        this.luckModifier = luckModifier;
    }
}
