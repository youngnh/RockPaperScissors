package com.twoguys.rps;

public class Paper extends Throw {

    public Class<? extends Throw> getPwns() {
	return Rock.class;
    }

}
