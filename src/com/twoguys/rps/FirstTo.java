package com.twoguys.rps;

import com.twoguys.util.*;

public class FirstTo implements WinLogic {

    private WinLogic logic;

    public FirstTo(int to) {
	boolean valid = (to > 0);
	if(!valid) {
	    throw new IllegalArgumentException("Must be greater than 0");
	}
	this.logic = new WinBy(to, 0);
    }

    public Maybe<Player> determineWinner(Pair<Player, Integer> p1, Pair<Player, Integer> p2) {
	return logic.determineWinner(p1, p2);
    }
}