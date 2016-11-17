package com.stolser.javatraining.project01.model.appliance.engine;

public class SimpleEngine extends AbstractEngine {

    public SimpleEngine(double power) {
        super(power);
    }

    @Override
    public void start() {
        System.out.println("SimpleEngine is starting...");
        isRunning = true;
    }

    @Override
    public void stop() {
        System.out.println("SimpleEngine is stopping...");
        isRunning = false;
    }
}
