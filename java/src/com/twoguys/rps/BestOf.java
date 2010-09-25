package com.twoguys.rps;

import com.twoguys.util.*;

public class BestOf implements WinLogic {

    private int of;
    private WinLogic impl;

    public BestOf(int of) {
	if(of <= 1 || of % 2 == 0) {
	    throw new IllegalArgumentException(of + " is not valid, must provide an odd number greater than 1");
	}

	this.of = of;
	this.impl = new FirstTo(of / 2 + 1);	
    }

    public Maybe<Player> determineWinner(Pair<Player, Integer> p1, Pair<Player, Integer> p2) {
	return impl.determineWinner(p1, p2);
    }

    public int getOf() {
	return of;
    }
}