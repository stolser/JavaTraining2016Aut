package com.stolser.javatraining.designpatterns.behavioral.delegate.delegator;

import com.stolser.javatraining.designpatterns.behavioral.delegate.delegatee.Graphics;

public class Painter {
    private Graphics graphics;

    public void setGraphics(Graphics graphics) {
        this.graphics = graphics;
    }

    public void paint() {
        graphics.draw();
    }
}
