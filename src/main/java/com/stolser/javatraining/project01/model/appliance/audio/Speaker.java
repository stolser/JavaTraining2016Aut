package com.stolser.javatraining.project01.model.appliance.audio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Has an ability to tune the level of produced sounds.
 */
public class Speaker {
    private static final Logger LOGGER = LoggerFactory.getLogger(Speaker.class);
    private static final String SPEAKER_VOLUME_UP_TEXT = "Speaker: volume up";
    private static final String SPEAKER_VOLUME_DOWN_TEXT = "Speaker: volume down";
    private int volume;

    public void volumeUp() {
        volume++;
        LOGGER.debug(SPEAKER_VOLUME_UP_TEXT);
    }

    public void volumeDown() {
        volume--;
        LOGGER.debug(SPEAKER_VOLUME_DOWN_TEXT);
    }

    public int getVolume() {
        return volume;
    }
}
