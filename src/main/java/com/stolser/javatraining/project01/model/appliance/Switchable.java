package com.stolser.javatraining.project01.model.appliance;

/**
 * Provides a public API for items that can be switch on/off.
 */
public interface Switchable {
    void switchOn();
    void switchOff();

    /**
     * @return {@code true} if this item is switch on, and {@code false} otherwise
     */
    boolean isOn();
}
