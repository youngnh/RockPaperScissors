package com.twoguys.util;

import java.text.*;
import java.util.*;

public class Reader<T> {

    private Read<T> read;

    public Reader(Read<T> read) {
	this.read = read;
    }

    public T read(String str) throws ParseException {
	List<Pair<T, String>> parses = read.read(str);
	if(parses.isEmpty()) {
	    throw new ParseException("No parse of 'jabberwocky'", 0);
	}
	return parses.get(0).a;
    }
}