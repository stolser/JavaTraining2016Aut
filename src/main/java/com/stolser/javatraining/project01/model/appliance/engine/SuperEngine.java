package com.stolser.javatraining.project01.model.appliance.engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SuperEngine extends AbstractEngine implements EnhancedEngine {
    private static final Logger LOGGER = LoggerFactory.getLogger(SuperEngine.class);
    private static final String SUPER_ENGINE_IS_STARTING_TEXT = "SuperEngine is starting...";
    private static final String SUPER_ENGINE_IS_STOPPING_TEXT = "SuperEngine is stopping...";
    private EngineRegime regime;

    public SuperEngine(double power) {
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
    public void setRegime(EngineRegime regime) {
        this.regime = regime;
    }
}
