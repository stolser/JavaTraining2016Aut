package com.stolser.javatraining.designpatterns.behavioral.observer.observer;

import com.stolser.javatraining.designpatterns.behavioral.observer.subject.Publisher;

public class SmsUser implements Observer {
    private final Publisher publisher;
    private String description;
    private String userInfo;

    public SmsUser(Publisher publisher, String userInfo) {
        if (publisher == null) {
            throw new IllegalArgumentException("No Publisher found.");
        }

        this.publisher = publisher;
        this.userInfo = userInfo;
    }

    @Override
    public void update(String desc) {
        this.description = desc;
        display();
    }

    private void display() {
        System.out.printf("[%s]: %s%n", userInfo, description);
    }

    @Override
    public void subscribe() {
        System.out.printf("Subscribing %s to %s ...%n", userInfo, publisher.subjectDetails());
        this.publisher.subscribeObserver(this);
        System.out.println("Subscribed successfully.");
    }

    @Override
    public void unSubscribe() {
        System.out.printf("Unsubscribing %s to %s ...%n", userInfo, publisher.subjectDetails());
        this.publisher.unSubscribeObserver(this);
        System.out.println("User has been unsubscribed successfully.");
    }
}