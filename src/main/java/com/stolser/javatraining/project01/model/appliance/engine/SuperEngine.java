package com.stolser.javatraining.project01.model.appliance.engine;

public class SuperEngine extends AbstractEngine implements EnhancedEngine {
    private EngineRegime regime;

    public SuperEngine(double power) {
        super(power);
    }

    @Override
    public void start() {
        System.out.println("SuperEngine is starting...");
        isRunning = true;
    }

    @Override
    public void stop() {
        System.out.println("SuperEngine is stopping...");
        isRunning = false;
    }

    @Override
    public void setRegime(EngineRegime regime) {
        this.regime = regime;
    }
}
