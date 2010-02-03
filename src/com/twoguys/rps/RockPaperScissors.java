package com.twoguys.rps;

import com.twoguys.util.*;
import java.io.*;
import java.util.*;

public class RockPaperScissors {

    private LineNumberReader in;
    private Writer out;

    public RockPaperScissors(LineNumberReader in, Writer out) {
	this.in = in;
	this.out = out;
    }

    public void run(String[] args) {
	try {
	    Either<String, WinLogic> logicOrError = WinLogicFactory.create(args);

	    if(logicOrError.left() != null) {
		out.write(logicOrError.left());
	    } else {
		WinLogic logic = logicOrError.right();

		PlayerPrompt prompt1 = new PlayerPrompt(in, out, 1);
		PlayerPrompt prompt2 = new PlayerPrompt(in, out, 2);

		Player player1 = prompt1.prompt();
		Player player2 = prompt2.prompt();

		AllThrows p1Throws = new AllThrows(new ThrowPrompt(in, out));
		AllThrows p2Throws = new AllThrows(new ThrowPrompt(in, out));

		Pair<Player, Iterator<Throw>> p1 = new Pair<Player, Iterator<Throw>>(player1, p1Throws);
		Pair<Player, Iterator<Throw>> p2 = new Pair<Player, Iterator<Throw>>(player2, p2Throws);

		Game game = new Game(logic, p1, p2);
		Player winner = game.play();

		out.write(winner.getName() + " Wins!");
	    }
	} catch(Exception e) {
	    e.printStackTrace(new PrintWriter(out));
	}
    }

}