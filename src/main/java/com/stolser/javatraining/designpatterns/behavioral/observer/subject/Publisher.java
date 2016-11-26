package com.stolser.javatraining.designpatterns.behavioral.observer.subject;

import com.stolser.javatraining.designpatterns.behavioral.observer.observer.Observer;

public interface Publisher {
    void subscribeObserver(Observer observer);
    void unSubscribeObserver(Observer observer);
    void notifyObservers();
    String subjectDetails();
}
