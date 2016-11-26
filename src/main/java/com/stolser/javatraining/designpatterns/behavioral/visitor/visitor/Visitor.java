package com.stolser.javatraining.designpatterns.behavioral.visitor.visitor;

import com.stolser.javatraining.designpatterns.behavioral.visitor.element.HtmlElement;
import com.stolser.javatraining.designpatterns.behavioral.visitor.element.HtmlParentElement;

public interface Visitor {
    void visit(HtmlElement element);
    void visit(HtmlParentElement parentElement);
}
