package com.twoguys.rps;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.twoguys.util.*;
import java.text.*;
import java.util.*;
import org.junit.*;

public class AllThrowsTest {

    @Test
    public void testHasNextPromptsTheUser() throws Exception {
	Prompt<Throw> prompt = mock(Prompt.class);
	AllThrows all = new AllThrows(prompt);

	all.hasNext();

	verify(prompt).prompt();
    }

    @Test
    public void testHasNextReturnsFalseIfPromptThrowsException() throws Exception {
	Prompt<Throw> prompt = mock(Prompt.class);
	when(prompt.prompt()).thenThrow(new ParseException("yarg", 0));
	
	AllThrows all = new AllThrows(prompt);

	assertFalse(all.hasNext());
    }

    @Test
    public void theNextInvokesPromptIfHasNextHasntBeenCalled() throws Exception {
	Prompt<Throw> prompt = mock(Prompt.class);
	
	AllThrows all = new AllThrows(prompt);
	all.next();

	verify(prompt).prompt();
    }

    @Test
    public void testPromptOnlyCalledASingleTimeForConsecutiveHasNextNextCalls() throws Exception {
	Prompt<Throw> prompt = mock(Prompt.class);
	when(prompt.prompt()).thenReturn(new Rock());

	AllThrows all = new AllThrows(prompt);

	all.hasNext();
	all.next();

	verify(prompt, times(1)).prompt();	
    }

    @Test
    public void testNextReturnsResultOfPrompt() throws Exception {
	Prompt<Throw> prompt = mock(Prompt.class);
	Throw rock = new Rock();
	when(prompt.prompt()).thenReturn(rock);

	AllThrows all = new AllThrows(prompt);
	Throw actual = all.next();

	assertSame(rock, actual);
    }

    @Test
    public void testNextReturnsResultOfHasNextPrompt() throws Exception {
	Prompt<Throw> prompt = mock(Prompt.class);
	Throw rock = new Rock();
	when(prompt.prompt()).thenReturn(rock).thenThrow(new ParseException("booga", 0));

	AllThrows all = new AllThrows(prompt);
	all.hasNext();
	Throw actual = all.next();

	assertSame(rock, actual);
    }
}