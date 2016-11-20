package com.stolser.javatraining.designpatterns.structural.decorator.decorator;

import com.stolser.javatraining.designpatterns.structural.decorator.component.Printer;

public class LeftBracketDecorator extends AbstractDecorator {
    public LeftBracketDecorator(Printer component) {
        super(component);
    }

    @Override
    public void print(String text) {
        System.out.print("[");
        super.print(text);
    }
}
