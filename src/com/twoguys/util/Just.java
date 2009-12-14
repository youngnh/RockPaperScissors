package com.twoguys.util;

public class Just<T> implements Maybe {

    private T value;

    public Just(T value) {
	this.value = value;
    }

    public T value() {
	return this.value;
    }

}
