import static org.junit.Assert.*;

import java.io.*;
import org.apache.commons.io.*;
import org.junit.*;

public class RockPaperScissorsTest {

    @Test
    public void testNothingOutputIfRPSNotRun() {
	InputStream instream = new ByteArrayInputStream("".getBytes());
	LineNumberReader in = new LineNumberReader(new InputStreamReader(instream));
	ByteArrayOutputStream out = new ByteArrayOutputStream();

	String[] args = { "-jabberwocky" };

	RockPaperScissors rps = new RockPaperScissors(in, new PrintStream(out));

	assertEquals("", out.toString());
    }

    @Test
    public void testDisplaysUsageWithBogusArgs() {
	InputStream instream = new ByteArrayInputStream("".getBytes());
	LineNumberReader in = new LineNumberReader(new InputStreamReader(instream));
	ByteArrayOutputStream out = new ByteArrayOutputStream();

	String[] args = { "-jabberwocky" };

	RockPaperScissors rps = new RockPaperScissors(in, new PrintStream(out));
	rps.run(args);

	String usage = 
	    "usage: \tRockPaperScissors -to x\n" +
	    "       \tRockPaperScissors -to to -by by\n" +
	    "       \tRockPaperScissors -bestof x\n";

	assertEquals(usage, out.toString());
    }

    @Test
    public void testIOExceptionResultsInSomeKindOfOutput() throws Exception {
	InputStream instream = new ByteArrayInputStream("".getBytes());
	LineNumberReader in = new LineNumberReader(new InputStreamReader(instream));
	OutputStream out = new ExceptionProneStream();

	String[] args = {};

	RockPaperScissors rps = new RockPaperScissors(in, new PrintStream(out));
	rps.run(args);

	String actual = out.toString();
	assertNotNull(actual);
	assertTrue(actual.length() > 0);
    }

    @Test
    public void testNoArgumentsResultsInSingleThrowGame() throws Exception {
	InputStream instream = new FileInputStream("data/RockPaperScissorsTest/noargs_game.input");
	LineNumberReader in = new LineNumberReader(new InputStreamReader(instream));
	OutputStream out = new ByteArrayOutputStream();

	String[] args = {};
	RockPaperScissors rps = new RockPaperScissors(in, new PrintStream(out));
	rps.run(args);

	String expected = IOUtils.toString(new FileInputStream("data/RockPaperScissorsTest/noargs_game.expected")).replace("\n", "");
	expected += "\n";

	String actual = out.toString();
	assertEquals(expected, actual);
    }

    @Test
    public void testFirstToGameResultsInBothPlayersPlaying() throws Exception {
	InputStream instream = new FileInputStream("data/RockPaperScissorsTest/firstto_game.input");
	LineNumberReader in = new LineNumberReader(new InputStreamReader(instream));
	OutputStream out = new ByteArrayOutputStream();

	String[] args = { "-to", "3" };
	RockPaperScissors rps = new RockPaperScissors(in, new PrintStream(out));
	rps.run(args);

	String expected = IOUtils.toString(new FileInputStream("data/RockPaperScissorsTest/firstto_game.expected")).replace("\n", "");
	expected += "\n";

	String actual = out.toString();
	assertEquals(expected, actual);
    }

    @Test
    public void testBestOfStopsAfterPlayerWinsMoreThanHalf() throws Exception {
	InputStream instream = new FileInputStream("data/RockPaperScissorsTest/bestof_game.input");
	LineNumberReader in = new LineNumberReader(new InputStreamReader(instream));
	OutputStream out = new ByteArrayOutputStream();

	String[] args = { "-bestof", "5" };
	RockPaperScissors rps = new RockPaperScissors(in, new PrintStream(out));
	rps.run(args);

	String expected = IOUtils.toString(new FileInputStream("data/RockPaperScissorsTest/bestof_game.expected")).replace("\n", "");
	expected += "\n";

	String actual = out.toString();
	assertEquals(expected, actual);
    }

    @Test
    public void testWinByGameContinuesUntilOnePlayerBeatsOtherByMargin() throws Exception {
	InputStream instream = new FileInputStream("data/RockPaperScissorsTest/winby_game.input");
	LineNumberReader in = new LineNumberReader(new InputStreamReader(instream));
	OutputStream out = new ByteArrayOutputStream();

	String[] args = { "-to", "3", "-by", "2" };
	RockPaperScissors rps = new RockPaperScissors(in, new PrintStream(out));
	rps.run(args);

	String expected = IOUtils.toString(new FileInputStream("data/RockPaperScissorsTest/winby_game.expected")).replace("\n", "");
	expected += "\n";

	String actual = out.toString();
	assertEquals(expected, actual);
    }

    @Test
    public void testNateWins() throws Exception {
	InputStream instream = new FileInputStream("data/RockPaperScissorsTest/nate_wins.input");
	LineNumberReader in = new LineNumberReader(new InputStreamReader(instream));
	OutputStream out = new ByteArrayOutputStream();

	String[] args = { };
	RockPaperScissors rps = new RockPaperScissors(in, new PrintStream(out));
	rps.run(args);

	String expected = IOUtils.toString(new FileInputStream("data/RockPaperScissorsTest/nate_wins.expected")).replace("\n", "");
	expected += "\n";

	String actual = out.toString();
	assertEquals(expected, actual);
    }

    class ExceptionProneStream extends OutputStream {

	private boolean crashed = false;
	private ByteArrayOutputStream writer = new ByteArrayOutputStream();

	@Override
	public void close() throws IOException {
	    writer.close();
	}

	@Override
	public void flush() throws IOException {
	    writer.flush();
	}

	@Override
	public void write(int b) {
	    writer.write(b);
	}

	@Override
	public void write(byte[] b, int off, int len) throws IOException {
	    if(!crashed) {
		crashed = true;
		throw new IOException("Guess I'm just crashy...");
	    } else {
		writer.write(b, off, len);
	    }
	}

	public String toString() {
	    return writer.toString();
	}
    }

}