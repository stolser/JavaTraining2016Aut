package com.stolser.javatraining.project01.model;

import com.stolser.javatraining.project01.model.appliance.ElectricalAppliance;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class HouseTest {

    private House house;
    private Set<ElectricalAppliance> appliances;

    @Before
    public void setUp() throws Exception {
        house = House.newEmptyHouse();
        appliances = ApplianceInitializer.getAppliances();
        house.addAppliances(appliances);
    }

    @Test
    public void switchAllAppliancesOn_Should_MakeAppliances_StartConsumingPower() throws Exception {
        house.switchAllAppliancesOn();

        int actualNumberOfSwitchedOnAppliances = appliances.stream()
                .filter(ElectricalAppliance::isOn)
                .collect(Collectors.toList())
                .size();

        assertEquals(appliances.size(), actualNumberOfSwitchedOnAppliances);
    }

    @Test
    public void switchAllAppliancesOff_Should_MakeAppliances_StopConsumingPower() throws Exception {
        house.switchAllAppliancesOn();
        house.switchAllAppliancesOff();

        int actualNumberOfSwitchedOnAppliances = appliances.stream()
                .filter(ElectricalAppliance::isOn)
                .collect(Collectors.toList())
                .size();

        assertEquals(0, actualNumberOfSwitchedOnAppliances);
    }


}