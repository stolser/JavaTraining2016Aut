package com.stolser.javatraining.project01.model.appliance.audio;

import com.stolser.javatraining.project01.model.appliance.AbstractElectricalAppliance;
import com.stolser.javatraining.project01.model.appliance.ApplianceType;

import static com.google.common.base.Preconditions.checkNotNull;

public class AudioSystem extends AbstractElectricalAppliance implements Audible {
    private static final double EFFICIENCY_RATION = 0.8;
    private Speaker speaker;

    public AudioSystem(ApplianceType type, String brand, double weight) {
        super(type, brand, weight);
    }

    @Override
    public void switchOn() {
        System.out.printf("AudioSystem (%s) is switched on.\n", this);
        isSwitchedOn = true;
        currentPower = getMaxPower() * EFFICIENCY_RATION;
    }

    @Override
    public void switchOff() {
        System.out.printf("AudioSystem (%s) is switched off.\n", this);
        isSwitchedOn = false;
        currentPower = 0;
    }

    @Override
    public void playMusic() {
        if (isSwitchedOn) {
            System.out.printf("AudioSystem is playing music at volume level = %d\n", speaker.getVolume());
        }
    }

    @Override
    public void pauseMusic() {
        System.out.println("AudioSystem: music is stopped.");
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

    @Override
    public void setSpeaker(Speaker speaker) {
        checkNotNull(speaker);
        this.speaker = speaker;
    }
}
