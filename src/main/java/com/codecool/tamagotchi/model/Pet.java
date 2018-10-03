package com.codecool.tamagotchi.model;

import javax.persistence.*;

@Entity
@Table(name = "pets")
public class Pet {
    /*public Pet(){

    }*/

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;
    @Column(name = "exp")
    private int exp = 0;
    @Column(name = "attack")
    private int attack;
    @Column(name = "defence")
    private int defence;
    @Column(name = "speed")
    private int speed;
    @Column(name = "health")
    private int health;
   /* private Action state;*/

   /* private final double WEAKER_ATTACK = 0.75;
    private final double STRONGER_ATTACK = 1.25;

    private int playersTwoDefencePoints;

    public Pet(String name, int attack, int defence, int speed) {
        this.name = name;
        this.attack = attack;
        this.defence = defence;
        this.speed = speed;
    }

    public void primaryAttack(Pet player) {
        if (!checkIfEvaded(this, player)) {
            int attackPower = this.getAttack();
            setPlayersTwoDefencePoints(getSecondPlayersDefence(player));
            attackPower = checkPrimaryTypes(this, player, attackPower);
            attackPower = attackPower - getPlayersTwoDefencePoints();
            if (attackPower > 0) player.setHealth(player.getHealth() - attackPower);
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

    public void secondaryAttack(Pet player) {
        if (!checkIfEvaded(this, player)) {
            double SECONDARY_ATTACK_REDUCTION = 0.75;
            setPlayersTwoDefencePoints(getSecondPlayersDefence(player));
            int attackPower = (int) (this.getAttack() * SECONDARY_ATTACK_REDUCTION);
            attackPower = checkSecondaryTypes(this, player, attackPower);
            attackPower = attackPower - getPlayersTwoDefencePoints();
            if (attackPower > 0) player.setHealth(player.getHealth() - attackPower);
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
        if (!playerOne.getState().equals(Action.EVADE)) {
            return playerOne.getSpeed() * (LOWER_LIMIT + rand.nextDouble() * (UPPER_LIMIT - LOWER_LIMIT))
                    - playerTwo.getSpeed() * (LOWER_LIMIT + rand.nextDouble() * (UPPER_LIMIT - LOWER_LIMIT)) > 0;
        } else {
            return false;
        }
    }
*/
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

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
/*
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
    }*/
}
