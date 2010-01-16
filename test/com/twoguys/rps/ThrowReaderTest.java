package com.twoguys.rps;

import static org.junit.Assert.*;

import org.junit.*;

public class ThrowReaderTest {

    @Test
    public void testRReturnsRock() {
	String thrown = "R";
	Throw expected = new Rock();

	ThrowReader throwReader = new ThrowReader();
	Throw actual = throwReader.read(thrown);

	assertEquals(expected, actual);
    }

    @Test
    public void testPReturnsPaper() {
	String thrown = "P";
	Throw expected = new Paper();

	ThrowReader throwReader = new ThrowReader();
	Throw actual = throwReader.read(thrown);

	assertEquals(expected, actual);
    }

}