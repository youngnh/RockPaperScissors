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
    public void testRockIsEqualToRock() {
	Throw rock1 = new Rock();
	Throw rock2 = new Rock();

	assertEquals(rock1, rock2);
    }

    @Test
    public void testPaperIsEqualToPaper() {
	Throw paper1 = new Paper();
	Throw paper2 = new Paper();

	assertEquals(paper1, paper2);
    }

    @Test
    public void testScissorsIsEqualToScissors() {
	Throw scissors1 = new Scissors();
	Throw scissors2 = new Scissors();

	assertEquals(scissors1, scissors2);
    }

    @Test
    public void testNoThrowsAreEqualToEachOther() {
	Throw rock = new Rock();
	Throw paper = new Paper();
	Throw scissors = new Scissors();

	assertFalse(rock.equals(paper));
	assertFalse(rock.equals(scissors));
	assertFalse(paper.equals(scissors));
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
