package com.codecool.tamagotchi.pet;

import com.codecool.tamagotchi.enumerations.Action;
import com.codecool.tamagotchi.enumerations.Type;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Random;

@Entity
public class Pet {
    @Id
    private Long id;
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

    private final double REDUCTION_VALUE = 0.75;
    private final double UPGRADE_VALUE = 1.25;

    // this variable is set in case second player is defending
    private int playersTwoDefencePoints;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pet() {}

    public Pet(String name, int attack, int defence, int speed, Type type) {
        this.name = name;
        this.attack = attack;
        this.defence = defence;
        this.speed = speed;
        this.type = type;
    }

    public void primaryAttack(Pet player) {
        if (!checkIfEvaded(player)) {
            int attackPower = this.getAttack();
            setPlayersTwoDefencePoints(getSecondPlayersDefence(player));
            attackPower = checkPrimaryTypes(player, attackPower);
            attackPower = attackPower - getPlayersTwoDefencePoints();
            if (attackPower > 0) player.setHealth(player.getHealth() - attackPower);
        }
    }

    private int checkPrimaryTypes(Pet player, int power) {
        if (this.getType().equals(player.getType())) {
            return power;
        } if (Type.FIRE.equals(this.getType()) && Type.WATER.equals(player.getType())
                || Type.WATER.equals(this.getType()) && Type.EARTH.equals(player.getType())
                || Type.EARTH.equals(this.getType()) && Type.FIRE.equals(player.getType())) {
            return (int) (power * REDUCTION_VALUE);
        } else {
            return (int) (power * UPGRADE_VALUE);
        }
    }

    public void secondaryAttack(Pet player) {
        if (!checkIfEvaded(player)) {
            setPlayersTwoDefencePoints(getSecondPlayersDefence(player));
            int attackPower = (int) (this.getAttack() * REDUCTION_VALUE);
            attackPower = checkSecondaryTypes(player, attackPower);
            attackPower = attackPower - getPlayersTwoDefencePoints();
            if (attackPower > 0) player.setHealth(player.getHealth() - attackPower);
        }
    }

    private int checkSecondaryTypes(Pet player, int power) {
        if (this.getClass() == player.getClass()) {
            return (int) (power * REDUCTION_VALUE);
        } if (Type.FIRE.equals(this.getType()) && Type.WATER.equals(player.getType())
                || Type.WATER.equals(this.getType()) && Type.EARTH.equals(player.getType())
                || Type.EARTH.equals(this.getType()) && Type.FIRE.equals(player.getType())) {
            return (int) (power * UPGRADE_VALUE);
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

    private boolean checkIfEvaded(Pet player) {
        double LOWER_LIMIT = REDUCTION_VALUE;
        double UPPER_LIMIT = UPGRADE_VALUE;
        Random rand = new Random();
        if (Action.EVADE.equals(player.getState())) {
            /* return true is one players speed multiplied by random value between given brackets 0.75 - 1.25 if faster
             than the other */
            return (player.getSpeed() * (LOWER_LIMIT + rand.nextDouble() * (UPPER_LIMIT - LOWER_LIMIT))
                    - this.getSpeed() * (LOWER_LIMIT + rand.nextDouble() * (UPPER_LIMIT - LOWER_LIMIT)) > 0);
        }
        return false;
    }

    public String getName() {
        return name;
    }

    void setName(String name) {
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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public double getHealth() {
        return health;
    }

    private void setHealth(double health) {
        this.health = health;
    }

    public Action getState() {
        return state;
    }

    public void setState(Action state) {
        this.state = state;
    }

    private int getPlayersTwoDefencePoints() {
        return playersTwoDefencePoints;
    }

    private void setPlayersTwoDefencePoints(int playersTwoDefencePoints) {
        this.playersTwoDefencePoints = playersTwoDefencePoints;
    }

    @Override
    public String toString() {
        return  "\nid: " + id +
                "\nname: " + name +
                "\ntype: " + type +
                "\nexp: " + exp +
                "\nattack: " + attack +
                "\ndefence: " + defence +
                "\nspeed: " + speed +
                "\nhealth: " + health +
                "\nstate: " + state;
    }
}
