package com.twoguys.util;

import java.io.*;
import java.text.*;

public class Prompter<T> implements PromptFor<T> {

    private LineNumberReader in;
    private Writer out;
    private com.twoguys.util.Reader<T> reader;
    private String prompt;

    public Prompter(java.io.Reader in, Writer out, String prompt, Read<T> reader) {
	this.in = new LineNumberReader(in);
	this.out = out;
	this.prompt = prompt;
	this.reader = new com.twoguys.util.Reader<T>(reader);
    }

    public T prompt() throws IOException, ParseException {
	out.write(prompt);
	out.flush();
	String line = in.readLine();
	return reader.read(line);
    }

}