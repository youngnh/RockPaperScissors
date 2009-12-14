package com.twoguys.rps;

public class FirstTo implements Scoring {

    public FirstTo(int to) {
	this.to = to;
    }

    @Override
    public Maybe<Player> getWinner() {
	Maybe<Player> winner = new Nothing();

	if(getPlayer1Score() == to) {
	    winner = new Just(player1);
	}
	if(getPlayer2Score() == to) {
	    winner = new Just(player2);
	}

	return winner;
    }

}