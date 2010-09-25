package com.twoguys.util;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.twoguys.util.*;
import java.io.*;
import java.text.*;
import java.util.*;
import org.junit.*;
import org.mockito.*;

public class PromptForFromTest {

    @Test
    public void testPromptPromptsThenReads() throws Exception {
	InputStream instream = new ByteArrayInputStream("jabberwocky".getBytes());
	LineNumberReader in = new LineNumberReader(new InputStreamReader(instream));
	Print notify = mock(Print.class);
	Reader reader = mock(Reader.class);

	InOrder inOrder = inOrder(notify, reader);

	PromptForFrom prompt = new PromptForFrom(in, notify, reader);
	prompt.prompt();

	inOrder.verify(notify).print();
	inOrder.verify(reader).read(anyString());
    }

    @Test
    public void testPromptPassesInputToReader() throws Exception {
	String input = "jabberwocky";
	InputStream instream = new ByteArrayInputStream(input.getBytes());
	LineNumberReader in = new LineNumberReader(new InputStreamReader(instream));

	Print notify = mock(Print.class);
	Reader reader = mock(Reader.class);

	PromptForFrom prompt = new PromptForFrom(in, notify, reader);
	prompt.prompt();

	verify(reader).read(input);
    }

    @Test
    public void testPromptOnlyReadsToNewLine() throws Exception {
	String input = "jabberwocky";
	InputStream instream = new ByteArrayInputStream((input + "\n" + "arachnilobster").getBytes());
	LineNumberReader in = new LineNumberReader(new InputStreamReader(instream));
	Print notify = mock(Print.class);
	Reader reader = mock(Reader.class);

	PromptForFrom prompt = new PromptForFrom(in, notify, reader);
	prompt.prompt();

	verify(reader).read(input);
    }

    @Test
    public void testPromptReturnsWhatReadReturns() throws Exception {
	String input = "jabberwocky";
	InputStream instream = new ByteArrayInputStream((input + "\n" + "arachnilobster").getBytes());
	LineNumberReader in = new LineNumberReader(new InputStreamReader(instream));
	Print notify = mock(Print.class);
	Reader reader = mock(Reader.class);

	Object mockObject = mock(Object.class);
	when(reader.read(input)).thenReturn(mockObject);

	PromptForFrom prompt = new PromptForFrom(in, notify, reader);
	Object actual = prompt.prompt();
	
	assertSame(mockObject, actual);
    }

}