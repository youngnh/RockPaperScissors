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

    public String getName() {
	return name;
    }

    public Throw getMove() {
	try {
	    toPlayer.write("[R]ock, [P]aper, or [S]cissors? ");
	} catch(IOException e) {
	    // squelch
	}
	return null;
    }
}