package com.stolser.javatraining.project01.model.appliance.engine;

/**
 * A basic implementation of the {@link Motor} interface.
 */
public class SimpleMotor extends AbstractMotor {
    public SimpleMotor(double power) {
        super(power);
    }

    @Override
    public void start() {
        isRunning = true;
    }

    @Override
    public void stop() {
        isRunning = false;
    }
}
