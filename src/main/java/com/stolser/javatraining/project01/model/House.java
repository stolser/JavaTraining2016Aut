package com.stolser.javatraining.project01.model;

import com.stolser.javatraining.project01.model.appliance.ElectricalAppliance;

import java.util.*;

public class House {
    private Set<ElectricalAppliance> appliances = new HashSet<>();

    public void addAppliances(Set<ElectricalAppliance> appliances) {
        this.appliances.addAll(appliances);
    }

    public void switchAllAppliancesOn() {
        appliances.forEach(ElectricalAppliance::switchOn);
        System.out.println("All appliances have been switched on.");
    }

    public void switchAllAppliancesOff() {
        appliances.forEach(ElectricalAppliance::switchOff);
        System.out.println("All appliances have been switched off.");
    }

    public void switchRandomlyAppliancesOn() {
        Random random = new Random();

        System.out.println("Switching on appliances randomly...");
        appliances.forEach(item -> {
            if (random.nextBoolean()) {
                item.switchOn();
            } else {
                item.switchOff();
            }
        });
    }

    public double calculateTotalPowerConsumption() {
        return appliances.stream()
                .filter(ElectricalAppliance::isOn)
                .mapToDouble(ElectricalAppliance::getCurrentPower)
                .sum();
    }
}
