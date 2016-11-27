package com.stolser.javatraining.designpatterns.behavioral.strategy.strategy;

public class LowerTextFormatter implements TextFormatter {

    @Override
    public void format(String text) {
        System.out.printf("[LowerTextFormatter]: %s%n", text.toLowerCase());
    }
}