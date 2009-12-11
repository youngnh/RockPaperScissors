package com.twoguys.rps;

public abstract class Throw {

    public boolean beats(Throw other) {
	if(other.getClass() == getPwns()) {
	    return true;
	} else {
	    return false;
	}
    }

    public abstract Class<? extends Throw> getPwns();
}
