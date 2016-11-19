package com.stolser.javatraining.project01.model.appliance.audio;

import com.stolser.javatraining.project01.model.appliance.AbstractElectricalAppliance;
import com.stolser.javatraining.project01.model.appliance.ApplianceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.google.common.base.Preconditions.checkNotNull;

public class AudioSystem extends AbstractElectricalAppliance implements Audible {
    private static final Logger LOGGER = LoggerFactory.getLogger(AudioSystem.class);
    /**
     * Affects the current input power of this electrical appliance.<br />
     * Is used during calculating the current power as a multiplication parameter along with the max power.
     */
    private static final double EFFICIENCY_RATION = 0.8;
    private static final String AUDIO_SYSTEM_IS_SWITCHED_ON = "AudioSystem (%s) is switched on.";
    private static final String AUDIO_SYSTEM_IS_SWITCHED_OFF = "AudioSystem (%s) is switched off.";
    private static final String AUDIO_SYSTEM_IS_PLAYING_MUSIC = "AudioSystem is playing music at volume level = %d";
    private static final String AUDIO_SYSTEM_MUSIC_IS_STOPPED = "AudioSystem: music is stopped.";
    private Speaker speaker;

    public AudioSystem(ApplianceType type, String brand, double weight) {
        super(type, brand, weight);
    }

    @Override
    public void switchOn() {
        LOGGER.debug(String.format(AUDIO_SYSTEM_IS_SWITCHED_ON, this));
        isSwitchedOn = true;
        currentPower = getMaxPower() * EFFICIENCY_RATION;
    }

    @Override
    public void switchOff() {
        LOGGER.debug(String.format(AUDIO_SYSTEM_IS_SWITCHED_OFF, this));
        isSwitchedOn = false;
        currentPower = 0;
    }

    @Override
    public void playMusic() {
        if (isSwitchedOn) {
            LOGGER.debug(String.format(AUDIO_SYSTEM_IS_PLAYING_MUSIC, speaker.getVolume()));
        }
    }

    @Override
    public void pauseMusic() {
        LOGGER.debug(AUDIO_SYSTEM_MUSIC_IS_STOPPED);
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
