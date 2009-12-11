package  com.twoguys.rps;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class ThrowTest {

    @Test
    public void testRockBeatsScissors() {
	assertBeats(new Rock(), new Scissors());
    }

    @Test
    public void testRockLosesToPaper() {
	assertLosesTo(new Rock(), new Paper());
    }

    public void assertBeats(Throw thrown, Throw other) {
	boolean result = thrown.beats(other);
	assertTrue(result);
    }

    public void assertLosesTo(Throw thrown, Throw other) {
	boolean result = thrown.beats(other);
	assertFalse(result);
    }
}
