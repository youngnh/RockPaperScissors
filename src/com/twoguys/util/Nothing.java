package com.twoguys.util;

public class Nothing<T> implements Maybe<T> {

    public T value() {
	return null;
    }

}
