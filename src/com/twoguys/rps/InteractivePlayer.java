package com.twoguys.rps;

import java.io.*;

public class InteractivePlayer implements Player {

    private String name;

    public InteractivePlayer(Reader fromPlayer, Writer toPlayer, int n) throws IOException {
	toPlayer.write("Player " + n + " Name: ");
	toPlayer.flush();
	LineNumberReader in = new LineNumberReader(fromPlayer);
	name = in.readLine();
    }

    public String getName() {
	return name;
    }

    public Throw getMove() {
	return null;
    }
}