package com.stolser.javatraining.project01.model.appliance.engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A basic implementation of the {@link Motor} interface.
 */
public class SimpleMotor extends AbstractMotor {
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleMotor.class);
    private static final String SIMPLE_ENGINE_IS_STARTING_TEXT = "SimpleMotor is starting...";
    private static final String SIMPLE_ENGINE_IS_STOPPING_TEXT = "SimpleMotor is stopping...";

    public SimpleMotor(double power) {
        super(power);
    }

    @Override
    public void start() {
        LOGGER.debug(SIMPLE_ENGINE_IS_STARTING_TEXT);
        isRunning = true;
    }

    @Override
    public void stop() {
        LOGGER.debug(SIMPLE_ENGINE_IS_STOPPING_TEXT);
        isRunning = false;
    }
}
