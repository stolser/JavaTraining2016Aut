package com.stolser.javatraining.designpatterns.behavioral.visitor.element;

import com.stolser.javatraining.designpatterns.behavioral.visitor.visitor.Visitor;

import java.util.ArrayList;
import java.util.List;

public class HtmlParentElement extends HtmlTag {
    private List<HtmlTag> childrenTag;

    public HtmlParentElement(String tagName) {
        this.tagName = tagName;
        this.startTag = "";
        this.endTag = "";
        this.childrenTag = new ArrayList<>();
    }

    @Override
    public void addChildTag(HtmlTag htmlTag) {
        childrenTag.add(htmlTag);
    }

    @Override
    public void removeChildTag(HtmlTag htmlTag) {
        childrenTag.remove(htmlTag);
    }

    @Override
    public List<HtmlTag> getChildren() {
        return childrenTag;
    }

    @Override
    public void generateHtml() {
        System.out.println(startTag);

        childrenTag.forEach(HtmlTag::generateHtml);

        System.out.println(endTag);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
