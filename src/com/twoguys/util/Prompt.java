package com.twoguys.util;

import java.io.*;
import java.text.*;
import java.util.*;
import org.apache.commons.io.*;

public class Prompt<T> {

    private LineNumberReader in;
    private Notify notify;
    private Reader<T> reader;

    public Prompt(LineNumberReader in, Notify notify, Reader<T> reader) {
	this.in = in;
	this.notify = notify;
	this.reader = reader;
    }

    public T prompt() throws ParseException {
	try {
	    notify.print();	 
	    String line = in.readLine();
	    return reader.read(line);
	} catch(IOException e) {
	    throw new RuntimeException(e);
	}
    }
}