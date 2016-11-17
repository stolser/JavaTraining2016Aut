package com.stolser.javatraining.project01.model.appliance;

import com.stolser.javatraining.project01.model.appliance.engine.Engine;

public interface ElectricalAppliance extends Appliance, Switchable {
    void setEngine(Engine engine);
    ApplianceType getType();
}
