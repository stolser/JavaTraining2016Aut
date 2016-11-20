package com.stolser.javatraining.designpatterns.structural.decorator.decorator;

import com.stolser.javatraining.designpatterns.structural.decorator.component.Printer;

import static com.google.common.base.Preconditions.checkNotNull;

public abstract class AbstractDecorator implements Printer {
    protected Printer component;

    public AbstractDecorator(Printer component) {
        checkNotNull("component cannot be null", component);
        this.component = component;
    }

    @Override
    public void print(String text) {
        component.print(text);
    }
}
