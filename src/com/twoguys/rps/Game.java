package com.twoguys.rps;

public class Game {

    private Player p1;
    private Player p2;

    public Game(Player p1, Player p2) {
	this.p1 = p1;
	this.p2 = p2;
    }

    public Player play() {
	Throw p1Throw = p1.getMove();
	Throw p2Throw = p2.getMove();

	return p1Throw.beats(p2Throw) ? p1 : p2;
    }

}