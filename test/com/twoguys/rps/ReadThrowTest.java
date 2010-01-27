package com.twoguys.rps;

import static org.junit.Assert.*;

import com.twoguys.util.*;
import java.util.*;
import org.junit.*;

public class ReadThrowTest {

    @Test
    public void testRReturnsSingleParseOfRock() {
	Read<Throw> reader = new ReadThrow();
	List<Pair<Throw, String>> result = reader.read("R");
	
	assertEquals(1, result.size());
	assertEquals(new Rock(), result.get(0).a);
    }
}