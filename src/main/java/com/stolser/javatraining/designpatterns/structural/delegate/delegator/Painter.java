package com.stolser.javatraining.designpatterns.structural.delegate.delegator;

import com.stolser.javatraining.designpatterns.structural.delegate.delegatee.Graphics;

public class Painter {
    private Graphics graphics;

    public void setGraphics(Graphics graphics) {
        this.graphics = graphics;
    }

    public void paint() {
        graphics.draw();
    }
}
