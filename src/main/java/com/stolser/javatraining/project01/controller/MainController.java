package com.stolser.javatraining.project01.controller;

import com.stolser.javatraining.generalMVC.controller.InputReader;
import com.stolser.javatraining.generalMVC.view.ViewPrinter;
import com.stolser.javatraining.project01.model.ApplianceInitializer;
import com.stolser.javatraining.project01.model.House;
import com.stolser.javatraining.project01.model.appliance.ElectricalAppliance;

import java.io.IOException;
import java.util.Arrays;
import java.util.Set;

import static com.stolser.javatraining.project01.controller.ApplianceUtils.getSorted;
import static java.lang.String.format;

public class MainController {
    private static final String CHOOSE_SORTING_ALGORITHM_TEXT = "Please, choose a sorting algorithm: ";
    private static final String WRONG_INPUT_ERROR_MESSAGE = "Wrong input! Please, repeat your attempt: ";
    private static final String SEPARATOR = "-----------------------------------------------------------";
    private static final String TOTAL_POWER_CONSUMPTION_TEXT = "Total power consumption: %.1f";
    private static final String APPLIANCES_SORTED_TEXT = "Appliances sorted '%s':";
    private Set<ElectricalAppliance> appliances;
    private SortingType sortingType;
    private ViewPrinter out;
    private InputReader in;

    public MainController(ViewPrinter out, InputReader in) {
        this.out = out;
        this.in = in;
    }

    public void start() throws IOException {
        House house = House.newInstance();
        appliances = ApplianceInitializer.getAppliances();

        house.addAppliances(appliances);
        house.switchAllAppliancesOn();
        out.printlnString(format(TOTAL_POWER_CONSUMPTION_TEXT, house.getPowerConsumption()));

        house.switchRandomlyAppliancesOn();
        out.printlnString(format(TOTAL_POWER_CONSUMPTION_TEXT, house.getPowerConsumption()));

        house.switchAllAppliancesOff();
        out.printlnString(format(TOTAL_POWER_CONSUMPTION_TEXT, house.getPowerConsumption()));

        sortAppliances();
        new FilterController(out, in).filterAppliances(appliances);
    }

    private void sortAppliances() {
        do {
            int userInput = askUserAlgorithm();

            if (userInput == -1) {
                break;
            }

            sortingType = SortingType.values()[userInput];
            printAllAppliancesSorted();

        } while (true);
    }

    private int askUserAlgorithm() {
        out.printlnString(CHOOSE_SORTING_ALGORITHM_TEXT);

        Arrays.stream(SortingType.values())
                .forEach(algorithm -> out.printlnString(format("\t- %s: %d", algorithm, algorithm.ordinal())));

        out.printlnString("\t- Exit: -1");

        return readUserInput();
    }

    private int readUserInput() {
        int userInput;

        while (true) {
            userInput = in.readIntValue();

            // -1 --> 'Exit'; [0; values().length) - the ordinals of SortingType elements;
            if ((userInput >= -1) && (userInput < SortingType.values().length)) {
                break;
            }

            out.printString(WRONG_INPUT_ERROR_MESSAGE);
        }

        return userInput;
    }

    private void printAllAppliancesSorted() {
        out.printlnString(format(APPLIANCES_SORTED_TEXT, sortingType));
        getSorted(appliances, sortingType)
                .stream()
                .map(Object::toString)
                .forEach(out::printlnString);

        out.printlnString(SEPARATOR);
    }
}
