package com.stolser.javatraining.project01.model.appliance.tools;

import com.google.common.base.Preconditions;
import com.stolser.javatraining.project01.model.appliance.AbstractElectricalAppliance;
import com.stolser.javatraining.project01.model.appliance.ApplianceType;

/**
 * Provides basic functionality for setting an accumulator. Should be extended by any concrete electrical tool.
 */
public abstract class ElectricalTool extends AbstractElectricalAppliance {
    protected Accumulator accumulator;

    public ElectricalTool(ApplianceType type, String brand, double weight) {
        super(type, brand, weight);
    }

    public void setAccumulator(Accumulator accumulator) {
        Preconditions.checkNotNull("Accumulator cannot be null.", accumulator);
        this.accumulator = accumulator;
    }

    public void switchOnAccumulator() {
        accumulator.switchOn();
    }
}
