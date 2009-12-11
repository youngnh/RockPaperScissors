package com.twoguys.rps;

public class Rock extends Throw {

    public boolean beats(Throw other) {
	if(other.getClass() == Scissors.class) {
	    return true;
	} else {
	    return false;
	}
    }

}
