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

import java.util.ArrayList;
import java.util.List;

import static com.stolser.javatraining.project01.model.appliance.ApplianceType.*;

public class MainController {
    private List<ElectricalAppliance> appliances = new ArrayList<>();

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
        appliances.add(hoover);

        ElectricalAppliance fridge = new Fridge(KITCHEN, "Electrolux", 65);
        fridge.setEngine(engine2);
        appliances.add(fridge);

        ElectricalAppliance oven = new Oven(KITCHEN, "Gorenje", 45);
        oven.setEngine(engine3);
        appliances.add(oven);

        ElectricalAppliance coffeeMaker = new CoffeeMaker(KITCHEN, "Gorenje", 3.5);
        coffeeMaker.setEngine(engine4);
        appliances.add(coffeeMaker);

        ElectricalAppliance drill = new Drill(TOOL, "Bosch", 1.8);
        drill.setEngine(engine5);
        ((ElectricalTool)drill).setAccumulator(accumulator);
        appliances.add(drill);

        ElectricalAppliance audioSystem = new AudioSystem(OFFICE, "F&D", 7);
        audioSystem.setEngine(engine6);
        ((Audible)audioSystem).setSpeaker(speaker);
        appliances.add(audioSystem);

        ElectricalAppliance oven2 = new Oven(KITCHEN, "Samsung", 40);
        oven2.setEngine(engine7);
        appliances.add(oven2);

    }
}
