package com.stolser.javatraining.designpatterns.structural.adapter.adapter;

import com.stolser.javatraining.designpatterns.structural.adapter.adaptee.RasterGraphics;
import com.stolser.javatraining.designpatterns.structural.adapter.target.VectorGraphics;

public class InheritanceVectorGraphicsFromRaster extends RasterGraphics implements VectorGraphics {
    @Override
    public void drawLine() {
        drawRasterLine();
    }

    @Override
    public void drawSquare() {
        drawRasterSquare();
    }
}
