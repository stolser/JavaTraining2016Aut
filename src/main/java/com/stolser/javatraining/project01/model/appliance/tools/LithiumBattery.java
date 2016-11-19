package com.stolser.javatraining.project01.model.appliance.tools;

import com.google.common.base.Preconditions;

public class LithiumBattery implements Accumulator {
    private boolean isOn;
    private int currentCharge;
    private int chargeCapacity;

    public LithiumBattery(int chargeCapacity) {
        Preconditions.checkNotNull("Charge capacity cannot be less than zero.", chargeCapacity);
        this.chargeCapacity = chargeCapacity;
    }

    @Override
    public void charge() {
        if (batteryCanBeChargedMore()) {
            currentCharge++;
        }
    }

    private boolean batteryCanBeChargedMore() {
        return currentCharge < chargeCapacity;
    }

    @Override
    public void switchOn() {
        isOn = true;
    }

    @Override
    public void switchOff() {
        isOn = false;
    }

    @Override
    public boolean isOn() {
        return isOn;
    }

    @Override
    public void usePower() {
        currentCharge--;
    }

    @Override
    public int getCurrentCharge() {
        return currentCharge;
    }
}
