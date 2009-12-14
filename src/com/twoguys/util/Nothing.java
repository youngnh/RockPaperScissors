package com.twoguys.util;

public class Nothing implements Maybe {

    public <T> T value() {
	return null;
    }

}
