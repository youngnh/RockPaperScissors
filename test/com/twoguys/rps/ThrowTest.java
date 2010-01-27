package  com.twoguys.rps;

import static org.junit.Assert.*;

import org.junit.*;

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

    @Test
    public void testRockEqualsRock() {
	assertEquals(new Rock(), new Rock());
	assertEquals(new Paper(), new Paper());
	assertEquals(new Scissors(), new Scissors());
	
	assertFalse(new Rock().equals(new Paper()));
	assertFalse(new Rock().equals(new Scissors()));

	assertFalse(new Paper().equals(new Rock()));
	assertFalse(new Paper().equals(new Scissors()));

	assertFalse(new Scissors().equals(new Rock()));
	assertFalse(new Scissors().equals(new Paper()));
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
