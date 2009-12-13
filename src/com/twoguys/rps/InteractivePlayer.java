package com.twoguys.rps;

import java.io.*;

public class InteractivePlayer implements Player {

    private String prompt;
    private LineNumberReader fromPlayer;
    private Writer toPlayer;
    private String name;

    public InteractivePlayer(Reader fromPlayer, Writer toPlayer, int n) throws IOException {
	this.prompt = "Player " + n + " Name: ";
	this.toPlayer = toPlayer;
	this.fromPlayer = new LineNumberReader(fromPlayer);
	init();
    }

    private void init() throws IOException {
	toPlayer.write(prompt);
	toPlayer.flush();
	name = fromPlayer.readLine();
    }

    @Override
    public String getName() {
	return name;
    }

    @Override
    public Throw getThrow() {
	Throw thrown = null;
	try {
	    toPlayer.write("[R]ock, [P]aper, or [S]cissors? ");
	    String input = fromPlayer.readLine();
	    if(input != null) {
		char choice = input.toUpperCase().charAt(0);
		switch(choice) {
		case 'R':
		    thrown = new Rock();
		    break;
		case 'P':
		    thrown = new Paper();
		    break;
		case 'S':
		    thrown = new Scissors();
		    break;
		}
	    }
	} catch(IOException e) {
	    // squelch
	}
	return thrown;
    }
}