package com.twoguys.rps;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.Test;

public class GameTest {

    @Test
    public void testPlayerWithBetterThrowWins() {
	String nate = "Nate";
	String ben = "Ben";
	
	Player p1 = mock(Player.class);
	Player p2 = mock(Player.class);

	when(p1.getName()).thenReturn(nate);
	when(p2.getName()).thenReturn(ben);
	when(p1.getMove()).thenReturn(new Rock());
	when(p2.getMove()).thenReturn(new Paper());

	Game game = new Game(p1, p2);
	Player winner = game.play();

	assertEquals(ben, winner.getName());
    }
    
}
