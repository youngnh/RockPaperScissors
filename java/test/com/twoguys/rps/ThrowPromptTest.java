package com.twoguys.rps;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.*;
import org.junit.*;

public class ThrowPromptTest {

    @Test
    public void testThrowPromptPrintsMessage() throws Exception {
	String expected = "[R]ock, [P]aper, or [S]cissors? ";
	InputStream instream = new ByteArrayInputStream("R".getBytes());
	LineNumberReader in = new LineNumberReader(new InputStreamReader(instream));
	ByteArrayOutputStream out = new ByteArrayOutputStream();

	ThrowPrompt prompt = new ThrowPrompt(in, new PrintStream(out));
	prompt.prompt();

	assertEquals(expected, out.toString());
    }

    @Test
    public void testThrowPromptReturnsParsedThrows() throws Exception {
	String expected = "Player 1 Name: ";
	String input = "R\nP\nS";
	InputStream instream = new ByteArrayInputStream(input.getBytes());
	LineNumberReader in = new LineNumberReader(new InputStreamReader(instream));
	ByteArrayOutputStream out = new ByteArrayOutputStream();

	ThrowPrompt prompt = new ThrowPrompt(in, new PrintStream(out));
	Throw throw1 = prompt.prompt();
	Throw throw2 = prompt.prompt();
	Throw throw3 = prompt.prompt();

	assertEquals(new Rock(), throw1);
	assertEquals(new Paper(), throw2);
	assertEquals(new Scissors(), throw3);
    }   

}