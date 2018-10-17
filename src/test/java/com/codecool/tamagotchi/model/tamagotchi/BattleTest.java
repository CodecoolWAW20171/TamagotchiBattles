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
        player1.setAttack(70);
        player1.setDefence(80);
        player1.setSpeed(50);
        player1.setType(Type.ROCK);

        player2 = new Pet();
        player2.setName("Pet 2");
        player2.setAttack(60);
        player2.setDefence(80);
        player2.setSpeed(60);
        player2.setType(Type.PAPER);
    }

    @Test
    public void startBattle() {
        battle = new Battle(player1, player2);

        player1.setState(Action.ATTACK);
        player2.setState(Action.DEFEND);
        battle.startBattle();
        Assert.assertEquals(100, player1.getHealth(), 0);
        Assert.assertEquals(-7.5, player2.getHealth(), 0.1);

        player1.setState(Action.SECONDARY_ATTACK);
        player2.setState(Action.DEFEND);
        battle.startBattle();

    }
}