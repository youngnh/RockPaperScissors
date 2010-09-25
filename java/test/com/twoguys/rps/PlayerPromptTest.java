package com.twoguys.rps;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.*;
import org.junit.*;

public class PlayerPromptTest {

    @Test
    public void testPlayerPromptPrintsMessage() throws Exception {
	String expected = "Player 1 Name: ";
	InputStream instream = new ByteArrayInputStream("jabberwocky".getBytes());
	LineNumberReader in = new LineNumberReader(new InputStreamReader(instream));
	ByteArrayOutputStream out = new ByteArrayOutputStream();

	PlayerPrompt prompt = new PlayerPrompt(in, new PrintStream(out), 1);
	prompt.prompt();

	assertEquals(expected, out.toString());
    }

     @Test
    public void testPlayerHasNameGivenInInput() throws Exception {
	String expected = "Player 1 Name: ";
	String input = "jabberwocky";
	InputStream instream = new ByteArrayInputStream(input.getBytes());
	LineNumberReader in = new LineNumberReader(new InputStreamReader(instream));
	ByteArrayOutputStream out = new ByteArrayOutputStream();

	PlayerPrompt prompt = new PlayerPrompt(in, new PrintStream(out), 1);
	Player player = prompt.prompt();

	assertEquals(input, player.getName());
    }   

}