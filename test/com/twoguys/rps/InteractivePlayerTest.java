package com.twoguys.rps;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.*;
import org.junit.*;

public class InteractivePlayerTest {

    public final static String P1_PROMPT = "Player 1 Name: ";
    public final static String P2_PROMPT = "Player 2 Name: ";
    public final static String MOVE_PROMPT = "[R]ock, [P]aper, or [S]cissors? ";
    private String nate = "Nate";
    private String ben = "Ben";
    private Reader in;
    private CharArrayWriter out;

    @Before
    public void setup() {
	in = new StringReader(nate + "\n");
	out = new CharArrayWriter();
    }

    @Test
    public void testPromptsForName() throws Exception {
	Player player = new InteractivePlayer(in, out, 1);

	String written = out.toString();
	assertEquals(P1_PROMPT, written);
    }

    @Test
    public void testPromptsPlayerNumber() throws Exception {
	Player player1 = new InteractivePlayer(in, out, 1);
	String written = out.toString();
	assertEquals(P1_PROMPT, written);

	out.reset();
	Player player2 = new InteractivePlayer(in, out, 2);
	written = out.toString();
	assertEquals(P2_PROMPT, written);
    }

    @Test
    public void testReadsNameFromInput() throws Exception {
	Player player = new InteractivePlayer(in, out, 1);

	assertEquals(-1, in.read());
    }

    @Test
    public void testSetsPlayerName() throws Exception {
	Player player = new InteractivePlayer(in, out, 1);
	assertEquals(nate, player.getName());

	in = new StringReader(ben + "\n");
	player = new InteractivePlayer(in, out, 2);
	assertEquals(ben, player.getName());
    }

    @Test
    public void testPromptsForMove() throws Exception {
	Player player = new InteractivePlayer(in, out, 1);
	out.reset();

	player.getThrow();
	String written = out.toString();
	assertEquals(MOVE_PROMPT, written);
    }

    @Test
    public void testGetThrowReturnsRock() throws Exception {
	in = new StringReader(nate + "\n" + "R" + "\n");

	Player player = new InteractivePlayer(in, out, 1);
	out.reset();

	Throw thrown = player.getThrow();
	assertEquals(Rock.class, thrown.getClass());
    }

    @Test
    public void testGetThrowReturnsPaper() throws Exception {
	in = new StringReader(nate + "\n" + "P" + "\n");

	Player player = new InteractivePlayer(in, out, 1);
	out.reset();

	Throw thrown = player.getThrow();
	assertEquals(Paper.class, thrown.getClass());
    }
}
