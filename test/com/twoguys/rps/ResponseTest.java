package com.twoguys.rps;

import static org.junit.Assert.*;

import java.io.*;
import org.junit.*;

public class ResponseTest {

    @Test
    public void testResponseReadsToFirstNewline() throws Exception {
	String message = "because the night";
	Reader in = new StringReader(message + "\nbelongs to us");
	Response response = new Response(in);
	String actual = response.read();
	assertEquals(message, actual);
    }

    @Test
    public void testResponseReadsUntilEOF() throws Exception {
	String message = "because the night";
	Reader in = new StringReader(message);
	Response response = new Response(in);
	String actual = response.read();
	assertEquals(message, actual);
    }
}