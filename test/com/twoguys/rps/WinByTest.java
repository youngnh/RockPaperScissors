package com.twoguys.rps;

import static org.junit.Assert.*;

import com.twoguys.util.*;
import java.util.*;
import org.junit.*;

public class WinByTest {

    private WinLogic logic;
    private Player player1;
    private Player player2;
    private int to;
    private int by;

    public WinLogic getLogic(int to, int by) {
	return new WinBy(to, by);
    }

    public int getTo(int by) {
	return new Random().nextInt(10) + by;
    }

    @Before
    public void setup() {
	player1 = new Player("Darth Vader");
	player2 = new Player("Predator");
    }

    @Test
    public void testBothScoresBelowTargetNoWinner() {
	by = 10;
	to = getTo(by);
	logic = getLogic(to, by);
	assertNoWinner(to - 1, to - 1);
	assertNoWinner(to - 5, to - 1);
	assertNoWinner(to - 1, to - 5);
	assertNoWinner(to - 1 - by, to - 1);
	assertNoWinner(to - 1, to - 1 - by);
    }

    @Test
    public void testNoWinnerIfTied() {
	by = 10;
	to = getTo(by);
	logic = getLogic(to, by);
	assertNoWinner(to, to);
    }

    @Test
    public void testNoWinnerIfAboveScoreAndNotWinningEnough() {
	by = 10;
	to = getTo(by);
	logic = getLogic(to, by);
	assertNoWinner(to + 5, to + 3);
	assertNoWinner(to + 3, to + 5);
    }

    @Test
    public void testPlayer1WinsIfTiedAndByIs0() {
	by = 0;
	to = getTo(by);
	logic = getLogic(to, by);
	assertWinnerIs(player1, to, to);
    }

    @Test
    public void testHighestWinsIfBothAboveScoreAndWinningBy() {
	by = 10;
	to = getTo(by);
	logic = getLogic(to, by);
	assertWinnerIs(player1, to + 5 + by, to + 2);
	assertWinnerIs(player2, to + 2, to + 5 + by);
	assertWinnerIs(player1, to + by, to);
	assertWinnerIs(player2, to, to + by);
    }

    private void assertNoWinner(int p1Score, int p2Score) {
	Pair<Player, Integer> p1 = new Pair<Player, Integer>(player1, p1Score);
	Pair<Player, Integer> p2 = new Pair<Player, Integer>(player2, p2Score);

	Maybe<Player> winner = logic.determineWinner(p1, p2);

	assertEquals("A winner was found with score " + 
		     p1Score + " - " + p2Score + 
		     " to " + to + " by " + by + " expected Nothing", new Nothing(), winner);
    }

    private void assertWinnerIs(Player expected, int p1Score, int p2Score) {
	Pair<Player, Integer> p1 = new Pair<Player, Integer>(player1, p1Score);
	Pair<Player, Integer> p2 = new Pair<Player, Integer>(player2, p2Score);

	Maybe<Player> winner = logic.determineWinner(p1, p2);

	assertEquals(expected, winner.value());
    }

}