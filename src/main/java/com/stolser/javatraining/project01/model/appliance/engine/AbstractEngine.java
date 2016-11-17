package com.stolser.javatraining.project01.model.appliance.engine;

public abstract class AbstractEngine implements Engine {
    private double power;
    protected boolean isRunning;

    public AbstractEngine(double power) {
        this.power = power;
    }

    @Override
    public boolean isRunning() {
        return isRunning;
    }

    @Override
    public double getInputPower() {
        return power;
    }
}
