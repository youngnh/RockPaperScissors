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

    @Test
    public void testPaperBeatsRock() {
	assertBeats(new Paper(), new Rock());
    }

    @Test
    public void testPaperLosesToScissors() {
	assertLosesTo(new Paper(), new Scissors());
    }

    @Test
    public void testScissorsBeatsPaper() {
	assertBeats(new Scissors(), new Paper());
    }

    @Test
    public void testScissorsLosesToRock() {
	assertLosesTo(new Scissors(), new Rock());
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
