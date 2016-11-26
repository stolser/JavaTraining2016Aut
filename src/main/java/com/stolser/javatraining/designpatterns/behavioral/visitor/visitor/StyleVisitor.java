package com.stolser.javatraining.designpatterns.behavioral.visitor.visitor;

import com.stolser.javatraining.designpatterns.behavioral.visitor.element.HtmlElement;
import com.stolser.javatraining.designpatterns.behavioral.visitor.element.HtmlParentElement;

public class StyleVisitor implements Visitor {
    @Override
    public void visit(HtmlElement element) {
        element.setStartTag(element.getStartTag().replace(">", " style=’width:46px;’>"));
    }
    @Override
    public void visit(HtmlParentElement parentElement) {
        parentElement.setStartTag(parentElement.getStartTag().replace(">", " style=’width:58px;’>"));
    }
}
