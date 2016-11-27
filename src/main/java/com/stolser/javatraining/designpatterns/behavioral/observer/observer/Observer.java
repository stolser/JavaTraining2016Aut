package com.stolser.javatraining.designpatterns.behavioral.observer.observer;

public interface Observer {
    void update(String desc);
    void subscribe();
    void unSubscribe();
}
