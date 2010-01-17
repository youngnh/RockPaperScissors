package com.twoguys.rps;

import static org.junit.Assert.*;

import com.twoguys.util.*;
import org.junit.*;

public class BestOfTest {

    @Test
    public void testWinnerHas2ForBestOf3() {
	Player player1 = new Player("Darth Vader");
	Player player2 = new Player("Predator");

	Pair<Player, Integer> p1 = new Pair<Player, Integer>(player1, 2);
	Pair<Player, Integer> p2 = new Pair<Player, Integer>(player2, 0);
	
	WinLogic logic = new BestOf(3);
	Maybe<Player> winner = logic.determineWinner(p1, p2);

	assertEquals(player1, winner.value());
    }

    @Test
    public void testNoWinnerBeforeEitherHasScoredEnough() {
	Player player1 = new Player("Darth Vader");
	Player player2 = new Player("Predator");

	Pair<Player, Integer> p1 = new Pair<Player, Integer>(player1, 1);
	Pair<Player, Integer> p2 = new Pair<Player, Integer>(player2, 1);
	
	WinLogic logic = new BestOf(3);
	Maybe<Player> winner = logic.determineWinner(p1, p2);

	assertEquals(new Nothing(), winner);
    }

}