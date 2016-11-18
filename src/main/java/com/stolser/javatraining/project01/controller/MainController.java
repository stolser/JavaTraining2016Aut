package com.stolser.javatraining.project01.controller;

import com.stolser.javatraining.project01.model.House;
import com.stolser.javatraining.project01.model.appliance.ElectricalAppliance;
import com.stolser.javatraining.project01.model.appliance.EmptyElectricalAppliance;
import com.stolser.javatraining.project01.model.appliance.audio.Audible;
import com.stolser.javatraining.project01.model.appliance.audio.AudioSystem;
import com.stolser.javatraining.project01.model.appliance.audio.Speaker;
import com.stolser.javatraining.project01.model.appliance.engine.Engine;
import com.stolser.javatraining.project01.model.appliance.engine.SimpleEngine;
import com.stolser.javatraining.project01.model.appliance.household.Hoover;
import com.stolser.javatraining.project01.model.appliance.kitchen.CoffeeMaker;
import com.stolser.javatraining.project01.model.appliance.kitchen.Fridge;
import com.stolser.javatraining.project01.model.appliance.kitchen.Oven;
import com.stolser.javatraining.project01.model.appliance.tools.Accumulator;
import com.stolser.javatraining.project01.model.appliance.tools.Drill;
import com.stolser.javatraining.project01.model.appliance.tools.ElectricalTool;
import com.stolser.javatraining.project01.model.appliance.tools.LithiumBattery;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import static com.stolser.javatraining.project01.controller.ApplianceUtils.getSorted;
import static com.stolser.javatraining.project01.controller.SortingType.*;
import static com.stolser.javatraining.project01.model.appliance.ApplianceType.*;

public class MainController {
    private static final String FILTER_PARAMS_FILENAME = "src\\main\\resources\\" +
            "project01\\filterParams.properties";
    private static final String CHOOSE_SORTING_ALGORITHM_TEXT = "Please, choose a sorting algorithm: ";
    private static final String ENTER_NUMBER_TEXT = "Please, enter a number.";
    private static final String WRONG_INPUT_ERROR_MESSAGE = "Wrong input! Please, repeat your attempt.";
    private static final String FILTERPARAMS_PRICE_MIN = "filterparams.price.min";
    private static final String FILTERPARAMS_PRICE_MAX = "filterparams.price.max";
    private static final String FILTERPARAMS_POWER_MIN = "filterparams.power.min";
    private static final String FILTERPARAMS_POWER_MAX = "filterparams.power.max";
    private static final String FILTERPARAMS_WEIGHT_MIN = "filterparams.weight.min";
    private static final String FILTERPARAMS_WEIGHT_MAX = "filterparams.weight.max";
    private static final String APPLIANCES_LIMITED_BY_PRICE_TEXT = "----------- Appliances limited by the price = [%.2f; %.2f]\n";
    private static final String SEPARATOR = "-----------------------------------------------------------";
    private static final String APPLIANCES_LIMITED_BY_POWER_TEXT = "----------- Appliances limited by the power = [%.2f; %.2f]\n";
    private static final String APPLIANCES_LIMITED_BY_WEIGHT_TEXT = "----------- Appliances limited by the weight = [%.2f; %.2f]\n";
    private Set<ElectricalAppliance> appliances = new HashSet<>();
    private SortingType sortingType;

    public void start() throws IOException {
        House house = new House();
        initializeAppliances();

        house.addAppliances(appliances);
        house.switchAllAppliancesOn();
        System.out.printf("Total power consumption: %.1f\n", house.calculateTotalPowerConsumption());

        house.switchRandomlyAppliancesOn();
        System.out.printf("Total power consumption: %.1f\n", house.calculateTotalPowerConsumption());

        house.switchAllAppliancesOff();
        System.out.printf("Total power consumption: %.1f\n", house.calculateTotalPowerConsumption());

        sortAppliances();
        searchAppliances();

    }

    private void sortAppliances() {
        sortingType = SortingType.values()[askUserAlgorithm()];
        printAllAppliancesSorted();
    }

    private int askUserAlgorithm() {
        System.out.println(CHOOSE_SORTING_ALGORITHM_TEXT);

        for (SortingType type : SortingType.values()) {
            System.out.printf("\t- %s: %d\n", type, type.ordinal());
        }

        System.out.printf("\t- Exit: -1\n");

        return readUserInput();
    }

    private int readUserInput() {
        Scanner scanner = new Scanner(System.in);
        int input;

        while (true) {
            while (!scanner.hasNextInt()) {
                System.out.println(ENTER_NUMBER_TEXT);
                scanner.nextLine();
            }

            input = scanner.nextInt();

            if (input == -1) {
                scanner.close();
                break;
            }

            if ((input >= 0) && (input < SortingType.values().length)) {
                scanner.close();
                break;
            }

            System.out.println(WRONG_INPUT_ERROR_MESSAGE);
        }

        scanner.close();

        return input;
    }

    private void searchAppliances() throws IOException {
        String filename = FILTER_PARAMS_FILENAME;
        InputStream input = new FileInputStream(filename);
        Properties properties = new Properties();
        properties.load(input);

        double priceMin = Double.valueOf(properties.getProperty(FILTERPARAMS_PRICE_MIN));
        double priceMax = Double.valueOf(properties.getProperty(FILTERPARAMS_PRICE_MAX));
        double powerMin = Double.valueOf(properties.getProperty(FILTERPARAMS_POWER_MIN));
        double powerMax = Double.valueOf(properties.getProperty(FILTERPARAMS_POWER_MAX));
        double weightMin = Double.valueOf(properties.getProperty(FILTERPARAMS_WEIGHT_MIN));
        double weightMax = Double.valueOf(properties.getProperty(FILTERPARAMS_WEIGHT_MAX));

        EmptyElectricalAppliance lowerLimits = new EmptyElectricalAppliance();
        EmptyElectricalAppliance upperLimits = new EmptyElectricalAppliance();
        NavigableSet<ElectricalAppliance> result = null;

        if (priceMin < priceMax) {
            lowerLimits.setPrice(priceMin);
            upperLimits.setPrice(priceMax);
            result = getSorted(appliances, BY_PRICE_ASC)
                    .subSet(lowerLimits, true, upperLimits, true);
            System.out.printf(APPLIANCES_LIMITED_BY_PRICE_TEXT,
                    priceMin, priceMax);
            result.forEach(System.out::println);
            System.out.println(SEPARATOR);

        }

        if (powerMin < powerMax) {
            lowerLimits.setMaxPower(powerMin);
            upperLimits.setMaxPower(powerMax);
            result = getSorted(result, BY_POWER_ASC)
                    .subSet(lowerLimits, true, upperLimits, true);
            System.out.printf(APPLIANCES_LIMITED_BY_POWER_TEXT,
                    powerMin, powerMax);
            result.forEach(System.out::println);
            System.out.println(SEPARATOR);
        }

        if (weightMin < weightMax) {
            lowerLimits.setWeight(weightMin);
            upperLimits.setWeight(weightMax);
            result = getSorted(result, BY_WEIGHT_ASC)
                    .subSet(lowerLimits, true, upperLimits, true);
            System.out.printf(APPLIANCES_LIMITED_BY_WEIGHT_TEXT,
                    weightMin, weightMax);
            result.forEach(System.out::println);
            System.out.println(SEPARATOR);
        }
    }

    private void printAllAppliancesSorted() {
        System.out.printf("Appliances sorted '%s':\n", sortingType);
        getSorted(appliances, sortingType).forEach(System.out::println);
    }

    private void initializeAppliances() {
        Engine engine1 = new SimpleEngine(320);
        Engine engine2 = new SimpleEngine(420);
        Engine engine3 = new SimpleEngine(520);
        Engine engine4 = new SimpleEngine(50);
        Engine engine5 = new SimpleEngine(180);
        Engine engine6 = new SimpleEngine(40);
        Engine engine7 = new SimpleEngine(180);

        Speaker speaker = new Speaker();
        Accumulator accumulator = new LithiumBattery(120);

        ElectricalAppliance hoover = new Hoover(CLEANING, "Philips", 5.2);
        hoover.setEngine(engine1);
        hoover.setPrice(150);
        appliances.add(hoover);

        ElectricalAppliance fridge = new Fridge(KITCHEN, "Electrolux", 65);
        fridge.setEngine(engine2);
        fridge.setPrice(450);
        appliances.add(fridge);

        ElectricalAppliance oven = new Oven(KITCHEN, "Gorenje", 45);
        oven.setEngine(engine3);
        oven.setPrice(420);
        appliances.add(oven);

        ElectricalAppliance coffeeMaker = new CoffeeMaker(KITCHEN, "Gorenje", 3.5);
        coffeeMaker.setEngine(engine4);
        coffeeMaker.setPrice(80);
        appliances.add(coffeeMaker);

        ElectricalAppliance drill = new Drill(TOOL, "Bosch", 1.8);
        drill.setEngine(engine5);
        drill.setPrice(190);
        ((ElectricalTool) drill).setAccumulator(accumulator);
        appliances.add(drill);

        ElectricalAppliance audioSystem = new AudioSystem(OFFICE, "F&D", 7);
        audioSystem.setEngine(engine6);
        audioSystem.setPrice(210);
        ((Audible) audioSystem).setSpeaker(speaker);
        appliances.add(audioSystem);

        ElectricalAppliance oven2 = new Oven(KITCHEN, "Samsung", 40);
        oven2.setEngine(engine7);
        oven2.setPrice(520);
        appliances.add(oven2);

    }
}
