package com.twoguys.rps;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.*;
import org.junit.*;

public class InteractivePlayerTest {

    public final static String P1_PROMPT = "Player 1 Name: ";

    @Test
    public void testPromptForName() {
	InputStream in = mock(InputStream.class);
	OutputStream out = new ByteArrayOutputStream();

	Player player = new InteractivePlayer(in, out);

	String written = out.toString();
	assertEquals(P1_PROMPT, written);
    }
}
