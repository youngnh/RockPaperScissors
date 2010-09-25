package com.twoguys.rps;

public class Rock extends Throw {

    public Class<? extends Throw> getPwns() {
	return Scissors.class;
    }
}
