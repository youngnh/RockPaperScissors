package com.twoguys.util;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.text.*;
import java.util.*;
import org.junit.*;

public class ReaderTest {

    @Test
    public void testThrowsParseExceptionIfNoParses() throws Exception {
	String input = "jabberwocky";
	Read read = mock(Read.class);

	Object expected = mock(Object.class);
	List<Pair<Object, String>> mockResult = new ArrayList<Pair<Object, String>>();

	when(read.read(input)).thenReturn(mockResult);

	try {
	    Reader reader = new Reader(read);
	    reader.read(input);
	} catch(ParseException e) {
	    assertEquals("No parse of '" + input + "'", e.getMessage());
	    assertEquals(0, e.getErrorOffset());
	}
    }

    @Test
    public void testReadReturnsFirstInListOfWhatReadReturns() throws Exception {
	String input = "jabberwocky";
	Read read = mock(Read.class);

	Object expected = mock(Object.class);
	String unconsumed = "";
	List<Pair<Object, String>> mockResult = new ArrayList<Pair<Object, String>>();
	mockResult.add(new Pair<Object, String>(expected, unconsumed));

	when(read.read(input)).thenReturn(mockResult);
	
	Reader reader = new Reader(read);
	Object actual = reader.read(input);

	assertSame(expected, actual);
    }
}