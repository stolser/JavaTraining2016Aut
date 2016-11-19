package com.stolser.javatraining.project01.model.appliance.engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleEngine extends AbstractEngine {
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleEngine.class);
    private static final String SIMPLE_ENGINE_IS_STARTING_TEXT = "SimpleEngine is starting...";
    private static final String SIMPLE_ENGINE_IS_STOPPING_TEXT = "SimpleEngine is stopping...";

    public SimpleEngine(double power) {
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
