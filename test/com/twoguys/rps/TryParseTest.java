package com.twoguys.rps;

import static org.junit.Assert.*;

import com.twoguys.util.*;
import java.util.*;
import org.junit.*;

public class TryParseTest {

    @Test
    public void testEmptyListReturnsEmptyParses() {
	List<Pair<String, Throw>> tries = new ArrayList<Pair<String, Throw>>();

	TryParse tryParse = new TryParse(tries);
	List<Pair<Throw, String>> result = tryParse.parse();

	assertEquals(0, result.size());
    }
}