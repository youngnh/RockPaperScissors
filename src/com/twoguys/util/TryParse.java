package com.twoguys.util;

import java.util.*;

public class TryParse<T> implements Read<T> {

    private List<Pair<String, T>> possible;

    public TryParse(List<Pair<String, T>> possible) {
	this.possible = possible;
    }

    public List<Pair<T, String>> read(String str) {
	List<Pair<T, String>> list = new ArrayList<Pair<T, String>>();

	for(Pair<String, T> attempt : possible) {
	    String prefix = attempt.a;
	    T result = attempt.b;

	    if(str.startsWith(prefix)) {
		String rest = str.substring(prefix.length());
		list.add(new Pair<T, String>(result, rest));
	    }
	}

	return list;
    }
}