package com.stolser.javatraining.project01.model;

import com.stolser.javatraining.project01.model.appliance.ElectricalAppliance;
import com.stolser.javatraining.project01.model.appliance.audio.Audible;
import com.stolser.javatraining.project01.model.appliance.audio.AudioSystem;
import com.stolser.javatraining.project01.model.appliance.audio.Speaker;
import com.stolser.javatraining.project01.model.appliance.engine.SimpleEngine;
import com.stolser.javatraining.project01.model.appliance.household.Hoover;
import com.stolser.javatraining.project01.model.appliance.kitchen.CoffeeMaker;
import com.stolser.javatraining.project01.model.appliance.kitchen.Fridge;
import com.stolser.javatraining.project01.model.appliance.kitchen.Oven;
import com.stolser.javatraining.project01.model.appliance.tools.Drill;
import com.stolser.javatraining.project01.model.appliance.tools.ElectricalTool;
import com.stolser.javatraining.project01.model.appliance.tools.LithiumBattery;

import java.util.HashSet;
import java.util.Set;

import static com.stolser.javatraining.project01.model.appliance.ApplianceType.*;

public class ApplianceInitializer {
    public static Set<ElectricalAppliance> getAppliances() {
        Set<ElectricalAppliance> appliances = new HashSet<>();

        ElectricalAppliance hoover = new Hoover(CLEANING, "Philips", 5.2);
        hoover.setEngine(new SimpleEngine(320));
        hoover.setPrice(150);
        appliances.add(hoover);

        ElectricalAppliance fridge = new Fridge(KITCHEN, "Electrolux", 65);
        fridge.setEngine(new SimpleEngine(420));
        fridge.setPrice(450);
        appliances.add(fridge);

        ElectricalAppliance oven = new Oven(KITCHEN, "Gorenje", 45);
        oven.setEngine(new SimpleEngine(520));
        oven.setPrice(420);
        appliances.add(oven);

        ElectricalAppliance coffeeMaker = new CoffeeMaker(KITCHEN, "Gorenje", 3.5);
        coffeeMaker.setEngine(new SimpleEngine(50));
        coffeeMaker.setPrice(80);
        appliances.add(coffeeMaker);

        ElectricalAppliance drill = new Drill(TOOL, "Bosch", 1.8);
        drill.setEngine(new SimpleEngine(180));
        drill.setPrice(190);
        ((ElectricalTool) drill).setAccumulator(new LithiumBattery(120));
        appliances.add(drill);

        ElectricalAppliance audioSystem = new AudioSystem(OFFICE, "F&D", 7);
        audioSystem.setEngine(new SimpleEngine(40));
        audioSystem.setPrice(210);
        ((Audible) audioSystem).setSpeaker(new Speaker());
        appliances.add(audioSystem);

        ElectricalAppliance oven2 = new Oven(KITCHEN, "Samsung", 40);
        oven2.setEngine(new SimpleEngine(180));
        oven2.setPrice(520);
        appliances.add(oven2);

        return appliances;
    }
}
