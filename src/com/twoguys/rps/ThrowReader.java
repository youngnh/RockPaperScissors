package com.twoguys.rps;

import com.twoguys.util.*;
import java.util.*;

public class ThrowReader {

    private Map<String, Throw> throwMap = new Hashtable<String, Throw>();

    public ThrowReader() {
	this.throwMap.put("R", new Rock());
	this.throwMap.put("P", new Paper());
	this.throwMap.put("S", new Scissors());
    }

    public Maybe<Throw> read(String thrown) {
	Maybe<Throw> result;

	Throw lookup = throwMap.get(thrown);
	if(lookup == null) {
	    result = new Nothing();
	} else {
	    result = new Just<Throw>(lookup);
	}

	return result;
    }

}