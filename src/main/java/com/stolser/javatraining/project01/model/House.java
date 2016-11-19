package com.stolser.javatraining.project01.model;

import com.google.common.base.Preconditions;
import com.stolser.javatraining.project01.model.appliance.ElectricalAppliance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class House {
    private static final Logger LOGGER = LoggerFactory.getLogger(House.class);
    private static final String ALL_APPLIANCES_ON_TEXT = "All appliances have been switched on.";
    private static final String ALL_APPLIANCES_OFF_TEXT = "All appliances have been switched off.";
    private static final String SWITCHING_ON_APPLIANCES_RANDOMLY_TEXT = "Switching on appliances randomly...";
    private Set<ElectricalAppliance> appliances = new HashSet<>();

    private House() {}

    public void addAppliances(Set<ElectricalAppliance> appliances) {
        Preconditions.checkNotNull("appliances cannot be null.", appliances);
        this.appliances.addAll(appliances);
    }

    public void switchAllAppliancesOn() {
        appliances.forEach(ElectricalAppliance::switchOn);
        LOGGER.debug(ALL_APPLIANCES_ON_TEXT);
    }

    public void switchAllAppliancesOff() {
        appliances.forEach(ElectricalAppliance::switchOff);
        LOGGER.debug(ALL_APPLIANCES_OFF_TEXT);
    }

    public void switchRandomlyAppliancesOn() {
        Random random = new Random();

        LOGGER.debug(SWITCHING_ON_APPLIANCES_RANDOMLY_TEXT);
        appliances.forEach(item -> {
            if (random.nextBoolean()) {
                item.switchOn();
            } else {
                item.switchOff();
            }
        });
    }

    public double getPowerConsumption() {
        return appliances.stream()
                .filter(ElectricalAppliance::isOn)
                .mapToDouble(ElectricalAppliance::getCurrentPower)
                .sum();
    }

    public static House newInstance() {
        return new House();
    }
}
