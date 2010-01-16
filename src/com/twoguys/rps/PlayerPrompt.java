package com.twoguys.rps;

import java.io.*;

public class PlayerPrompt {

    private Prompt prompt;

    public PlayerPrompt(Prompt prompt, Response response) {
	this.prompt = prompt;
    }

    public Player prompt() throws IOException {
	prompt.prompt();
	return null;
    }

}