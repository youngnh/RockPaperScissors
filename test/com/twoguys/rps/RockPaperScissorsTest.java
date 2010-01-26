package com.twoguys.rps;

import static org.junit.Assert.*;

import java.io.*;
import org.junit.*;

public class RockPaperScissorsTest {

    @Test
    public void testDisplaysUsageWithBogusArgs() {
	StringReader in = new StringReader("");
	StringWriter out = new StringWriter();

	String[] args = { "-jabberwocky" };

	RockPaperScissors rps = new RockPaperScissors(in, out);
	rps.run(args);

	String usage = 
	    "usage: \tRockPaperScissors -to x\n" +
	    "       \tRockPaperScissors -to to -by by\n" +
	    "       \tRockPaperScissors -bestof x\n";

	assertEquals(usage, out.toString());
    }

}