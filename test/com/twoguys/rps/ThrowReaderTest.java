package com.twoguys.rps;

import static org.junit.Assert.*;

import com.twoguys.util.*;
import org.junit.*;

public class ThrowReaderTest {

    @Test
    public void testRReturnsRock() {
	String thrown = "R";
	Throw expected = new Rock();

	ThrowReader throwReader = new ThrowReader();
	Maybe<Throw> actual = throwReader.read(thrown);

	assertEquals(expected, actual.value());
    }

    @Test
    public void testPReturnsPaper() {
	String thrown = "P";
	Throw expected = new Paper();

	ThrowReader throwReader = new ThrowReader();
	Maybe<Throw> actual = throwReader.read(thrown);

	assertEquals(expected, actual.value());
    }

    @Test
    public void testSReturnsScissors() {
	String thrown = "S";
	Throw expected = new Scissors();

	ThrowReader throwReader = new ThrowReader();
	Maybe<Throw> actual = throwReader.read(thrown);

	assertEquals(expected, actual.value());
    }

    @Test
    public void testWrongParsingsReturnNothing() {
	String thrown = "invalid";
	
	ThrowReader throwReader = new ThrowReader();
	Maybe<Throw> actual = throwReader.read(thrown);
	assertEquals(new Nothing(), actual);
    }
}