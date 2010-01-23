package com.twoguys.rps;

import java.io.*;
import java.text.*;

public class PlayGame {

    private WinLogic logic;

    public PlayGame(WinLogic logic) {
	this.logic = logic;
    }

    public void play() throws IOException, ParseException {
	Reader stdin = new InputStreamReader(System.in);
	PrintWriter stdout = new PrintWriter(System.out);

	PlayerPrompt getPlayer1 = new PlayerPrompt(stdin, stdout, 1);
	PlayerPrompt getPlayer2 = new PlayerPrompt(stdin, stdout, 2);
	AllThrows p1Throws = new AllThrows(stdin, stdout);
	AllThrows p2Throws = new AllThrows(stdin, stdout);

	Player p1 = getPlayer1.prompt();
	Player p2 = getPlayer2.prompt();

	Game game = new Game(logic, p1, p2, p1Throws, p2Throws);
	
	Player winner = game.play();

	String notifyWinner = winner.getName() + " Wins!";
	stdout.println(notifyWinner);
    }

}