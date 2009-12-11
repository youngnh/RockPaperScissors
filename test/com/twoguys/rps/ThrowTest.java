package  com.twoguys.rps;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class ThrowTest {

    @Test
    public void testRockBeatsScissors() {
	Throw rock = new Rock();
	Throw scissors = new Scissors();
	boolean result = rock.beats(scissors);
	assertTrue(result);
    }

    @Test
    public void testRockLosesToPaper() {
	Throw rock = new Rock();
	Throw paper = new Paper();
	boolean result = rock.beats(paper);
	assertFalse(result);
    }
}
