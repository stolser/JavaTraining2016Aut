package com.stolser.javatraining.project01.model.appliance.audio;

/**
 * Has an ability to tune the level of produced sounds.
 */
public class Speaker {
    private int volume;

    public void volumeUp() {
        volume++;
    }

    public void volumeDown() {
        volume--;
    }

    public int getVolume() {
        return volume;
    }
}
