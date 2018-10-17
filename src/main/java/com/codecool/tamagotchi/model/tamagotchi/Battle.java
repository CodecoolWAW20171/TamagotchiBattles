package com.codecool.tamagotchi.model.tamagotchi;

public class Battle {

    private Pet player1;
    private Pet player2;

    private Round round;
    private int roundNumber;

    public Battle(Pet player1, Pet player2) {
        this.player1 = player1;
        this.player2 = player2;
        roundNumber = 1;
    }

    public void startBattle() {
        if(isNextRoundPossible()) {
            round = new Round(player1, player2);
            round.fight();
        } else {
            System.out.println(endBattle());
        }
    }

    private boolean isNextRoundPossible() {
        if(player1.getHealth() <= 0 || player2.getHealth() <=0 ) {
            return false;
        }
        return true;
    }

    private String endBattle(){
        return "End Battle";
    }

}
