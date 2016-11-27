package com.stolser.javatraining.designpatterns.behavioral.iterator.iterator;

public interface Iterator<T> {
    boolean hasNext();
    T next();
}
