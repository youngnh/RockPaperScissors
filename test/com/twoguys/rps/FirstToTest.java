package com.twoguys.rps;

import static org.junit.Assert.*;

import com.twoguys.util.*;
import java.util.*;
import org.junit.*;

public class FirstToTest {

    @Test
    public void testNeitherAtScoreNoWinner() {
	Player player1 = new Player("Darth Vader");
	Player player2 = new Player("Predator");

	Pair<Player, Integer> p1 = new Pair<Player, Integer>(player1, 0);
	Pair<Player, Integer> p2 = new Pair<Player, Integer>(player2, 0);
	
	WinLogic logic = new FirstTo(5);
	Maybe<Player> winner = logic.determineWinner(p1, p2);

	assertEquals(new Nothing(), winner);
    }

    @Test
    public void testPlayer1AtScoreFirstWins() {
	Player player1 = new Player("Darth Vader");
	Player player2 = new Player("Predator");

	Pair<Player, Integer> p1 = new Pair<Player, Integer>(player1, 5);
	Pair<Player, Integer> p2 = new Pair<Player, Integer>(player2, 1);

	WinLogic logic = new FirstTo(5);
	Maybe<Player> winner = logic.determineWinner(p1, p2);

	assertEquals(player1, winner.value());
    }

    @Test
    public void testPlayer2WithScoreWins() {
	Player player1 = new Player("Darth Vader");
	Player player2 = new Player("Predator");

	Pair<Player, Integer> p1 = new Pair<Player, Integer>(player1, 1);
	Pair<Player, Integer> p2 = new Pair<Player, Integer>(player2, 5);

	WinLogic logic = new FirstTo(5);
	Maybe<Player> winner = logic.determineWinner(p1, p2);

	assertEquals(player2, winner.value());
    }

    @Test
    public void testPlayer1WinsIfGreaterThanScore() {
	Player player1 = new Player("Darth Vader");
	Player player2 = new Player("Predator");

	Pair<Player, Integer> p1 = new Pair<Player, Integer>(player1, 6);
	Pair<Player, Integer> p2 = new Pair<Player, Integer>(player2, 1);

	WinLogic logic = new FirstTo(5);
	Maybe<Player> winner = logic.determineWinner(p1, p2);

	assertEquals(player1, winner.value());
    }


    @Test
    public void testPlayer2WinsIfGreaterThanScore() {
	Player player1 = new Player("Darth Vader");
	Player player2 = new Player("Predator");

	Pair<Player, Integer> p1 = new Pair<Player, Integer>(player1, 1);
	Pair<Player, Integer> p2 = new Pair<Player, Integer>(player2, 6);

	WinLogic logic = new FirstTo(5);
	Maybe<Player> winner = logic.determineWinner(p1, p2);

	assertEquals(player2, winner.value());
    }

    @Test
    public void testPlayer1WinsWhenTied() {
	Player player1 = new Player("Darth Vader");
	Player player2 = new Player("Predator");

	Pair<Player, Integer> p1 = new Pair<Player, Integer>(player1, 5);
	Pair<Player, Integer> p2 = new Pair<Player, Integer>(player2, 5);

	WinLogic logic = new FirstTo(5);
	Maybe<Player> winner = logic.determineWinner(p1, p2);

	assertEquals(player1, winner.value());
    }

    @Test(expected=IllegalArgumentException.class)
    public void test0ThrowsIllegalArgumentException() {
	new FirstTo(0);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testNegativeNumbersThrowsIllegalArgumentException() {
	Random random = new Random();
	int num = random.nextInt();
	while(num == 0) {
	    num = random.nextInt();
	}
	if(num > 0) {
	    num *= -1;
	}
	new FirstTo(num);
    }
}