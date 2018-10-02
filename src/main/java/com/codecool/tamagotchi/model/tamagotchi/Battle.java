package com.codecool.tamagotchi.model.tamagotchi;

public class Battle {
    private Pet firstPlayer;
    private Pet secondPlayer;
    private int turn = 1;

    public Battle(Pet firstPlayer, Pet secondPlayer) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
    }

    public String nextTurn() {
        return "";
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
}
