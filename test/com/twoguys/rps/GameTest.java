package com.twoguys.rps;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class GameTest {

    private String nate = "Nate";
    private String ben = "Ben";
	
    private Player p1;
    private Player p2;

    @Before
    public void setup() {
	p1 = mock(Player.class);
	p2 = mock(Player.class);

	when(p1.getName()).thenReturn(nate);
	when(p2.getName()).thenReturn(ben);
    }

    @Test
    public void testPaperPlayerBeatsRock() {
	when(p1.getThrow()).thenReturn(new Rock());
	when(p2.getThrow()).thenReturn(new Paper());

	Game game = new Game(p1, p2);
	Player winner = game.play();

	assertEquals(ben, winner.getName());
    }
    
    @Test
    public void testRockPlayerBeatsScissors() {
	when(p1.getThrow()).thenReturn(new Rock());
	when(p2.getThrow()).thenReturn(new Scissors());

	Game game = new Game(p1, p2);
	Player winner = game.play();

	assertEquals(nate, winner.getName());
    }

}
