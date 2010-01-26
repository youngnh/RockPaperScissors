package com.twoguys.rps;

import java.io.*;

public class RockPaperScissors {

    public static final String USAGE =
	"usage: \tRockPaperScissors -to x\n" +
	"       \tRockPaperScissors -to to -by by\n" +
	"       \tRockPaperScissors -bestof x\n";

    public RockPaperScissors(Reader in, Writer out) {
	try {
	    out.write(USAGE);
	    out.flush();
	} catch(Exception e) {
	}
    }

    public void run(String[] args) {
    }

}