package com.stolser.javatraining.designpatterns.structural.adapter.adaptee;

public class RasterGraphics {
    public void drawRasterLine() {
        System.out.println("RasterGraphics.drawRasterLine()");
    }

    public void drawRasterSquare() {
        System.out.println("RasterGraphics.drawRasterSquare()");
    }
}
