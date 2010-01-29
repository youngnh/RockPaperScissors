package com.twoguys.rps;

import com.twoguys.util.*;
import java.io.*;
import java.text.*;
import java.util.*;
import org.apache.commons.io.*;

public class Prompt<T> {

    private LineNumberReader in;
    private Notify notify;
    private Read<T> read;

    public Prompt(Reader in, Notify notify, Read<T> read) {
	this.in = new LineNumberReader(in);
	this.notify = notify;
	this.read = read;
    }

    public T prompt() throws ParseException {
	try {
	    notify.print();	 
	    String line = in.readLine();
	    List<Pair<T, String>> parses = read.read(line);
	    if(parses.size() > 0) {
		return parses.get(0).a;
	    } else {
		throw new ParseException("No parse of " + line, 0);
	    }
	} catch(IOException e) {
	    throw new RuntimeException(e);
	}
    }
}