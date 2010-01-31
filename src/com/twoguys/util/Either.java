package com.twoguys.util;

public interface Either<A, B> {

    A left();
    B right();

}