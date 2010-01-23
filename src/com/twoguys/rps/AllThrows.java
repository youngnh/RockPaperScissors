package com.twoguys.rps;

import com.twoguys.util.*;
import java.io.*;
import java.util.*;

public class AllThrows implements Iterator<Throw> {

    private PromptFor<Throw> prompt;
    private Throw next;

    public AllThrows(java.io.Reader in, Writer out) {
	this.prompt = new ThrowPrompt(in, out);
    }

    public boolean hasNext() {
	if(next == null) {
	    try {
		next = prompt.prompt();
	    } catch(Exception e) {
		return false;
	    }
	}
	return true;
    }

    public Throw next() {
	if(next == null) { // caller didn't check hasNextFirst(), fuckers
	    try {
		next = prompt.prompt();
	    } catch(Exception e) {
		// get what they don't check for
		throw new RuntimeException(e);
	    }
	}
	Throw result = next;
	next = null;
	return result;
    }

    public void remove() {
	throw new UnsupportedOperationException();
    }

}