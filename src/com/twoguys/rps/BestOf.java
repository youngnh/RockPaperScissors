package com.twoguys.rps;

import com.twoguys.util.*;

public class BestOf implements WinLogic {

    private WinLogic logic;

    public BestOf(int total) {
	boolean valid = (total > 1) && (total % 2 == 1);
	if(!valid) {
	    throw new IllegalArgumentException("Must be an odd number greater than 1");
	}
	this.logic = new FirstTo(total / 2 + 1);
    }

    public Maybe<Player> determineWinner(Pair<Player, Integer> p1, Pair<Player, Integer> p2) {
	return logic.determineWinner(p1, p2);
    }
    
}