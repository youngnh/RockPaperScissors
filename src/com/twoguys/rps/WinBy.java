package com.twoguys.rps;

import com.twoguys.util.*;

public class WinBy implements WinLogic {

    private int to;
    private int by;

    public WinBy(int to, int by) {
	validate(to, by);
	this.to = to;
	this.by = by;
    }

    public void validate(int to, int by) {
	if(to <= 0 || by < 0) {
	    throw new IllegalArgumentException("Must provide a number greater than 0");
	}
	if(by > to) {
	    throw new IllegalArgumentException("Cannot win by a larger margin (" + by + ") than playing to (" + 2 + ")");
	}
    }

    public Maybe<Player> determineWinner(Pair<Player, Integer> p1, Pair<Player, Integer> p2) {
	int p1Score = p1.b;
	int p2Score = p2.b;

	if(p1Score >= getTo() && (p1Score - p2Score) >= by) {
	    return new Just(p1.a);
	} else if(p2Score >= getTo() && (p2Score - p1Score) >= by) {
	    return new Just(p2.a);
	}
	return new Nothing();
    }

    public int getTo() {
	return to;
    }
    
    public int getBy() {
	return by;
    }

}