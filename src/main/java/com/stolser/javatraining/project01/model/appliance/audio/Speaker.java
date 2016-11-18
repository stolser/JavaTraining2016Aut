package com.stolser.javatraining.project01.model.appliance.audio;

public class Speaker {
    private static final String SPEAKER_VOLUME_UP_TEXT = "Speaker: volume up";
    private static final String SPEAKER_VOLUME_DOWN_TEXT = "Speaker: volume down";
    private int volume;

    public void volumeUp() {
        volume++;
        System.out.println(SPEAKER_VOLUME_UP_TEXT);
    }

    public void volumeDown() {
        volume--;
        System.out.println(SPEAKER_VOLUME_DOWN_TEXT);
    }

    public int getVolume() {
        return volume;
    }
}
