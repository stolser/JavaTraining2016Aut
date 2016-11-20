package com.stolser.javatraining.designpatterns.structural.adapter;

import com.stolser.javatraining.designpatterns.structural.adapter.adaptee.RasterGraphics;
import com.stolser.javatraining.designpatterns.structural.adapter.adapter.CompositionVectorGraphicsFromRaster;
import com.stolser.javatraining.designpatterns.structural.adapter.adapter.InheritanceVectorGraphicsFromRaster;
import com.stolser.javatraining.designpatterns.structural.adapter.target.VectorGraphics;

public class Client {
    public static void main(String[] args) {
        usingInheritanceAdapter();
        usingCompositionAdapter();
    }

    private static void usingInheritanceAdapter() {
        System.out.println("------------ Using an inheritance Adapter");
        VectorGraphics graphics = new InheritanceVectorGraphicsFromRaster();
        graphics.drawLine();
        graphics.drawSquare();
        System.out.println("----------------------------------------------------");
    }

    private static void usingCompositionAdapter() {
        System.out.println("------------ Using an composition Adapter");
        VectorGraphics graphics = new CompositionVectorGraphicsFromRaster(new RasterGraphics());
        graphics.drawLine();
        graphics.drawSquare();
        System.out.println("----------------------------------------------------");
    }
}
