package com.twoguys.rps;

import com.twoguys.util.*;
import java.util.*;

public class Game {

    private WinLogic logic;
    private Round round;
    private Player p1;
    private Player p2;
    private int p1Score;
    private int p2Score;
    private Iterator<Throw> p1Throws;
    private Iterator<Throw> p2Throws;

    public Game(WinLogic logic, Player p1, Player p2, Iterator<Throw> p1Throws, Iterator<Throw> p2Throws) {
	this.logic = logic;
	this.round = new Round();
	this.p1 = p1;
	this.p2 = p2;
	this.p1Score = 0;
	this.p2Score = 0;
	this.p1Throws = p1Throws;
	this.p2Throws = p2Throws;
    }

    public Player play() {
	Maybe<Player> winner = logic.determineWinner(new Pair<Player, Integer>(p1, p1Score), new Pair<Player, Integer>(p2, p2Score));

	if(winner.getClass() == Nothing.class) {
	    return play((Nothing) winner);
	} else {
	    return play((Just) winner);
	}
    }

    public Player play(Nothing noWinner) {
	Throw t1 = p1Throws.next();
	Throw t2 = p2Throws.next();
	Pair<Integer, Integer> roundScore = round.play(t1, t2);
	p1Score += roundScore.a;
	p2Score += roundScore.b;

	return play();
    }

    public Player play(Just<Player> winner) {
	return winner.value();
    } 
}