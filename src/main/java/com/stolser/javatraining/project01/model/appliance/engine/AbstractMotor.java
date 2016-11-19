package com.stolser.javatraining.project01.model.appliance.engine;

import com.google.common.base.Preconditions;

/**
 * Provides implementation of some methods with general behavior.
 */
public abstract class AbstractMotor implements Motor {
    private double power;
    protected boolean isRunning;

    public AbstractMotor(double power) {
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
