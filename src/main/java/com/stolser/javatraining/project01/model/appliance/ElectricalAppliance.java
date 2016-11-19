package com.stolser.javatraining.project01.model.appliance;

import com.stolser.javatraining.project01.model.appliance.engine.Motor;

/**
 * Provides a public API for all electrical appliances by combining several interfaces and
 * adding some new functionality, such as having a motor and a type.
 */
public interface ElectricalAppliance extends Appliance, Switchable {
    ApplianceType getType();
    void setMotor(Motor motor);
    double getMaxPower();
    double getCurrentPower();
}
