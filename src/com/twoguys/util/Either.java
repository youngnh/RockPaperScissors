package com.twoguys.rps;

public interface Either<A, B> {

    A left();
    B right();

}