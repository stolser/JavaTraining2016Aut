package com.stolser.javatraining.project01.model.appliance.tools;

import com.stolser.javatraining.project01.model.appliance.AbstractElectricalAppliance;
import com.stolser.javatraining.project01.model.appliance.ApplianceType;

public abstract class ElectricalTool extends AbstractElectricalAppliance {
    protected Accumulator accumulator;

    public ElectricalTool(ApplianceType type, String brand, double weight) {
        super(type, brand, weight);
    }

    public void setAccumulator(Accumulator accumulator) {
        this.accumulator = accumulator;
    }
}