package com.twoguys.rps;

import java.io.*;

public class PlayerPrompt {

    private Prompt prompt;
    private Response response;

    public PlayerPrompt(Prompt prompt, Response response) {
	this.prompt = prompt;
	this.response = response;
    }

    public Player prompt() throws IOException {
	prompt.prompt();
	response.read();
	return null;
    }

}