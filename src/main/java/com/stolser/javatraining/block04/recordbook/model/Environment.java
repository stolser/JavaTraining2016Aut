package com.stolser.javatraining.block04.recordbook.model;

import com.stolser.javatraining.block04.recordbook.view.ConsoleViewFactory;
import com.stolser.javatraining.block04.recordbook.view.ViewFactory;
import com.stolser.javatraining.block04.recordbook.view.ViewGenerator;
import com.stolser.javatraining.generalMVC.controller.ConsoleInputReader;
import com.stolser.javatraining.generalMVC.controller.InputReader;
import com.stolser.javatraining.generalMVC.view.ViewPrinter;

/**
 * A convenient class encapsulating classes for working with input and output
 * for this instance of the application.
 */
public class Environment {
    private InputReader inputReader;
    private ViewPrinter viewPrinter;
    private ViewGenerator viewGenerator;

    private Environment(InputReader inputReader, ViewPrinter viewPrinter, ViewGenerator viewGenerator) {
        this.inputReader = inputReader;
        this.viewPrinter = viewPrinter;
        this.viewGenerator = viewGenerator;
    }

    public static Environment newInstance() {
        ViewFactory viewFactory = ConsoleViewFactory.newInstance();
        ViewGenerator viewGenerator = viewFactory.getViewGenerator();
        ViewPrinter viewPrinter = viewFactory.getViewPrinter();
        InputReader inputReader = new ConsoleInputReader(viewPrinter);

        return new Environment(inputReader, viewPrinter, viewGenerator);
    }

    public InputReader getInputReader() {
        return inputReader;
    }

    public ViewPrinter getViewPrinter() {
        return viewPrinter;
    }

    public ViewGenerator getViewGenerator() {
        return viewGenerator;
    }
}
