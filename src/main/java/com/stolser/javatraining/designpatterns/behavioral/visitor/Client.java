package com.stolser.javatraining.designpatterns.behavioral.visitor;

import com.stolser.javatraining.designpatterns.behavioral.visitor.element.HtmlElement;
import com.stolser.javatraining.designpatterns.behavioral.visitor.element.HtmlParentElement;
import com.stolser.javatraining.designpatterns.behavioral.visitor.element.HtmlTag;
import com.stolser.javatraining.designpatterns.behavioral.visitor.visitor.*;

public class Client {
    public static void main(String[] args) {
        System.out.println("Before visitor......... \\n");
        
        HtmlTag parentTag = new HtmlParentElement("<html>");
        parentTag.setStartTag("<html>");
        parentTag.setEndTag("</html>");
        
        HtmlTag body = new HtmlParentElement("<body>");
        body.setStartTag("<body>");
        body.setEndTag("</body>");
        parentTag.addChildTag(body);
        
        HtmlTag p = new HtmlElement("<p>");
        p.setStartTag("<p>");
        p.setEndTag("</p>");
        p.setTagBody("Testing html tag library");

        body.addChildTag(p);

        p = new HtmlElement("<p>");
        p.setStartTag("<p>");
        p.setEndTag("</p>");
        p.setTagBody("Paragraph 2");

        body.addChildTag(p);

        parentTag.generateHtml();

        System.out.println("\\nAfter visitor....... \\n");

        Visitor cssClass = new CssClassVisitor();
        Visitor style = new StyleVisitor();

        parentTag = new HtmlParentElement("<html>");
        parentTag.setStartTag("<html>");
        parentTag.setEndTag("</html>");
        parentTag.accept(style);
        parentTag.accept(cssClass);

        body = new HtmlParentElement("<body>");
        body.setStartTag("<body>");
        body.setEndTag("</body>");
        body.accept(style);
        body.accept(cssClass);

        parentTag.addChildTag(body);

        p = new HtmlElement("<p>");
        p.setStartTag("<p>");
        p.setEndTag("</p>");

        p.setTagBody("Testing html tag library");

        p.accept(style);
        p.accept(cssClass);
        body.addChildTag(p);

        p = new HtmlElement("<p>");
        p.setStartTag("<p>");
        p.setEndTag("</p>");
        p.setTagBody("Paragraph 2");
        p.accept(style);
        p.accept(cssClass);
        body.addChildTag(p);

        parentTag.generateHtml();
    }
}
