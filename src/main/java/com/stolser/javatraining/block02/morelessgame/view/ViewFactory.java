package com.stolser.javatraining.block02.morelessgame.view;

public interface ViewFactory {
    ViewGenerator getViewGenerator();
    ViewPrinter getViewPrinter();
}