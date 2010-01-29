package com.twoguys.rps;

import com.twoguys.util.*;

public class FirstTo implements WinLogic {

    private int to;

    public FirstTo(int to) {
	this.to = to;
    }

    public Maybe<Player> determineWinner(Pair<Player, Integer> p1, Pair<Player, Integer> p2) {
	int p1Score = p1.b;
	int p2Score = p2.b;

	if(p2Score >= to && p2Score > p1Score) {
	    return new Just(p2.a);
	} else if(p1Score >= to) {
	    return new Just(p1.a);
	}
	return new Nothing();
    }

}