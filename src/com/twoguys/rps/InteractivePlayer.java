package com.twoguys.rps;

import java.io.*;

public class InteractivePlayer implements Player {

    public InteractivePlayer(InputStream fromPlayer, OutputStream toPlayer) {
	PrintWriter out = new PrintWriter(toPlayer);
	out.write("Player 1 Name: ");
	out.flush();
    }

    public String getName() {
	return "";
    }

    public Throw getMove() {
	return null;
    }
}