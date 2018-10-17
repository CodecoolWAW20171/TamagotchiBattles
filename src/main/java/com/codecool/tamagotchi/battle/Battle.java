package com.codecool.tamagotchi.battle;

import com.codecool.tamagotchi.pet.Pet;
import com.codecool.tamagotchi.enumerations.Action;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Random;

class Battle {
    private Pet firstPlayer;
    private Pet secondPlayer;
    private double firstPlayerHealthAtStartOfTurn;
    private double secondPlayerHealthAtStartOfTurn;
    // Calculate turns for battle to view information in user friendly way, starting from 1
    private int turn = 1;
    private String battleFinalString;
    private boolean isGameOver;

    private void getOrder() {
        firstPlayerHealthAtStartOfTurn = getFirstPlayer().getHealth();
        secondPlayerHealthAtStartOfTurn = getSecondPlayer().getHealth();
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

    private void makeAMove(Pet playerOne, Pet playerTwo) {
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
                setBattleResult();
            }
        } else {
            playerOne.setExp((int) (playerOne.getExp() + playerOne.getHealth()));
            setBattleResult();
            addWinningString(playerOne);
        }
    }

    private boolean checkPrimaryAttack(Pet player) {
        return player.getState().equals(Action.ATTACK);
    }

    private boolean checkSecondaryAttack(Pet player) {
        return player.getState().equals(Action.SECONDARY_ATTACK);
    }

    private void setBattleResult() {
        double damageDealtToSecondPlayer = secondPlayerHealthAtStartOfTurn - getSecondPlayer().getHealth();
        double damageDealtToFirstPlayer = firstPlayerHealthAtStartOfTurn - getFirstPlayer().getHealth();

        battleFinalString = "Round " + turn + "\n" +
                getFirstPlayer().getName() + " has dealt " + damageDealtToSecondPlayer+ " damage, and " +
                getSecondPlayer().getName() + " has dealt " + damageDealtToFirstPlayer + " damage.";
    }

    private boolean checkIfWon(Pet player) {
        if(player.getHealth() <= 0) { isGameOver = true; }
        return isGameOver;
    }

    private void addWinningString(Pet player) {
        String result = "\n" + player.getName() + " has won!";
        battleFinalString = battleFinalString + result;
    }

    private void goThroughTurn() {
        getOrder();
        //Increase turn by one
        turn++;
        firstPlayer.setState(null);
        secondPlayer.setState(null);
    }

    void addPlayer(Pet pet) {
        if (firstPlayer == null) {
            firstPlayer = pet;
        } else if (secondPlayer == null) {
            secondPlayer = pet;
        }
    }

    private Pet getFirstPlayer() {
        return firstPlayer;
    }

    private Pet getSecondPlayer() {
        return secondPlayer;
    }

    BattleLog setPlayerAction(String username, String message) {
        Action action = setActionFromString(message);
        BattleLog battleLog = new BattleLog();

        if (firstPlayer != null && secondPlayer != null) {
            if (username.equals(firstPlayer.getName()))
                firstPlayer.setState(action);
            if (username.equals(secondPlayer.getName()))
                secondPlayer.setState(action);

            if (firstPlayer.getState() != null && secondPlayer.getState() != null) {
                goThroughTurn();
                battleLog.setUnlockButtons(true);
                battleLog.setGameOver(isGameOver);
                battleLog.setLog(battleFinalString);
                return battleLog;
            }
        }
        battleLog.setLog(username + " set up his action");
        return battleLog;
    }

    private Action setActionFromString(String action) {
        switch (action) {
            case "attack": return Action.ATTACK;
            case "special": return Action.SECONDARY_ATTACK;
            case "defend": return Action.DEFEND;
            case "evade": return Action.EVADE;
            default: return null;
        }
    }

}
