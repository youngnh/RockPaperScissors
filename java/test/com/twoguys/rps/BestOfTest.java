package com.twoguys.rps;

import static org.junit.Assert.*;

import com.twoguys.util.*;
import java.util.*;
import org.junit.*;

public class BestOfTest extends FirstToAbstractTest {

    public int getTo() {
	int to = new Random().nextInt(100);
	if(to % 2 == 0) {
	    to += 1;
	}
	return to + 2;
    }

    public WinLogic getLogic(int to) {
	return new BestOf(to * 2 - 1);
    }

    @Test
    public void testLessThan1ToThrowsIAE() {
	try {
	    new BestOf(-1);
	    fail("Constructor should have thrown an exception");
	} catch(IllegalArgumentException e) {
	    assertEquals("-1 is not valid, must provide an odd number greater than 1", e.getMessage());
	}
    }

    @Test
    public void testEvenNumbersThrowIAE() {
	try {
	    new BestOf(2);
	    fail("Constructor should have thrown an exception");
	} catch(IllegalArgumentException e) {
	    assertEquals("2 is not valid, must provide an odd number greater than 1", e.getMessage());
	}
    }
    
}