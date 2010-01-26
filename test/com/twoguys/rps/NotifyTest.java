package com.twoguys.rps;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.*;
import org.junit.*;

public class NotifyTest {

    @Test
    public void testOutputWrittenOnWrite() {
	StringWriter out = new StringWriter();

	String message = "here's looking at you, kid";
	Notify notify = new Notify(out, message);
	notify.print();

	String actual = out.toString();
	assertEquals(message, actual);
    }

    @Test
    public void testOutputFlushedOnWrite() throws Exception {
	Writer out = mock(Writer.class);

	String message = "wonder boy and young nasty man";
	Notify notify = new Notify(out, message);
	notify.print();

	verify(out).flush();
    }

    @Test
    public void testMessageNotWrittenUntilInvoked() {
	StringWriter out = new StringWriter();

	String message = "beam me up, scotty";
	Notify notify = new Notify(out, message);

	assertEquals("", out.toString());
    }
}