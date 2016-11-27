package com.stolser.javatraining.project01.model.appliance.engine;

/**
 * An implementation of the {@link EnhancedMotor} interface.
 */
public class SuperMotor extends AbstractMotor implements EnhancedMotor {
    private MotorRegime regime;

    public SuperMotor(double power) {
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

    @Override
    public void setRegime(MotorRegime regime) {
        this.regime = regime;
    }
}
