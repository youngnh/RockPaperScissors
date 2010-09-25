package com.twoguys.util;

import java.io.*;
import java.text.*;
import java.util.*;
import org.apache.commons.io.*;

public class PromptForFrom<T> {

    private LineNumberReader in;
    private Print notify;
    private Reader<T> reader;

    public PromptForFrom(LineNumberReader in, Print notify, Reader<T> reader) {
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