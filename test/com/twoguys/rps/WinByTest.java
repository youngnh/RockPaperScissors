package com.twoguys.rps;

import static org.junit.Assert.*;

import com.twoguys.util.*;
import java.util.*;
import org.junit.*;

public class WinByTest {

    @Test
    public void testNeitherAtScoreNoWinner() {
	Player player1 = new Player("Darth Vader");
	Player player2 = new Player("Predator");

	Pair<Player, Integer> p1 = new Pair<Player, Integer>(player1, 0);
	Pair<Player, Integer> p2 = new Pair<Player, Integer>(player2, 0);
	
	WinLogic logic = new WinBy(5, 2);
	Maybe<Player> winner = logic.determineWinner(p1, p2);

	assertEquals(new Nothing(), winner);
    }

    @Test
    public void testPlayer1AtScoreAndByMarginFirstWins() {
	Player player1 = new Player("Darth Vader");
	Player player2 = new Player("Predator");

	Pair<Player, Integer> p1 = new Pair<Player, Integer>(player1, 5);
	Pair<Player, Integer> p2 = new Pair<Player, Integer>(player2, 3);

	WinLogic logic = new WinBy(5, 2);
	Maybe<Player> winner = logic.determineWinner(p1, p2);

	assertEquals(player1, winner.value());
    }

    @Test
    public void testPlayer1AtScoreButNotByMarginNoWinner() {
	Player player1 = new Player("Darth Vader");
	Player player2 = new Player("Predator");

	Pair<Player, Integer> p1 = new Pair<Player, Integer>(player1, 5);
	Pair<Player, Integer> p2 = new Pair<Player, Integer>(player2, 4);

	WinLogic logic = new WinBy(5, 2);
	Maybe<Player> winner = logic.determineWinner(p1, p2);

	assertEquals(new Nothing(), winner);
    }

    @Test
    public void testPlayer2WithScoreByMarginWins() {
	Player player1 = new Player("Darth Vader");
	Player player2 = new Player("Predator");

	Pair<Player, Integer> p1 = new Pair<Player, Integer>(player1, 3);
	Pair<Player, Integer> p2 = new Pair<Player, Integer>(player2, 5);

	WinLogic logic = new WinBy(5, 2);
	Maybe<Player> winner = logic.determineWinner(p1, p2);

	assertEquals(player2, winner.value());
    }

    @Test
    public void testPlayer1WinsIfGreaterThanScore() {
	Player player1 = new Player("Darth Vader");
	Player player2 = new Player("Predator");

	Pair<Player, Integer> p1 = new Pair<Player, Integer>(player1, 6);
	Pair<Player, Integer> p2 = new Pair<Player, Integer>(player2, 1);

	WinLogic logic = new WinBy(5, 2);
	Maybe<Player> winner = logic.determineWinner(p1, p2);

	assertEquals(player1, winner.value());
    }


    @Test
    public void testPlayer2WinsIfGreaterThanScore() {
	Player player1 = new Player("Darth Vader");
	Player player2 = new Player("Predator");

	Pair<Player, Integer> p1 = new Pair<Player, Integer>(player1, 1);
	Pair<Player, Integer> p2 = new Pair<Player, Integer>(player2, 6);

	WinLogic logic = new WinBy(5, 2);
	Maybe<Player> winner = logic.determineWinner(p1, p2);

	assertEquals(player2, winner.value());
    }

    @Test
    public void testNoWinnerWhenTiedAndMargin() {
	Player player1 = new Player("Darth Vader");
	Player player2 = new Player("Predator");

	Pair<Player, Integer> p1 = new Pair<Player, Integer>(player1, 5);
	Pair<Player, Integer> p2 = new Pair<Player, Integer>(player2, 5);

	WinLogic logic = new WinBy(5, 2);
	Maybe<Player> winner = logic.determineWinner(p1, p2);

	assertEquals(new Nothing(), winner);
    }

    @Test
    public void testPlayer1WinsWhenTiedAndNoMargin() {
	Player player1 = new Player("Darth Vader");
	Player player2 = new Player("Predator");

	Pair<Player, Integer> p1 = new Pair<Player, Integer>(player1, 5);
	Pair<Player, Integer> p2 = new Pair<Player, Integer>(player2, 5);

	WinLogic logic = new WinBy(5, 0);
	Maybe<Player> winner = logic.determineWinner(p1, p2);

	assertEquals(player1, winner.value());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testTo0ThrowsIllegalArgumentException() {
	new WinBy(0, 0);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testToANegativeNumberThrowsIllegalArgumentException() {
	Random random = new Random();
	int num = random.nextInt();
	while(num == 0) {
	    num = random.nextInt();
	}
	if(num > 0) {
	    num *= -1;
	}
	new WinBy(num, 2);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testByANegativeNumberThrowsIllegalArgumentException() {
	Random random = new Random();
	int num = random.nextInt();
	while(num == 0) {
	    num = random.nextInt();
	}
	if(num > 0) {
	    num *= -1;
	}
	new WinBy(5, num);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testByANumberGreaterThanToThrowsIllegalArgumentException() {
	Random random = new Random();
	int num = random.nextInt();
	while(num == 0) {
	    num = random.nextInt();
	}
	if(num < 0) {
	    num *= -1;
	}
	new WinBy(num, num+2);
    }
}