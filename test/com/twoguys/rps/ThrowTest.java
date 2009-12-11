package  com.twoguys.rps;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ThrowTest {

    @Test
    public void testRockBeatsScissors() {
	Throw rock = new Rock();
	Throw scissors = new Scissors();
	boolean result = rock.beats(scissors);
	assertTrue(result);
    }
}
