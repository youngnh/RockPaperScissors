package com.twoguys.util;

public class Left<A, B> implements Either<A, B> {

    private A value;

    public Left(A value) {
	this.value = value;
    }

    public A left() {
	return value;
    }

    public B right() {
	return null;
    }

}