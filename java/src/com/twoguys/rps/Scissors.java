package com.twoguys.rps;

public class Scissors extends Throw {

    public Class<? extends Throw> getPwns() {
	return Paper.class;
    }

}
