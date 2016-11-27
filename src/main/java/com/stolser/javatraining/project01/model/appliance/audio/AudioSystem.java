package com.stolser.javatraining.project01.model.appliance.audio;

import com.stolser.javatraining.project01.model.appliance.AbstractElectricalAppliance;
import com.stolser.javatraining.project01.model.appliance.ApplianceType;

import static com.google.common.base.Preconditions.checkNotNull;

public class AudioSystem extends AbstractElectricalAppliance implements Audible {
    /**
     * Affects the current input power of this electrical appliance.<br />
     * Is used during calculating the current power as a multiplication parameter along with the max power.
     */
    private static final double EFFICIENCY_RATION = 0.8;
    private Speaker speaker;

    public AudioSystem(ApplianceType type, String brand, double weight) {
        super(type, brand, weight);
    }

    @Override
    public void switchOn() {
        isSwitchedOn = true;
    }

    @Override
    public void switchOff() {
        isSwitchedOn = false;
    }

    @Override
    public double getCurrentPower() {
        return isSwitchedOn ? (getMaxPower() * EFFICIENCY_RATION) : 0;
    }

    @Override
    public void playMusic() {
        if (isSwitchedOn) {
            // play music
        }
    }

    @Override
    public void pauseMusic() {
        // pause music
    }

    @Override
    public void volumeUp() {
        if (isSwitchedOn) {
            speaker.volumeUp();
        }
    }

    @Override
    public void volumeDown() {
        if (isSwitchedOn) {
            speaker.volumeDown();
        }
    }

    public void setSpeaker(Speaker speaker) {
        checkNotNull(speaker);
        this.speaker = speaker;
    }
}
