package com.twoguys.rps;

import static org.junit.Assert.*;

import com.twoguys.util.*;
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

}