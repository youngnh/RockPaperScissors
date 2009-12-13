package com.twoguys.rps;

import java.io.*;

public class InteractivePlayer implements Player {

    private String name;

    public InteractivePlayer(Reader fromPlayer, Writer toPlayer) throws IOException {
	toPlayer.write("Player 1 Name: ");
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