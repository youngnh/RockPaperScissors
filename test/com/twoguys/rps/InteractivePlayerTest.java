package com.twoguys.rps;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.*;
import org.junit.*;

public class InteractivePlayerTest {

    public final static String P1_PROMPT = "Player 1 Name: ";
    private String nate = "Nate";
    private Reader in;
    private Writer out;

    @Before
    public void setup() {
	in = new StringReader(nate + "\n");
	out = new CharArrayWriter();
    }

    @Test
    public void testPromptsForName() throws Exception {
	Player player = new InteractivePlayer(in, out);

	String written = out.toString();
	assertEquals(P1_PROMPT, written);
    }

    @Test
    public void testReadsNameFromInput() throws Exception {
	Player player = new InteractivePlayer(in, out);

	assertEquals(-1, in.read());
    }	
}
