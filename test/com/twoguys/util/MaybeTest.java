package com.twoguys.util;

import static org.junit.Assert.*;

import org.junit.*;

public class MaybeTest {

    @Test
    public void testJustHoldsSingleValue() {
	String expected = "jabberwocky";
	Maybe<String> something = new Just<String>(expected);
	String actual = something.value();
	assertEquals(expected, actual);
    }

    @Test
    public void testNothingHoldsNullValue() {
	Maybe<String> something = new Nothing();
	String actual = something.value();
	assertNull(actual);
    }

}