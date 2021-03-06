package com.stolser.javatraining.project01.controller;

import com.stolser.javatraining.generalMVC.controller.InputReader;
import com.stolser.javatraining.generalMVC.view.ViewPrinter;
import com.stolser.javatraining.project01.model.ApplianceInitializer;
import com.stolser.javatraining.project01.model.House;
import com.stolser.javatraining.project01.model.appliance.ElectricalAppliance;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static com.stolser.javatraining.project01.controller.ApplianceUtils.sortAppliances;
import static java.lang.String.format;

/**
 * Contains the main logic of the whole application. Uses {@link ApplianceUtils} and
 * {@link FilteringController} to sort and filter appliances.
 */
public class MainController {
    private static final String CHOOSE_SORTING_ORDER_TEXT = "Please, choose a sorting order: ";
    private static final String WRONG_INPUT_ERROR_MESSAGE = "Wrong input! Please, repeat your attempt: ";
    private static final String SEPARATOR = "-----------------------------------------------------------";
    private static final String TOTAL_POWER_CONSUMPTION_TEXT = "Total power consumption: %.1f";
    private static final String APPLIANCES_SORTED_TEXT = "Appliances sorted '%s':";
    /**
     * A set of appliances to be sorted and filtered.
     */
    private List<ElectricalAppliance> appliances;
    private SortingOrder sortingOrder;
    private FilteringController filteringController;
    private ViewPrinter out;
    private InputReader in;

    public MainController(ViewPrinter out, InputReader in) {
        this.out = out;
        this.in = in;
        filteringController = new FilteringController(out);
    }

    /**
     * The main enter point of this app. Performs the following: initializes appliances; switches them;
     * calculates and prints the total power consumption; sorts appliances according to a sorting order
     * picked by a user; filtered out appliances according to the parameters read from a file.
     *
     * @throws IOException if an exception errors during reading params from a file
     */
    public void start() throws IOException {
        House house = House.newEmptyHouse();
        appliances = ApplianceInitializer.getAppliances();

        house.addAppliances(appliances);
        house.switchAllAppliancesOn();
        out.printlnString(format(TOTAL_POWER_CONSUMPTION_TEXT, house.getPowerConsumption()));

        house.switchRandomlyAppliancesOn();
        out.printlnString(format(TOTAL_POWER_CONSUMPTION_TEXT, house.getPowerConsumption()));

        house.switchAllAppliancesOff();
        out.printlnString(format(TOTAL_POWER_CONSUMPTION_TEXT, house.getPowerConsumption()));

        sortAppliancesAndPrintResult();

        filteringController.filterAppliancesAndPrintResult(appliances);
    }

    private void sortAppliancesAndPrintResult() {
        do {
            int userInput = askUserSortingOrder();

            if (userChoseExit(userInput)) {
                break;
            }

            sortingOrder = SortingOrder.values()[userInput];
            printAllAppliancesSorted();

        } while (true);
    }

    private int askUserSortingOrder() {
        out.printlnString(CHOOSE_SORTING_ORDER_TEXT);

        Arrays.stream(SortingOrder.values())
                .forEach(sortingOrder -> out.printlnString(format("\t- %s: %d", sortingOrder,
                        sortingOrder.ordinal())));

        out.printlnString("\t- Exit: -1");

        return readUserInput();
    }

    private int readUserInput() {
        int userInput;

        while (true) {
            userInput = in.readIntValue();

            if (userChoseSortingOrder(userInput)
                    || userChoseExit(userInput)) {
                break;
            }

            out.printString(WRONG_INPUT_ERROR_MESSAGE);
        }

        return userInput;
    }

    private boolean userChoseSortingOrder(int userInput) {
        return (userInput >= 0) && (userInput < SortingOrder.values().length);
    }

    private boolean userChoseExit(int userInput) {
        return userInput == -1;
    }

    private void printAllAppliancesSorted() {
        out.printlnString(format(APPLIANCES_SORTED_TEXT, sortingOrder));

        sortAppliances(appliances, sortingOrder);
        appliances.stream()
                .map(Object::toString)
                .forEach(out::printlnString);

        out.printlnString(SEPARATOR);
    }
}
