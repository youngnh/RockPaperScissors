package com.twoguys.rps;

import com.twoguys.util.*;

public class FirstToWinBy implements WinLogic {

    private int to;
    private int by;

    public FirstToWinBy(int to, int by) {
	this.to = to;
	this.by = by;
    }

    public Maybe<Player> determineWinner(Pair<Player, Integer> player1, Pair<Player, Integer> player2) {
	Player p1 = player1.a;
	int p1Score = player1.b;
	Player p2 = player2.a;
	int p2Score = player2.b;

	if((p1Score >= to) && (p1Score - p2Score) >= by) {
	    return new Just<Player>(p1);
	} else if((p2Score >= to) && (p2Score - p1Score) >= by) {
	    return new Just<Player>(p2);
	} else {
	    return new Nothing();
	}
    }
}