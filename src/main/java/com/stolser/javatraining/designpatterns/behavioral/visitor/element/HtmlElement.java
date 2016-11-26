package com.stolser.javatraining.designpatterns.behavioral.visitor.element;

import com.stolser.javatraining.designpatterns.behavioral.visitor.visitor.Visitor;

public class HtmlElement extends HtmlTag {
    private String tagBody;

    public HtmlElement(String tagName) {
        this.tagName = tagName;
        this.tagBody = "";
        this.startTag = "";
        this.endTag = "";
    }

    @Override
    public void setTagBody(String tagBody) {
        this.tagBody = tagBody;
    }

    @Override
    public void generateHtml() {
        System.out.println(startTag + "" + tagBody + "" + endTag);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
