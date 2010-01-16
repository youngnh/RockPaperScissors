package com.twoguys.rps;

import java.io.*;

public class Response {

    private LineNumberReader in;

    public Response(Reader in) {
	this.in = new LineNumberReader(in);
    }

    public String read() throws IOException {
	return in.readLine();
    }

}