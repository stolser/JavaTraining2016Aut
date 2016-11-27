package com.stolser.javatraining.designpatterns.behavioral.visitor.element;

import com.stolser.javatraining.designpatterns.behavioral.visitor.visitor.Visitor;

public interface Element {
    void accept(Visitor visitor);
}
