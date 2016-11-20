package com.stolser.javatraining.designpatterns.structural.iterator;

import com.stolser.javatraining.designpatterns.structural.iterator.container.MyContainer;
import com.stolser.javatraining.designpatterns.structural.iterator.iterator.Iterable;
import com.stolser.javatraining.designpatterns.structural.iterator.iterator.Iterator;

public class Client {
    public static void main(String[] args) {
        String[] strNumbers = {"one", "two", "three", "four", "five"};

        Iterable<String> numbers = new MyContainer(strNumbers);
        Iterator<String> iterator = numbers.iterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

    }
}
