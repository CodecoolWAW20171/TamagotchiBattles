package com.codecool.tamagotchi.model.tamagotchi;

import com.codecool.tamagotchi.model.tamagotchi.enumerations.Action;

public class Pet {
    private String name;
    private int exp = 0;
    private int attack;
    private int defend;
    private int speed;
    private double health = 100;
    private Action state;

    public Pet(String name, int attack, int defend, int speed) {
        this.name = name;
        this.attack = attack;
        this.defend = defend;
        this.speed = speed;
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

    public int getDefend() {
        return defend;
    }

    public void setDefend(int defend) {
        this.defend = defend;
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
