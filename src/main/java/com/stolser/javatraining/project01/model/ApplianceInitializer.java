package com.stolser.javatraining.project01.model;

import com.stolser.javatraining.project01.model.appliance.ElectricalAppliance;
import com.stolser.javatraining.project01.model.appliance.audio.AudioSystem;
import com.stolser.javatraining.project01.model.appliance.audio.Speaker;
import com.stolser.javatraining.project01.model.appliance.engine.SimpleMotor;
import com.stolser.javatraining.project01.model.appliance.household.Hoover;
import com.stolser.javatraining.project01.model.appliance.kitchen.CoffeeMaker;
import com.stolser.javatraining.project01.model.appliance.kitchen.Fridge;
import com.stolser.javatraining.project01.model.appliance.kitchen.Oven;
import com.stolser.javatraining.project01.model.appliance.tools.Drill;
import com.stolser.javatraining.project01.model.appliance.tools.ElectricalTool;
import com.stolser.javatraining.project01.model.appliance.tools.LithiumBattery;

import java.util.ArrayList;
import java.util.List;

import static com.stolser.javatraining.project01.model.appliance.ApplianceType.*;

/**
 * Contains static methods for initialization purposes.
 */
public class ApplianceInitializer {
    public static List<ElectricalAppliance> getAppliances() {
        List<ElectricalAppliance> appliances = new ArrayList<>();

        ElectricalAppliance hoover = new Hoover(CLEANING, "Philips", 5.2);
        hoover.setMotor(new SimpleMotor(320));
        hoover.setPrice(150);
        appliances.add(hoover);

        ElectricalAppliance fridge = new Fridge(KITCHEN, "Electrolux", 65);
        fridge.setMotor(new SimpleMotor(420));
        fridge.setPrice(450);
        appliances.add(fridge);

        ElectricalAppliance oven = new Oven(KITCHEN, "Gorenje", 45);
        oven.setMotor(new SimpleMotor(520));
        oven.setPrice(420);
        appliances.add(oven);

        ElectricalAppliance coffeeMaker = new CoffeeMaker(KITCHEN, "Gorenje", 3.5);
        coffeeMaker.setMotor(new SimpleMotor(180));
        coffeeMaker.setPrice(80);
        appliances.add(coffeeMaker);

        ElectricalTool drill = new Drill(TOOL, "Bosch", 1.8);
        drill.setMotor(new SimpleMotor(110));
        drill.setPrice(190);
        drill.setAccumulator(new LithiumBattery(120));
        appliances.add(drill);

        AudioSystem audioSystem = new AudioSystem(OFFICE, "F&D", 7);
        audioSystem.setMotor(new SimpleMotor(180));
        audioSystem.setPrice(210);
        audioSystem.setSpeaker(new Speaker());
        appliances.add(audioSystem);

        ElectricalAppliance oven2 = new Oven(KITCHEN, "Samsung", 40);
        oven2.setMotor(new SimpleMotor(180));
        oven2.setPrice(520);
        appliances.add(oven2);

        return appliances;
    }
}
