package com.twoguys.rps;

import java.io.*;

public class InteractivePlayer implements Player {

    public InteractivePlayer(Reader fromPlayer, Writer toPlayer) throws IOException {
	toPlayer.write("Player 1 Name: ");
	toPlayer.flush();
	LineNumberReader in = new LineNumberReader(fromPlayer);
	in.readLine();
    }

    public String getName() {
	return "";
    }

    public Throw getMove() {
	return null;
    }
}