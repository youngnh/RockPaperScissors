package com.twoguys.util;

import java.text.*;
import java.util.*;

public class Reader<T> {

    private Read<T> read;

    public Reader(Read<T> read) {
	this.read = read;
    }

    public T read(String s) throws ParseException {
	List<Pair<T, String>> results = read.read(s);
	if(results.isEmpty()) {
	    throw new ParseException("No parse of '" + s + "'", 0);
	}

	return results.get(0).a;
    }

}