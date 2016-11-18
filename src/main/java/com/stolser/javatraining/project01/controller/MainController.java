package com.stolser.javatraining.project01.controller;

import com.stolser.javatraining.project01.model.House;
import com.stolser.javatraining.project01.model.appliance.ElectricalAppliance;
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

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import static com.stolser.javatraining.project01.model.appliance.ApplianceType.*;

public class MainController {
    private Set<ElectricalAppliance> appliances = new HashSet<>();
    private SortingType sortingType;

    public void start() {
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

    }

    private void sortAppliances() {
        askUserAlgorithm();
    }

    private void askUserAlgorithm() {
        System.out.println("Please, choose a sorting algorithm: ");

        for (SortingType type : SortingType.values()) {
            System.out.printf("\t- %s: %d\n", type, type.ordinal());
        }

        int userInput = readUserInput();

        sortingType = getAlgorithmByOrdinal(userInput);
        printAllAppliancesSorted();
    }

    private int readUserInput() {
        Scanner scanner = new Scanner(System.in);
        int input;

        while (true) {
            while (!scanner.hasNextInt()) {
                System.out.println("Please, enter a number.");
                scanner.nextLine();
            }

            input = scanner.nextInt();

            if ((input >= 0) && (input < SortingType.values().length)) {
                break;
            }

            System.out.println("Wrong input! Please, repeat your attempt.");
        }

        scanner.close();

        return input;
    }

    private SortingType getAlgorithmByOrdinal(int ordinal) {
        for (SortingType type : SortingType.values()) {
            if (type.ordinal() == ordinal)
                return type;
        }

        throw new IllegalArgumentException();
    }

    private void printAllAppliancesSorted() {
        System.out.printf("Appliances sorted '%s':\n", sortingType);
        ApplianceUtils.getSorted(appliances, sortingType).forEach(System.out::println);
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
