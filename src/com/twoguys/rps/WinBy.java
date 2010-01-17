package com.twoguys.rps;

import com.twoguys.util.*;

public class WinBy implements WinLogic {

    private int to;
    private int by;

    public WinBy(int to, int by) {
	boolean valid = (to > 0 && by >= 0 && to >= by);
	if(!valid) {
	    throw new IllegalArgumentException("Must be to a number larger than 0 by a postive number less than that");
	}
	this.to = to;
	this.by = by;
    }

    public Maybe<Player> determineWinner(Pair<Player, Integer> p1, Pair<Player, Integer> p2) {
	Maybe<Player> winner = new Nothing();

	int p1Score = p1.b;
	int p2Score = p2.b;
	if(p1Score >= to && p1Score >= p2Score + by) {
	    winner = new Just<Player>(p1.a);
	} else if(p2Score >= to && p2Score >= p1Score + by) {
	    winner = new Just<Player>(p2.a);
	}
	    
	return winner;
    }
}