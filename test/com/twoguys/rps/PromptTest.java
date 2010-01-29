package com.twoguys.rps;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.twoguys.util.*;
import java.io.*;
import java.text.*;
import java.util.*;
import org.junit.*;
import org.mockito.*;

public class PromptTest {

    @Test
    public void testPromptPromptsThenReads() throws Exception {
	Reader in = new StringReader("jabberwocky");
	Notify notify = mock(Notify.class);
	Read read = mock(Read.class);

	Object mockObject = mock(Object.class);
	List<Pair<Object, String>> readReturn = new ArrayList<Pair<Object, String>>();
	readReturn.add(new Pair<Object, String>(mockObject, ""));
	
	when(read.read(anyString())).thenReturn(readReturn);

	InOrder inOrder = inOrder(notify, read);

	Prompt prompt = new Prompt(in, notify, read);
	prompt.prompt();

	inOrder.verify(notify).print();
	inOrder.verify(read).read(anyString());
    }

    @Test
    public void testPromptPassesReadInputToReader() throws Exception {
	String input = "jabberwocky";
	Reader in = new StringReader(input);
	Notify notify = mock(Notify.class);
	Read read = mock(Read.class);

	Object mockObject = mock(Object.class);
	List<Pair<Object, String>> readReturn = new ArrayList<Pair<Object, String>>();
	readReturn.add(new Pair<Object, String>(mockObject, ""));

	when(read.read(anyString())).thenReturn(readReturn);

	Prompt prompt = new Prompt(in, notify, read);
	prompt.prompt();

	verify(read).read(input);
    }

    @Test
    public void testPromptOnlyReadsToNewLine() throws Exception {
	String input = "jabberwocky";
	Reader in = new StringReader(input + "\n" + "arachnilobster");
	Notify notify = mock(Notify.class);
	Read read = mock(Read.class);

	Object mockObject = mock(Object.class);
	List<Pair<Object, String>> readReturn = new ArrayList<Pair<Object, String>>();
	readReturn.add(new Pair<Object, String>(mockObject, ""));

	when(read.read(anyString())).thenReturn(readReturn);

	Prompt prompt = new Prompt(in, notify, read);
	prompt.prompt();

	verify(read).read(input);
    }

    @Test
    public void testPromptReturnsFirstInListOfWhatReadReturns() throws Exception {
	Reader in = new StringReader("jabberwocky");
	Notify notify = mock(Notify.class);
	Read read = mock(Read.class);

	Object mockObject = mock(Object.class);
	List<Pair<Object, String>> readReturn = new ArrayList<Pair<Object, String>>();
	readReturn.add(new Pair<Object, String>(mockObject, ""));

	when(read.read(anyString())).thenReturn(readReturn);

	Prompt prompt = new Prompt(in, notify, read);
	Object actual = prompt.prompt();
	
	assertSame(mockObject, actual);
    }

    @Test(expected=ParseException.class)
    public void testIfNoParsesThrowsParseException() throws Exception {
	Reader in = new StringReader("jabberwocky");
	Notify notify = mock(Notify.class);
	Read read = mock(Read.class);
	List<Pair<Object, String>> readReturn = new ArrayList<Pair<Object, String>>();
	when(read.read(anyString())).thenReturn(readReturn);

	Prompt prompt = new Prompt(in, notify, read);
	prompt.prompt();

	fail("Should have thrown an exception due to no parses");
    }
}