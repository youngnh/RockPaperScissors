package com.twoguys.util;

public class Right<A, B> implements Either<A, B> {

    private B value;

    public Right(B value) {
	this.value = value;
    }

    public A left() {
	return null;
    }

    public B right() {
	return value;
    }

}