package com.twoguys.rps;

public class Right<B> implements Either<Object, B> {

    private B value;

    public Right(B value) {
	this.value = value;
    }

    public Object left() {
	return null;
    }

    public B right() {
	return value;
    }

}