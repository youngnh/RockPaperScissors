package com.twoguys.rps;

public class Game {

    private Player p1;
    private Player p2;
    private int p1Score = 0;
    private int p2Score = 0;

    public Game(Player p1, Player p2) {
	this.p1 = p1;
	this.p2 = p2;
    }

    public Player play() {
	Player winner;
	for(; winner == null; winner = scoring.getWinner()) {
	    Throw p1Throw = p1.getThrow();
	    Throw p2Throw = p2.getThrow();

	    p1Throw.beats(p2Throw) ? p1Score++ : p2Score++;
	}
	return winner;
    }

}