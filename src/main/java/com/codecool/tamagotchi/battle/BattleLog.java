package com.codecool.tamagotchi.battle;

public class BattleLog {
    private String log;
    private boolean unlockButtons;
    private boolean isGameOver;

    public BattleLog() {
    }

    public BattleLog(String content) {
        this.log = content;
    }

    public String getLog() {
        return log;
    }

    public boolean isUnlockButtons() {
        return unlockButtons;
    }

    public void setUnlockButtons(boolean unlockButtons) {
        this.unlockButtons = unlockButtons;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }
}
