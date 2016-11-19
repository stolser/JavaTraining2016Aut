package com.stolser.javatraining.project01.model.appliance.audio;

/**
 * Represents functionality for setting and manipulating a speaker.
 * Should by implemented by any appliances that can play music.
 */
public interface Audible {
    /**
     * Starts playing music.
     */
    void playMusic();

    /**
     * Pauses playing music.
     */
    void pauseMusic();

    /**
     * Increases the level of volume.
     */
    void volumeUp();

    /**
     * Decreases the level of volume
     */
    void volumeDown();
}
