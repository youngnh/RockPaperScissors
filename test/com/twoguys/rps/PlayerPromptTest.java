package com.twoguys.rps;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.*;
import org.junit.*;

public class PlayerPromptTest {

    @Test
    public void testPlayerPromptPrintsMessage() throws Exception {
	String expected = "Player 1 Name: ";
	LineNumberReader in = new LineNumberReader(new StringReader("jabberwocky"));
	StringWriter out = new StringWriter();

	PlayerPrompt prompt = new PlayerPrompt(in, out, 1);
	prompt.prompt();

	assertEquals(expected, out.toString());
    }

     @Test
    public void testPlayerHasNameGivenInInput() throws Exception {
	String expected = "Player 1 Name: ";
	String input = "jabberwocky";
	LineNumberReader in = new LineNumberReader(new StringReader(input));
	StringWriter out = new StringWriter();

	PlayerPrompt prompt = new PlayerPrompt(in, out, 1);
	Player player = prompt.prompt();

	assertEquals(input, player.getName());
    }   

}