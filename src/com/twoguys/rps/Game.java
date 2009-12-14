package com.twoguys.rps;

import com.twoguys.util.*;

public abstract class Game {

    protected Player player1;
    protected Player player2;
    private int p1Score = 0;
    private int p2Score = 0;

    public void setPlayer1(Player player) {
	player1 = player;
    }

    public void setPlayer2(Player player) {
	player2 = player;
    }

    protected int getPlayer1Score() {
	return p1Score;
    }

    protected int getPlayer2Score() {
	return p2Score;
    }

    public abstract Maybe<Player> getWinner();

    public void round() {
	Throw p1Throw = player1.getThrow();
	Throw p2Throw = player2.getThrow();

	if(p1Throw.beats(p2Throw)) {
	    p1Score++;
	} else {
	    p2Score++;
	}
    }

    public Player play() {
	Player winner = null;
	for(; winner == null; winner = getWinner().value()) {
	    round();
	}
	return winner;
    }

}