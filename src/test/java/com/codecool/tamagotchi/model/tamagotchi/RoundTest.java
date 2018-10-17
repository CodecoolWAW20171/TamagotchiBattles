package com.codecool.tamagotchi.model.tamagotchi;

import com.codecool.tamagotchi.model.tamagotchi.enumerations.Action;
import com.codecool.tamagotchi.model.tamagotchi.enumerations.Type;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RoundTest {
    private Pet player1;
    private Pet player2;

    private Round round;

    @Before
    public void setUp() throws Exception {
        player1 = new Pet();
        player1.setName("Pet 1");
        player1.setAttack(60);
        player1.setDefence(50);
        player1.setType(Type.PAPER);

        player2 = new Pet();
        player2.setName("Pet 2");
        player2.setAttack(20);
        player2.setDefence(50);
        player2.setType(Type.ROCK);
    }

    @Test
    public void setOrderAttack() {
        round = new Round(player1, player2);
        player1.setSpeed(80);
        player2.setSpeed(70);
        player1.setState(Action.ATTACK);
        player2.setState(Action.ATTACK);

        round.setOrderAttack(player1, player2);
        Assert.assertSame(player1, round.getFirstAttacker());
        Assert.assertSame(player2, round.getSecondAttacker());

        player1.setSpeed(80);
        player2.setSpeed(90);
        player1.setState(Action.ATTACK);
        player2.setState(Action.ATTACK);

        round.setOrderAttack(player1, player2);
        Assert.assertSame(player2, round.getFirstAttacker());
        Assert.assertSame(player1, round.getSecondAttacker());


        player1.setSpeed(80);
        player2.setSpeed(80);
        player1.setState(Action.ATTACK);
        player2.setState(Action.ATTACK);

        double luckModifier = round.calculateLuckModifier();
        round.setLuckModifier(luckModifier);
        round.setOrderAttack(player1, player2);
        System.out.println(round.getLuckModifier());
        if(luckModifier > 1) {
            Assert.assertSame(round.getFirstAttacker(), player1);
            Assert.assertSame(round.getSecondAttacker(), player2);
        } else {
            Assert.assertSame(round.getFirstAttacker(), player2);
            Assert.assertSame(round.getSecondAttacker(), player1);
        }

        player1.setSpeed(80);
        player2.setSpeed(70);
        player1.setState(Action.DEFEND);
        player2.setState(Action.ATTACK);
        round.setOrderAttack(player1, player2);
        Assert.assertSame(round.getFirstAttacker(), player2);
        Assert.assertSame(round.getSecondAttacker(), player1);
    }

    @Test
    public void fight() {
        round = new Round(player1, player2);
        player1.setSpeed(90);
        player2.setSpeed(80);

        player1.setState(Action.ATTACK);
        player2.setState(Action.ATTACK);

        round = new Round(player1, player2);
        round.fight();

        player1.setState(Action.ATTACK);
        player2.setState(Action.ATTACK);
        round = new Round(player1, player2);
        round.fight();

        player1.setState(Action.ATTACK);
        player2.setState(Action.ATTACK);
        round = new Round(player1, player2);
        round.fight();

        player1.setState(Action.ATTACK);
        player2.setState(Action.DEFEND);
        round = new Round(player1, player2);
        round.fight();

        player1.setState(Action.ATTACK);
        player2.setState(Action.ATTACK);
        round = new Round(player1, player2);
        round.fight();
    }

    @Test
    public void compareSpeed() {
        player1.setSpeed(80);
        player2.setSpeed(70);
        round = new Round(player1, player2);
        round.compareSpeed(player1, player2);
        Assert.assertSame(player1, round.getFirstAttacker());

        player1.setSpeed(60);
        player2.setSpeed(70);
        round.compareSpeed(player1, player2);
        Assert.assertSame(player2, round.getFirstAttacker());
    }

}