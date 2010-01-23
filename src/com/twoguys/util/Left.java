package com.twoguys.rps;

public class Left<A> implements Either<A, Object> {

    private A value;

    public Left(A value) {
	this.value = value;
    }

    public A left() {
	return value;
    }

    public Object right() {
	return null;
    }

}