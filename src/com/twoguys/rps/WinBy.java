package com.twoguys.rps;

public class WinBy implements Scoring {

    public WinBy(int to, int by) {
	this.to = to;
	this.by = by;
    }

    @Override
    public Maybe<Player> getWinner() {
	Maybe<Player> winner = new Nothing();

	int p1 = getPlayer1Score();
	int p2 = getPlayer2Score();
	if(p1 >= to && p1 >= (p2 + by)) {
	    winner = new Just(player1);
	}
	if(p2 >= to && p2 >= (p1 + by)) {
	    winner = new Just(player2);
	}

	return winner;
    }

}