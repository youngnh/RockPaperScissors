package com.twoguys.util;

import java.io.*;

public class PutStrTo implements Print {

    private PrintStream out;
    private String msg;

    public PutStrTo(PrintStream out, String msg) {
	this.out = out;
	this.msg = msg;
    }

    public void print() {
	out.print(msg);
	out.flush();
    }

}