package com.stolser.javatraining.designpatterns.structural.decorator.decorator;

import com.stolser.javatraining.designpatterns.structural.decorator.component.Printer;

public class NewLineDecorator extends AbstractDecorator {
    public NewLineDecorator(Printer component) {
        super(component);
    }

    @Override
    public void print(String text) {
        super.print(text);
        System.out.print("\n");
    }
}
