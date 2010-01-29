package com.twoguys.rps;

import static org.junit.Assert.*;

import com.twoguys.util.*;
import java.util.*;
import org.junit.*;

public class FirstToTest {

    private WinLogic logic;
    private Player player1;
    private Player player2;

    @Before
    public void setup() {
	player1 = new Player("Darth Vader");
	player2 = new Player("Predator");
    }

    @Test
    public void testBothScoresBelowTargetNoWinner() {
	int to = new Random().nextInt(10);

	logic = new FirstTo(to);

	assertNoWinner(to - 1, to - 1);
	assertNoWinner(to - 5, to - 1);
	assertNoWinner(to - 1, to - 5);
    }

    @Test
    public void testPlayer1WinsIfTiedAtScore() {
	int to = new Random().nextInt(10);
	
	logic = new FirstTo(to);
	
	assertWinnerIs(player1, to, to);
    }

    @Test
    public void testHighestWinsIfBothAboveScore() {
	int to = new Random().nextInt(10);

	logic = new FirstTo(to);

	assertWinnerIs(player1, to + 10, to + 5);
	assertWinnerIs(player2, to + 5, to + 10);
    }

    private void assertNoWinner(int p1Score, int p2Score) {
	Pair<Player, Integer> p1 = new Pair<Player, Integer>(player1, p1Score);
	Pair<Player, Integer> p2 = new Pair<Player, Integer>(player2, p2Score);

	Maybe<Player> winner = logic.determineWinner(p1, p2);

	assertEquals(new Nothing(), winner);
    }

    private void assertWinnerIs(Player expected, int p1Score, int p2Score) {
	Pair<Player, Integer> p1 = new Pair<Player, Integer>(player1, p1Score);
	Pair<Player, Integer> p2 = new Pair<Player, Integer>(player2, p2Score);

	Maybe<Player> winner = logic.determineWinner(p1, p2);

	assertEquals(expected, winner.value());
    }

}