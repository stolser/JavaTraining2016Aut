package com.stolser.javatraining.designpatterns.behavioral.strategy.strategy;

public class CapTextFormatter implements TextFormatter {

    @Override
    public void format(String text) {
        System.out.printf("[CapTextFormatter]: %s%n", text.toUpperCase());
    }
}