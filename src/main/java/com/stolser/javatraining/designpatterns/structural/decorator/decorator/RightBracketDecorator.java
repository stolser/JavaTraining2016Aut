package com.stolser.javatraining.designpatterns.structural.decorator.decorator;

import com.stolser.javatraining.designpatterns.structural.decorator.component.Printer;

public class RightBracketDecorator extends AbstractDecorator {
    public RightBracketDecorator(Printer component) {
        super(component);
    }

    @Override
    public void print(String text) {
        super.print(text);
        System.out.print("]");
    }
}
