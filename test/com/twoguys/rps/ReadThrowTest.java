package com.twoguys.rps;

import static org.junit.Assert.*;

import com.twoguys.util.*;
import java.util.*;
import org.junit.*;

@Ignore
public class ReadThrowTest {

    @Test
    public void testRReturnsSingleParseOfRock() {
	Read<Throw> reader = new ReadThrow();
	List<Pair<Throw, String>> result = reader.read("R");
	
	assertEquals(1, result.size());
	assertEquals(new Rock(), result.get(0).a);
    }

    @Test
    public void testRReturnsRestOfString() {
	Read<Throw> reader = new ReadThrow();
	String r = "R";
	String rest = "jabberwocky";
	List<Pair<Throw, String>> result = reader.read(r + rest);

	assertEquals(1, result.size());
	assertEquals(new Rock(), result.get(0).a);
	assertEquals(rest, result.get(0).b);
    }
}