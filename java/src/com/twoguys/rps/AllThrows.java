package com.twoguys.rps;

import com.twoguys.util.*;
import java.text.*;
import java.util.*;

public class AllThrows implements Iterator<Throw> {

    private PromptForFrom<Throw> prompt;
    private Throw next;

    public AllThrows(PromptForFrom<Throw> prompt) {
	this.prompt = prompt;
    }

    @Override
    public boolean hasNext() {
	try {
	    next = prompt.prompt();
	} catch(ParseException e) {
	    return false;
	}
	return true;
    }

    @Override
    public Throw next() {
	Throw result = next;
	try {
	    if(next == null) {
		result = prompt.prompt();
	    }
	    next = null;
	    return result;
	} catch(Exception e) {
	    throw new RuntimeException(e);
	}
    }

    @Override
    public void remove() {	
    }
}