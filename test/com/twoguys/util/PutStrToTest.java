package com.twoguys.util;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.*;
import org.junit.*;

public class PutStrToTest {

    @Test
    public void testOutputWrittenOnWrite() {
	ByteArrayOutputStream out = new ByteArrayOutputStream();

	String message = "here's looking at you, kid";
	Print notify = new PutStrTo(new PrintStream(out), message);
	notify.print();

	String actual = out.toString();
	assertEquals(message, actual);
    }

    @Test
    public void testOutputFlushedOnWrite() throws Exception {
	PrintStream out = mock(PrintStream.class);

	String message = "wonder boy and young nasty man";
	Print notify = new PutStrTo(out, message);
	notify.print();

	verify(out).flush();
    }

    @Test
    public void testMessageNotWrittenUntilInvoked() {
	ByteArrayOutputStream out = new ByteArrayOutputStream();

	String message = "beam me up, scotty";
	Print notify = new PutStrTo(new PrintStream(out), message);

	assertEquals("", out.toString());
    }
}