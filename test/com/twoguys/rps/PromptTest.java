package com.twoguys.rps;

import static org.junit.Assert.*;

import java.io.*;
import org.junit.*;

public class PromptTest {

    @Test
    public void testPromptWritesMessageToWriter() throws Exception {
	Writer out = new StringWriter();
	String message = "whoa";
	Prompt prompt = new Prompt(out, message);
	prompt.prompt();
	String actual = out.toString();
	assertEquals(message, actual);
    }
}