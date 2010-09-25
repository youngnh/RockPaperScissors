package com.twoguys.rps;

import static org.junit.Assert.*;

import com.twoguys.util.*;
import java.util.*;
import org.junit.*;

public abstract class FirstToAbstractTest {

    private WinLogic logic;
    private Player player1;
    private Player player2;
    private int to;

    public abstract WinLogic getLogic(int to);

    public int getTo() {
	return new Random().nextInt(10) + 15;
    }

    @Before
    public void setup() {
	to = getTo();
	logic = getLogic(to);
	player1 = new Player("Darth Vader");
	player2 = new Player("Predator");
    }

    @Test
    public void testBothScoresBelowTargetNoWinner() {
	assertNoWinner(to - 1, to - 1);
	assertNoWinner(to - 5, to - 1);
	assertNoWinner(to - 1, to - 5);
    }

    @Test
    public void testPlayer1WinsIfTiedAtScore() {
	assertWinnerIs(player1, to, to);
    }

    @Test
    public void testHighestWinsIfBothAboveScore() {
	assertWinnerIs(player1, to + 10, to + 5);
	assertWinnerIs(player2, to + 5, to + 10);
    }

    private void assertNoWinner(int p1Score, int p2Score) {
	Pair<Player, Integer> p1 = new Pair<Player, Integer>(player1, p1Score);
	Pair<Player, Integer> p2 = new Pair<Player, Integer>(player2, p2Score);

	Maybe<Player> winner = logic.determineWinner(p1, p2);

	assertEquals("A winner was found with score " + 
		     p1Score + " - " + p2Score + 
		     " to " + to + " expected Nothing", new Nothing(), winner);
    }

    private void assertWinnerIs(Player expected, int p1Score, int p2Score) {
	Pair<Player, Integer> p1 = new Pair<Player, Integer>(player1, p1Score);
	Pair<Player, Integer> p2 = new Pair<Player, Integer>(player2, p2Score);

	Maybe<Player> winner = logic.determineWinner(p1, p2);

	assertEquals(expected, winner.value());
    }

}