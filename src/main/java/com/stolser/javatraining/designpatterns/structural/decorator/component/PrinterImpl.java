package com.stolser.javatraining.designpatterns.structural.decorator.component;

public class PrinterImpl implements Printer {

    @Override
    public void print(String text) {
        System.out.print(text);
    }
}
