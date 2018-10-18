package com.codecool.tamagotchi.pet;

import com.codecool.tamagotchi.enumerations.Action;
import com.codecool.tamagotchi.enumerations.Type;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Random;
import javax.persistence.*;

@Entity
@Table(name = "pets")
public class Pet {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "user_id")
    @NotNull
    private String user_id;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "attack")
    @NotNull
    private int attack;

    @Column(name = "defence")
    @NotNull
    private int defence;

    @Column(name = "speed")
    @NotNull
    private int speed;

    @Column(name = "type")
    @NotNull
    private Type type;

    @Column(name = "exp")
    private int exp = 0;

    @Column(name = "health")
    private double health = 100;

    @Transient
    private Action state;

    @Transient
    private final double REDUCTION_VALUE = 0.75;
    @Transient
    private final double UPGRADE_VALUE = 1.25;

    // this variable is set in case second player is defending
    @Transient
    private int playersTwoDefencePoints;

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

    public int getPlayersTwoDefencePoints() {
        return playersTwoDefencePoints;
    }

    public void setPlayersTwoDefencePoints(int playersTwoDefencePoints) {
        this.playersTwoDefencePoints = playersTwoDefencePoints;
    }

    @Override
    public String toString() {
        return  "user_id: " + user_id + "\n" +
                "name: " + name + "\n" +
                "type: " + type + "\n" +
                "exp: " + exp + "\n" +
                "attack: " + attack + "\n" +
                "defence: " + defence + "\n" +
                "speed: " + speed + "\n" +
                "health: " + health + "\n" +
                "state: " + state + "\n";
    }
}
