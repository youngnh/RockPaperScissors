package com.twoguys.rps;

import static org.junit.Assert.*;

import com.twoguys.util.*;
import java.util.*;
import org.junit.*;

public class FirstToTest extends FirstToAbstractTest {

    public WinLogic getLogic(int to) {
	return new FirstTo(to);
    }

    @Test
    public void testNegativeToThrowsIAE() {
	try {
	    new FirstTo(-1);
	    fail("Constructor should have thrown an exception");
	} catch(IllegalArgumentException e) {
	    assertEquals("Must provide a number greater than 0", e.getMessage());
	}
    }
}