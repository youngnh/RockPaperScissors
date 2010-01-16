package com.twoguys.rps;

import java.io.*;

public class Prompt {

    private Writer out;
    private String message;

    public Prompt(Writer out, String message) {
	this.out = out;
	this.message = message;
    }

    public void prompt() throws IOException {
	out.write(message);
	out.flush();
    }

}