package com.twoguys.rps;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.*;
import org.junit.*;

public class InteractivePlayerTest {

    public final static String P1_PROMPT = "Player 1 Name: ";
    public final static String P2_PROMPT = "Player 2 Name: ";
    private String nate = "Nate";
    private String ben = "Ben";
    private Reader in;
    private Writer out;

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
	Player player2 = new InteractivePlayer(in, out, 2);
	String written = out.toString();
	assertEquals(P1_PROMPT + P2_PROMPT, written);
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
}
