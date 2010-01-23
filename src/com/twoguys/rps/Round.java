package com.twoguys.rps;

import com.twoguys.util.*;

public class Round {

    public Pair<Integer, Integer> play(Throw t1, Throw t2) {
	if(t1.beats(t2)) {
	    return new Pair<Integer, Integer>(1, 0);
	} else if(t2.beats(t1)) {
	    return new Pair<Integer, Integer>(0, 1);
	} else {
	    return new Pair<Integer, Integer>(0, 0);
	}
    }

}