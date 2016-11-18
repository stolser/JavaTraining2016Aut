package com.stolser.javatraining.project01.model.appliance.audio;

import com.stolser.javatraining.project01.model.appliance.AbstractElectricalAppliance;
import com.stolser.javatraining.project01.model.appliance.ApplianceType;

import static com.google.common.base.Preconditions.checkNotNull;

public class AudioSystem extends AbstractElectricalAppliance implements Audible {
    private static final double EFFICIENCY_RATION = 0.8;
    private static final String AUDIO_SYSTEM_IS_SWITCHED_ON = "AudioSystem (%s) is switched on.\n";
    private static final String AUDIO_SYSTEM_IS_SWITCHED_OFF = "AudioSystem (%s) is switched off.\n";
    private static final String AUDIO_SYSTEM_IS_PLAYING_MUSIC = "AudioSystem is playing music at volume level = %d\n";
    private static final String AUDIO_SYSTEM_MUSIC_IS_STOPPED = "AudioSystem: music is stopped.";
    private Speaker speaker;

    public AudioSystem(ApplianceType type, String brand, double weight) {
        super(type, brand, weight);
    }

    @Override
    public void switchOn() {
        System.out.printf(AUDIO_SYSTEM_IS_SWITCHED_ON, this);
        isSwitchedOn = true;
        currentPower = getMaxPower() * EFFICIENCY_RATION;
    }

    @Override
    public void switchOff() {
        System.out.printf(AUDIO_SYSTEM_IS_SWITCHED_OFF, this);
        isSwitchedOn = false;
        currentPower = 0;
    }

    @Override
    public void playMusic() {
        if (isSwitchedOn) {
            System.out.printf(AUDIO_SYSTEM_IS_PLAYING_MUSIC, speaker.getVolume());
        }
    }

    @Override
    public void pauseMusic() {
        System.out.println(AUDIO_SYSTEM_MUSIC_IS_STOPPED);
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
