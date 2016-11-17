package com.stolser.javatraining.generalMVC.controller;

/**
 * An abstraction for input data source.
 */
public interface InputReader {
    int readIntValue();
    String readLine();
    boolean readYesNoValue();
}
