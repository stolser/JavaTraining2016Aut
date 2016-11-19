package com.stolser.javatraining.project01.model.appliance.engine;

/**
 * Adds an ability to set a motor regime.
 */
public interface EnhancedMotor extends Motor {
    void setRegime(MotorRegime regime);
}
