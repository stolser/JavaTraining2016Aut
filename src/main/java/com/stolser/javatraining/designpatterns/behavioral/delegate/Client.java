package com.stolser.javatraining.designpatterns.behavioral.delegate;

import com.stolser.javatraining.designpatterns.behavioral.delegate.delegatee.Circle;
import com.stolser.javatraining.designpatterns.behavioral.delegate.delegatee.Graphics;
import com.stolser.javatraining.designpatterns.behavioral.delegate.delegatee.Rectangle;
import com.stolser.javatraining.designpatterns.behavioral.delegate.delegatee.Triangle;
import com.stolser.javatraining.designpatterns.behavioral.delegate.delegator.Painter;

import java.util.Arrays;
import java.util.List;

public class Client {
    public static void main(String[] args) {
        List<Graphics> graphicElements = Arrays.asList(new Circle(), new Rectangle(), new Triangle());
        Painter painter = new Painter();

        graphicElements.stream()
                .forEach(element -> {
                    painter.setGraphics(element);
                    painter.paint();
                });
    }
}
