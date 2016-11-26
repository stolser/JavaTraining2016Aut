package com.stolser.javatraining.designpatterns.behavioral.visitor.element;

import java.util.List;

public abstract class HtmlTag implements Element {
    String tagName;
    String startTag;
    String endTag;

    private static final String OPERATION_IS_NOT_SUPPORT_TEXT =
            "Current operation is not support for this object ";

    public String getTagName() {
        return tagName;
    }

    public String getStartTag() {
        return startTag;
    }

    public void setStartTag(String startTag) {
        this.startTag = startTag;
    }

    public String getEndTag() {
        return endTag;
    }

    public void setEndTag(String endTag) {
        this.endTag = endTag;
    }

    public abstract void generateHtml();

    public void setTagBody(String tagBody) {
        throw new UnsupportedOperationException(OPERATION_IS_NOT_SUPPORT_TEXT);
    }

    public void addChildTag(HtmlTag htmlTag) {
        throw new UnsupportedOperationException(OPERATION_IS_NOT_SUPPORT_TEXT);
    }

    public void removeChildTag(HtmlTag htmlTag) {
        throw new UnsupportedOperationException(OPERATION_IS_NOT_SUPPORT_TEXT);
    }

    public List<HtmlTag> getChildren() {
        throw new UnsupportedOperationException(OPERATION_IS_NOT_SUPPORT_TEXT);
    }
}
