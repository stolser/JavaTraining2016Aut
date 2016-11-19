package com.stolser.javatraining.project01.model.appliance.tools;

/**
 * Provides a public API for components representing energy sources, such as batteries.
 */
public interface Accumulator {
    /**
     * Increases the charge of this accumulator if it is lower than the charge capacity.
     */
    void charge();

    /**
     * Prepares this accumulator to be used as a source of electricity.<br />
     * Only a switched on accumulator can be used.
     */
    void switchOn();

    /**
     * Switches off this accumulator making it impossible to use it as a source of electricity.
     */
    void switchOff();

    /**
     * @return {@code true} if this accumulator is switched on
     */
    boolean isOn();

    /**
     * Uses this accumulator as a source of electricity decreasing it's charge.
     */
    void usePower();
    int getCurrentCharge();
}
