package com.stolser.javatraining.designpatterns.structural.decorator.decorator;

import com.stolser.javatraining.designpatterns.structural.decorator.component.Printer;

public class QuotesDecorator extends AbstractDecorator {
    public QuotesDecorator(Printer component) {
        super(component);
    }

    @Override
    public void print(String text) {
        component.print("\"");
        super.print(text);
        component.print("\"");
    }
}
