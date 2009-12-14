package com.twoguys.util;

public class Just<T> implements Maybe<T> {

    private T value;

    public Just(T value) {
	this.value = value;
    }

    public T value() {
	return this.value;
    }

}
