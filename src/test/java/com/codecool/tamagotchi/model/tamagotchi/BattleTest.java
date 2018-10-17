package com.codecool.tamagotchi.model.tamagotchi;

import com.codecool.tamagotchi.model.tamagotchi.enumerations.Action;
import com.codecool.tamagotchi.model.tamagotchi.enumerations.Type;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BattleTest {

    private Pet player1;
    private Pet player2;

    private Battle battle;

    @Before
    public void setUp() throws Exception {
        player1 = new Pet();
        player1.setName("Pet 1");
        player1.setAttack(50);
        player1.setDefence(50);
        player1.setSpeed(70);
        player1.setType(Type.ROCK);

        player2 = new Pet();
        player2.setName("Pet 2");
        player2.setAttack(50);
        player2.setDefence(50);
        player2.setSpeed(60);
        player2.setType(Type.PAPER);
    }
    @Test
    public void startBattle() {
        player1.setState(Action.DEFEND);
        player2.setState(Action.DEFEND);
        battle = new Battle(player1, player2);
        battle.startBattle();
    }
}