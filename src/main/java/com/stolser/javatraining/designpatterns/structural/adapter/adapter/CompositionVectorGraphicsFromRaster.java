package com.stolser.javatraining.designpatterns.structural.adapter.adapter;

import com.stolser.javatraining.designpatterns.structural.adapter.adaptee.RasterGraphics;
import com.stolser.javatraining.designpatterns.structural.adapter.target.VectorGraphics;

public class CompositionVectorGraphicsFromRaster implements VectorGraphics {
    private RasterGraphics rasterGraphics;

    public CompositionVectorGraphicsFromRaster(RasterGraphics rasterGraphics) {
        this.rasterGraphics = rasterGraphics;
    }

    @Override
    public void drawLine() {
        rasterGraphics.drawRasterLine();
    }

    @Override
    public void drawSquare() {
        rasterGraphics.drawRasterSquare();
    }
}
