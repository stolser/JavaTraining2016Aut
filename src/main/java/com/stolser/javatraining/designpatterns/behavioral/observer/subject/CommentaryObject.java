package com.stolser.javatraining.designpatterns.behavioral.observer.subject;

import com.stolser.javatraining.designpatterns.behavioral.observer.observer.Observer;

import java.util.List;

public class CommentaryObject implements Publisher, Commentary {
    private final List<Observer> observers;
    private String description;
    private final String subjectDetails;

    public CommentaryObject(List<Observer> observers, String subjectDetails) {
        this.observers = observers;
        this.subjectDetails = subjectDetails;
    }

    @Override
    public void subscribeObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void unSubscribeObserver(Observer observer) {
        int index = observers.indexOf(observer);
        observers.remove(index);
    }

    @Override
    public void notifyObservers() {
        System.out.println("Notifying all observers...");

        for (Observer observer : observers) {
            observer.update(description);
        }

        System.out.println("... all observers have been notified.");
    }

    public void setDescription(String description) {
        this.description = description;
        notifyObservers();
    }

    @Override
    public String subjectDetails() {
        return subjectDetails;
    }
}