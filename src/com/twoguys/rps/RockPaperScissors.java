package com.twoguys.rps;

import java.io.*;

public class RockPaperScissors {

    public static final String USAGE =
	"usage: \tRockPaperScissors -to x\n" +
	"       \tRockPaperScissors -to to -by by\n" +
	"       \tRockPaperScissors -bestof x\n";

    private Writer out;

    public RockPaperScissors(Reader in, Writer out) {
	this.out = out;
    }

    public void run(String[] args) {
	try {
	    out.write(USAGE);
	    out.flush();
	} catch(IOException e) {
	    e.printStackTrace(new PrintWriter(out));
	}
    }

}