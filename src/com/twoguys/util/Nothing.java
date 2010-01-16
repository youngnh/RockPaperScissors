package com.twoguys.util;

public class Nothing implements Maybe {

    public Object value() {
	return null;
    }

    public boolean equals(Object other) {
	return (getClass() == other.getClass());
    }
}
