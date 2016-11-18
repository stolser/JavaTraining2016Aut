package com.stolser.javatraining.project01.model.appliance.audio;

/**
 * Represents functionality for setting and manipulating a speaker.
 */
public interface Audible {
    void playMusic();
    void pauseMusic();
    void volumeUp();
    void volumeDown();
    void setSpeaker(Speaker speaker);
}
