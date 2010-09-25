package com.twoguys.rps;

import static org.junit.Assert.*;

import com.twoguys.util.*;
import org.junit.*;

public class WinLogicFactoryTest {

    @Test
    public void testNoArgsReturnsRightFirstTo1() {
	String[] args = {};
	Either<String, WinLogic> result = WinLogicFactory.create(args);
	assertEquals(Right.class, result.getClass());
	WinLogic logic = result.right();
	assertEquals(FirstTo.class, logic.getClass());
	assertEquals(1, ((FirstTo) logic).getTo());
    }

    @Test
    public void testToAndNumberReturnsFirstToThatNumber() {
	String[] args = { "-to", "5" };
	Either<String, WinLogic> result = WinLogicFactory.create(args);
	assertEquals(Right.class, result.getClass());
	WinLogic logic = result.right();
	assertEquals(FirstTo.class, logic.getClass());
	assertEquals(5, ((FirstTo) logic).getTo());
    }

    @Test
    public void testToAndUnparseableReturnsParseErrorMessage() {
	String badInt = "jabberwocky";
	String[] args = { "-to", badInt };
	Either<String, WinLogic> result = WinLogicFactory.create(args);
	assertEquals(Left.class, result.getClass());
	String err = result.left();
	assertEquals("'" + badInt + "' not a valid integer", err);
    }

    @Test
    public void testToAndInvalidNumberReturnsConstructorException() {
	String expected = null;
	try {
	    new FirstTo(0);
	    fail("FirstTo constructor should have thrown an exception");
	} catch(Exception e) {
	    expected = e.getMessage();
	}

	String[] args = { "-to", "0" };
	Either<String, WinLogic> result = WinLogicFactory.create(args);
	assertEquals(Left.class, result.getClass());
	String actual = result.left();
	assertEquals(expected, actual);
    }

    @Test
    public void testToByAndTwoNumbersReturnsWinBy() {
	String[] args = { "-to", "5", "-by", "2" };
	Either<String, WinLogic> result = WinLogicFactory.create(args);
	assertEquals(Right.class, result.getClass());
	WinLogic logic = result.right();
	assertEquals(WinBy.class, logic.getClass());
	assertEquals(5, ((WinBy) logic).getTo());
	assertEquals(2, ((WinBy) logic).getBy());
    }

    @Test
    public void testToByAndUnparseableNumberReturnsParseErrorMessage() {
	String badInt = "jabberwocky";
	String[] args = { "-to", badInt, "-by", "2" };
	Either<String, WinLogic> result = WinLogicFactory.create(args);
	assertEquals(Left.class, result.getClass());
	String err = result.left();
	assertEquals("'" + badInt + "' not a valid integer", err);

	badInt = "wonderboy";
	String[] args2 = { "-to", "2", "-by", badInt };
	result = WinLogicFactory.create(args2);
	assertEquals(Left.class, result.getClass());
	err = result.left();
	assertEquals("'" + badInt + "' not a valid integer", err);
    }

    @Test
    public void testToByAndInvalidNumberReturnsConstructorException() {
	// negative numbers
	String expected = null;
	try {
	    new WinBy(-1, 5);
	    fail("WinBy constructor should have thrown an exception");
	} catch(Exception e) {
	    expected = e.getMessage();
	}

	// bigger by than to
	String expected2 = null;
	try {
	    new WinBy(5, 10);
	    fail("WinBy constructor should have thrown an exception");
	} catch(Exception e) {
	    expected2 = e.getMessage();
	}

	String[] args = { "-to", "-1", "-by", "5" };
	Either<String, WinLogic> result = WinLogicFactory.create(args);
	assertEquals(Left.class, result.getClass());
	String actual = result.left();
	assertEquals(expected, actual);

	String[] args2 = { "-to", "5", "-by", "10" };
	result = WinLogicFactory.create(args2);
	assertEquals(Left.class, result.getClass());
	actual = result.left();
	assertEquals(expected2, actual);
    }

    @Test
    public void testBestOfReturnsAndNumberReturnsBestOf() {
	String[] args = { "-bestof", "5" };
	Either<String, WinLogic> result = WinLogicFactory.create(args);
	assertEquals(Right.class, result.getClass());
	WinLogic logic = result.right();
	assertEquals(BestOf.class, logic.getClass());
	assertEquals(5, ((BestOf) logic).getOf());
    }

    @Test
    public void testBestOfAndUnparseableNumberReturnsParseErrorMessage() {
	String badInt = "jabberwocky";
	String[] args = { "-bestof", badInt };
	Either<String, WinLogic> result = WinLogicFactory.create(args);
	assertEquals(Left.class, result.getClass());
	String err = result.left();
	assertEquals("'" + badInt + "' not a valid integer", err);
    }

    @Test
    public void testBestOfAndInvalidNumberReturnsConstructorException() {
	// negative numbers
	String expected = null;
	try {
	    new BestOf(-1);
	    fail("BestOf constructor should have thrown an exception");
	} catch(Exception e) {
	    expected = e.getMessage();
	}

	// even numbers
	String expected2 = null;
	try {
	    new BestOf(4);
	    fail("BestOf constructor should have thrown an exception");
	} catch(Exception e) {
	    expected2 = e.getMessage();
	}

	String[] args = { "-bestof", "-1" };
	Either<String, WinLogic> result = WinLogicFactory.create(args);
	assertEquals(Left.class, result.getClass());
	String actual = result.left();
	assertEquals(expected, actual);

	String[] args2 = { "-bestof", "4" };
	result = WinLogicFactory.create(args2);
	assertEquals(Left.class, result.getClass());
	actual = result.left();
	assertEquals(expected2, actual);
    }
}