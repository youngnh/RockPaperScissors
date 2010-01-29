package com.twoguys.rps;

import com.twoguys.util.*;

public class WinBy implements WinLogic {

    private int to;
    private int by;

    public WinBy(int to, int by) {
	this.to = to;
	this.by = by;
    }

    public Maybe<Player> determineWinner(Pair<Player, Integer> p1, Pair<Player, Integer> p2) {
	int p1Score = p1.b;
	int p2Score = p2.b;

	if(p1Score >= to && (p1Score - p2Score) >= by) {
	    return new Just(p1.a);
	} else if(p2Score >= to && (p2Score - p1Score) >= by) {
	    return new Just(p2.a);
	}
	return new Nothing();
    }

}