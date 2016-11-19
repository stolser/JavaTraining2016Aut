package com.stolser.javatraining.project01.model.appliance.engine;

/**
 * Provides a public API to manipulate components that convert electricity into motion.
 */
public interface Motor {
    /**
     * Makes this motor working by consuming electricity.
     */
    void start();

    /**
     * Stops this motor.
     */
    void stop();

    /**
     * @return true if this motor works consuming electricity; false - otherwise
     */
    boolean isRunning();

    /**
     * @return the max power of this motor
     */
    double getInputPower();
}
