package com.samuel.progresqldatabase.mappers;

// in case we want to use a different Mapping implementation
public interface Mapper<A, B> {
    B mapTo(A a);

    A mapFrom (B b);
}
