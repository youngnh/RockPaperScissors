package com.twoguys.rps;

import com.twoguys.util.*;
import java.util.*;

public class Game {

    public static final Maybe<Player> NO_WINNER = new Nothing();

    private WinLogic logic;
    private Player player1;
    private Player player2;
    private int p1Score = 0;
    private int p2Score = 0;
    private Iterator<Throw> p1Throws;
    private Iterator<Throw> p2Throws;

    public Game(WinLogic logic, Pair<Player, Iterator<Throw>> player1, Pair<Player, Iterator<Throw>> player2) {
	this.logic = logic;
	this.player1 = player1.a;
	this.player2 = player2.a;
	this.p1Throws = player1.b;
	this.p2Throws = player2.b;
    }

    private Maybe<Player> getWinner() {
	Pair<Player, Integer> p1 = new Pair<Player, Integer>(player1, p1Score);
	Pair<Player, Integer> p2 = new Pair<Player, Integer>(player2, p2Score);

	return logic.determineWinner(p1, p2);
    }

    public Player play() {
	Maybe<Player> player = getWinner();
	while(player.equals(NO_WINNER)) {
	    if(p1Throws.hasNext() && p2Throws.hasNext()) {
		Throw t1 = p1Throws.next();
		Throw t2 = p2Throws.next();
		if(t1.beats(t2)) {
		    p1Score++;
		} else if(t2.beats(t1)) {
		    p2Score++;
		}
	    }
	    player = getWinner();
	}
	return player.value();
    }

}