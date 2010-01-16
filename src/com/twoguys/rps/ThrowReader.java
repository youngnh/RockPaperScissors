package com.twoguys.rps;

import java.util.*;

public class ThrowReader {

    private Map<String, Throw> throwMap = new Hashtable<String, Throw>();

    public ThrowReader() {
	this.throwMap.put("R", new Rock());
	this.throwMap.put("P", new Paper());
    }

    public Throw read(String thrown) {
	return throwMap.get(thrown);
    }

}