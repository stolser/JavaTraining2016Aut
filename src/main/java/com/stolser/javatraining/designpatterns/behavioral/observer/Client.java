package com.stolser.javatraining.designpatterns.behavioral.observer;

import com.stolser.javatraining.designpatterns.behavioral.observer.observer.Observer;
import com.stolser.javatraining.designpatterns.behavioral.observer.observer.SmsUser;
import com.stolser.javatraining.designpatterns.behavioral.observer.subject.Commentary;
import com.stolser.javatraining.designpatterns.behavioral.observer.subject.CommentaryObject;
import com.stolser.javatraining.designpatterns.behavioral.observer.subject.Publisher;

import java.util.ArrayList;

public class Client {

    public static final String SEPARATOR = "------------------------------";

    public static void main(String[] args) {
        Publisher publisher = new CommentaryObject(new ArrayList<Observer>(), "Football match [2016Nov26]");

        Observer observer = new SmsUser(publisher, "Oleg Stoliarov [Ukraine]");
        observer.subscribe();
        System.out.println(SEPARATOR);

        Observer observer2 = new SmsUser(publisher, "Joshua Bloch [USA]");
        observer2.subscribe();

        Commentary commentary = ((Commentary)publisher);
        commentary.setDescription("Welcome to a live Football match");
        commentary.setDescription("Current score 0-0");
        System.out.println(SEPARATOR);

        observer2.unSubscribe();
        System.out.println(SEPARATOR);

        commentary.setDescription("It’s a goal!!");
        commentary.setDescription("Current score 1-0");
        System.out.println(SEPARATOR);

        Observer observer3 = new SmsUser(publisher, "Big Fan [Poland]");
        observer3.subscribe();
        System.out.println(SEPARATOR);

        commentary.setDescription("It’s another goal!!");
        commentary.setDescription("Half-time score 2-0");
    }
}
