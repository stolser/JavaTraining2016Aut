package com.stolser.javatraining.project01.model.appliance.engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An implementation of the {@link EnhancedMotor} interface.
 */
public class SuperMotor extends AbstractMotor implements EnhancedMotor {
    private static final Logger LOGGER = LoggerFactory.getLogger(SuperMotor.class);
    private static final String SUPER_ENGINE_IS_STARTING_TEXT = "SuperMotor is starting...";
    private static final String SUPER_ENGINE_IS_STOPPING_TEXT = "SuperMotor is stopping...";
    private MotorRegime regime;

    public SuperMotor(double power) {
        super(power);
    }

    @Override
    public void start() {
        LOGGER.debug(SUPER_ENGINE_IS_STARTING_TEXT);
        isRunning = true;
    }

    @Override
    public void stop() {
        LOGGER.debug(SUPER_ENGINE_IS_STOPPING_TEXT);
        isRunning = false;
    }

    @Override
    public void setRegime(MotorRegime regime) {
        this.regime = regime;
    }
}
