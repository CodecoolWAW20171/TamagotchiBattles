package com.codecool.tamagotchi.model.tamagotchi;

import com.codecool.tamagotchi.model.tamagotchi.enumerations.Action;
import com.codecool.tamagotchi.model.tamagotchi.enumerations.Type;

import javax.validation.constraints.NotNull;

public class Pet {
    @NotNull
    private String name;
    @NotNull
    private int attack;
    @NotNull
    private int defence;
    @NotNull
    private int speed;
    @NotNull
    private Type type;
    private int exp = 0;
    private double health = 100;
    private Action state;

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

    public void setDefence(int defend) {
        this.defence = defend;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
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

    @Override
    public String toString() {
        return  "name: " + name + "\n" +
                "type: " + type + "\n" +
                "exp: " + exp + "\n" +
                "attack: " + attack + "\n" +
                "defence: " + defence + "\n" +
                "speed: " + speed + "\n" +
                "health: " + health + "\n" +
                "state: " + state + "\n";
    }
}
