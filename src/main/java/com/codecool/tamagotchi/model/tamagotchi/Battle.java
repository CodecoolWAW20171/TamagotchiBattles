package com.codecool.tamagotchi.model.tamagotchi;

import com.codecool.tamagotchi.model.tamagotchi.enumerations.Action;

import java.util.Random;

public class Battle {
    private Pet firstPlayer;
    private Pet secondPlayer;
    private double firstPlayerHealthAtStartOfTurn;
    private double secondPlayerHealthAtStartOfTurn;
    // Calculate turns for battle to view information in user friendly way, starting from 1
    private int turn = 1;
    private String battleFinalString;

    public Battle(Pet firstPlayer, Pet secondPlayer) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
    }

    public void getOrder() {
        setFirstPlayerHealthAtStartOfTurn(getFirstPlayer().getHealth());
        setSecondPlayerHealthAtStartOfTurn(getSecondPlayer().getHealth());
        if (getFirstPlayer().getSpeed() > getSecondPlayer().getSpeed()) {
            moveFirstPlayerFirst();
        } else if (getFirstPlayer().getSpeed() == getSecondPlayer().getSpeed()){
            Random rand = new Random();
            //If both players have the same speed, pick one to move first at random, using nextDouble
            //To give them both 50% chance, we use 0,5 as parameter
            if (rand.nextDouble() > 0.5) moveFirstPlayerFirst(); else moveSecondPlayerFirst();
        } else moveSecondPlayerFirst();
    }

    private void moveFirstPlayerFirst() {
        makeAMove(getFirstPlayer(), getSecondPlayer());
    }

    private void moveSecondPlayerFirst() {
        makeAMove(getSecondPlayer(), getFirstPlayer());
    }

    public void makeAMove(Pet playerOne, Pet playerTwo) {
        if (checkPrimaryAttack(playerOne)) {
            playerOne.primaryAttack(playerTwo);
        } else if (checkSecondaryAttack(playerOne)) {
            playerOne.secondaryAttack(playerTwo);
        } if (!checkIfWon(playerOne)) {
            if (checkPrimaryAttack(playerTwo)) {
                playerTwo.primaryAttack(playerOne);
            } else if (checkSecondaryAttack(playerTwo)) {
                playerTwo.secondaryAttack(playerOne);
            }
            if (checkIfWon(playerTwo)) {
                playerTwo.setExp((int) (playerTwo.getExp() + playerTwo.getHealth()));
                addWinningString(playerTwo);
            } else {
                getBattleResult();
            }
        } else {
            playerOne.setExp((int) (playerOne.getExp() + playerOne.getHealth()));
            getBattleResult();
            addWinningString(playerOne);
        }
    }

    public boolean checkPrimaryAttack(Pet player) {
        return player.getState().equals(Action.ATTACK);
    }

    public boolean checkSecondaryAttack(Pet player) {
        return player.getState().equals(Action.SECONDARY_ATTACK);
    }

    public void getBattleResult() {
        double damageDealtToSecondPlayer = getSecondPlayerHealthAtStartOfTurn() - getSecondPlayer().getHealth();
        double damageDealtToFirstPlayer = getFirstPlayerHealthAtStartOfTurn() - getFirstPlayer().getHealth();

        String result = "In this turn " + getFirstPlayer().getName() + " has dealt " + damageDealtToSecondPlayer
                + " damage, and " + getSecondPlayer().getName() + " has dealt " + damageDealtToFirstPlayer + " damage.";
        setBattleFinalString(result);
    }

    public boolean checkIfWon(Pet player) {
        return player.getHealth() <= 0;
    }

    public void addWinningString(Pet player) {
        String result = "\n" + player.getName() + " has won!";
        setBattleFinalString(getBattleFinalString() + result);
    }

    public String goThroughTurn() {
        getOrder();
        //Increase turn by one
        setTurn(getTurn() + 1);
        return getBattleFinalString();
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public int getTurn() {
        return turn;
    }

    public Pet getFirstPlayer() {
        return firstPlayer;
    }

    public Pet getSecondPlayer() {
        return secondPlayer;
    }

    public double getFirstPlayerHealthAtStartOfTurn() {
        return firstPlayerHealthAtStartOfTurn;
    }

    public void setFirstPlayerHealthAtStartOfTurn(double firstPlayerHealthAtStartOfTurn) {
        this.firstPlayerHealthAtStartOfTurn = firstPlayerHealthAtStartOfTurn;
    }

    public double getSecondPlayerHealthAtStartOfTurn() {
        return secondPlayerHealthAtStartOfTurn;
    }

    public void setSecondPlayerHealthAtStartOfTurn(double secondPlayerHealthAtStartOfTurn) {
        this.secondPlayerHealthAtStartOfTurn = secondPlayerHealthAtStartOfTurn;
    }

    public String getBattleFinalString() {
        return battleFinalString;
    }

    public void setBattleFinalString(String battleFinalString) {
        this.battleFinalString = battleFinalString;
    }
}
