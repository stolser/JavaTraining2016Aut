package com.stolser.javatraining.project01.model.appliance.engine;

import com.google.common.base.Preconditions;

public abstract class AbstractEngine implements Engine {
    private double power;
    protected boolean isRunning;

    public AbstractEngine(double power) {
        Preconditions.checkArgument(power > 0, "Power cannot be negative or zero.");
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
