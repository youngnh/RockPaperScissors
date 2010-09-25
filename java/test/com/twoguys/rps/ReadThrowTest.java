package com.twoguys.rps;

import static org.junit.Assert.*;

import com.twoguys.util.*;
import java.util.*;
import org.junit.*;

public class ReadThrowTest {

    @Test
    public void testRReturnsParseAndRest() {
	Read<Throw> reader = new ReadThrow();
	String r = "R";
	String rest = "jabberwocky";
	List<Pair<Throw, String>> result = reader.read(r + rest);

	assertEquals(1, result.size());
	assertEquals(new Rock(), result.get(0).a);
	assertEquals(rest, result.get(0).b);
    }

    @Test
    public void testPReturnsParseAndRest() {
	Read<Throw> reader = new ReadThrow();
	String r = "P";
	String rest = "jabberwocky";
	List<Pair<Throw, String>> result = reader.read(r + rest);

	assertEquals(1, result.size());
	assertEquals(new Paper(), result.get(0).a);
	assertEquals(rest, result.get(0).b);
    }

    @Test
    public void testRReturnsRestOfString() {
	Read<Throw> reader = new ReadThrow();
	String r = "S";
	String rest = "jabberwocky";
	List<Pair<Throw, String>> result = reader.read(r + rest);

	assertEquals(1, result.size());
	assertEquals(new Scissors(), result.get(0).a);
	assertEquals(rest, result.get(0).b);
    }
}