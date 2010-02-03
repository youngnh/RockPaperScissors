package com.twoguys.util;

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
	LineNumberReader in = new LineNumberReader(new StringReader("jabberwocky"));
	Notify notify = mock(Notify.class);
	Reader reader = mock(Reader.class);

	InOrder inOrder = inOrder(notify, reader);

	Prompt prompt = new Prompt(in, notify, reader);
	prompt.prompt();

	inOrder.verify(notify).print();
	inOrder.verify(reader).read(anyString());
    }

    @Test
    public void testPromptPassesInputToReader() throws Exception {
	String input = "jabberwocky";
	LineNumberReader in = new LineNumberReader(new StringReader(input));

	Notify notify = mock(Notify.class);
	Reader reader = mock(Reader.class);

	Prompt prompt = new Prompt(in, notify, reader);
	prompt.prompt();

	verify(reader).read(input);
    }

    @Test
    public void testPromptOnlyReadsToNewLine() throws Exception {
	String input = "jabberwocky";
	LineNumberReader in = new LineNumberReader(new StringReader(input + "\n" + "arachnilobster"));
	Notify notify = mock(Notify.class);
	Reader reader = mock(Reader.class);

	Prompt prompt = new Prompt(in, notify, reader);
	prompt.prompt();

	verify(reader).read(input);
    }

    @Test
    public void testPromptReturnsWhatReadReturns() throws Exception {
	String input = "jabberwocky";
	LineNumberReader in = new LineNumberReader(new StringReader(input + "\n" + "arachnilobster"));
	Notify notify = mock(Notify.class);
	Reader reader = mock(Reader.class);

	Object mockObject = mock(Object.class);
	when(reader.read(input)).thenReturn(mockObject);

	Prompt prompt = new Prompt(in, notify, reader);
	Object actual = prompt.prompt();
	
	assertSame(mockObject, actual);
    }

}