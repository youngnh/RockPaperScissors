package com.twoguys.rps;

import java.io.*;

public class Notify {

    private Writer out;
    private String msg;

    public Notify(Writer out, String msg) {
	this.out = out;
	this.msg = msg;
    }

    public void print() {
	try {
	    out.write(msg);
	    out.flush();
	} catch(IOException e) {
	}
    }

}