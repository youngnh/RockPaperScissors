package com.twoguys.rps;

import static org.junit.Assert.*;

import java.io.*;
import org.apache.commons.io.*;
import org.junit.*;

public class RockPaperScissorsTest {

    @Test
    public void testNothingOutputIfRPSNotRun() {
	LineNumberReader in = new LineNumberReader(new StringReader(""));
	StringWriter out = new StringWriter();

	String[] args = { "-jabberwocky" };

	RockPaperScissors rps = new RockPaperScissors(in, out);

	assertEquals("", out.toString());
    }

    @Test
    public void testDisplaysUsageWithBogusArgs() {
	LineNumberReader in = new LineNumberReader(new StringReader(""));
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

    @Test
    public void testIOExceptionResultsInSomeKindOfOutput() throws Exception {
	LineNumberReader in = new LineNumberReader(new StringReader(""));
	Writer out = new ExceptionProneWriter();

	String[] args = {};

	RockPaperScissors rps = new RockPaperScissors(in, out);
	rps.run(args);

	String actual = out.toString();
	assertNotNull(actual);
	assertTrue(actual.length() > 0);
    }

    @Test
    public void testNoArgumentsResultsInSingleThrowGame() throws Exception {
	Reader file = new FileReader("data/RockPaperScissorsTest/noargs_game.input");
	LineNumberReader in = new LineNumberReader(file);
	Writer out = new StringWriter();

	String[] args = {};
	RockPaperScissors rps = new RockPaperScissors(in, out);
	rps.run(args);

	String expected = IOUtils.toString(new FileReader("data/RockPaperScissorsTest/noargs_game.expected")).replace("\n", "");

	String actual = out.toString();
	assertEquals(expected, actual);
    }

    @Test
    public void testFirstToGameResultsInBothPlayersPlaying() throws Exception {
	Reader file = new FileReader("data/RockPaperScissorsTest/firstto_game.input");
	LineNumberReader in = new LineNumberReader(file);
	Writer out = new StringWriter();

	String[] args = { "-to", "3" };
	RockPaperScissors rps = new RockPaperScissors(in, out);
	rps.run(args);

	String expected = IOUtils.toString(new FileReader("data/RockPaperScissorsTest/firstto_game.expected")).replace("\n", "");

	String actual = out.toString();
	assertEquals(expected, actual);
    }

    @Test
    public void testBestOfStopsAfterPlayerWinsMoreThanHalf() throws Exception {
	Reader file = new FileReader("data/RockPaperScissorsTest/bestof_game.input");
	LineNumberReader in = new LineNumberReader(file);
	Writer out = new StringWriter();

	String[] args = { "-bestof", "5" };
	RockPaperScissors rps = new RockPaperScissors(in, out);
	rps.run(args);

	String expected = IOUtils.toString(new FileReader("data/RockPaperScissorsTest/bestof_game.expected")).replace("\n", "");

	String actual = out.toString();
	assertEquals(expected, actual);
    }

    @Test
    public void testWinByGameContinuesUntilOnePlayerBeatsOtherByMargin() throws Exception {
	Reader file = new FileReader("data/RockPaperScissorsTest/winby_game.input");
	LineNumberReader in = new LineNumberReader(file);
	Writer out = new StringWriter();

	String[] args = { "-to", "3", "-by", "2" };
	RockPaperScissors rps = new RockPaperScissors(in, out);
	rps.run(args);

	String expected = IOUtils.toString(new FileReader("data/RockPaperScissorsTest/winby_game.expected")).replace("\n", "");

	String actual = out.toString();
	assertEquals(expected, actual);
    }

    @Test
    public void testNateWins() throws Exception {
	Reader file = new FileReader("data/RockPaperScissorsTest/nate_wins.input");
	LineNumberReader in = new LineNumberReader(file);
	Writer out = new StringWriter();

	String[] args = { };
	RockPaperScissors rps = new RockPaperScissors(in, out);
	rps.run(args);

	String expected = IOUtils.toString(new FileReader("data/RockPaperScissorsTest/nate_wins.expected")).replace("\n", "");

	String actual = out.toString();
	assertEquals(expected, actual);
    }

    class ExceptionProneWriter extends Writer {

	private boolean crashed = false;
	private StringWriter writer = new StringWriter();

	@Override
	public void close() throws IOException {
	    writer.close();
	}

	@Override
	public void flush() {
	    writer.flush();
	}

	@Override
	public void write(char[] cbuf, int off, int len) {
	    writer.write(cbuf, off, len);
	}

	@Override
	public void write(String str) throws IOException {
	    if(!crashed) {
		crashed = true;
		throw new IOException("Guess I'm just crashy...");
	    } else {
		writer.write(str);
	    }
	}

	@Override
	public String toString() {
	    return writer.toString();
	}
    }

}