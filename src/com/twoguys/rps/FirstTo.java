package com.twoguys.rps;

import com.twoguys.util.*;

public class FirstTo implements WinLogic {

    private int to;

    public FirstTo(int to) {
	this.to = to;
    }

    public Maybe<Player> determineWinner(Pair<Player, Integer> p1, Pair<Player, Integer> p2) {
	Maybe<Player> winner = new Nothing();

	int p1Score = p1.b;
	int p2Score = p2.b;
	if(p1Score >= to && p1Score >= p2Score) {
	    winner = new Just<Player>(p1.a);
	} else if(p2Score >= to) {
	    winner = new Just<Player>(p2.a);
	}
	    
	return winner;
    }

}