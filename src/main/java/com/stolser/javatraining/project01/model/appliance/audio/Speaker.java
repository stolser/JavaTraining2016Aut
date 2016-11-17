package com.stolser.javatraining.project01.model.appliance.audio;

public class Speaker {
    private int volume;
    public void volumeUp() {
        volume++;
        System.out.println("Speaker: volume up");
    }

    public void volumeDown() {
        volume--;
        System.out.println("Speaker: volume down");
    }

    public int getVolume() {
        return volume;
    }
}
