package com.stolser.javatraining.project01.controller;

import com.stolser.javatraining.project01.model.ApplianceInitializer;
import com.stolser.javatraining.project01.model.House;
import com.stolser.javatraining.project01.model.appliance.ElectricalAppliance;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static com.stolser.javatraining.project01.controller.ApplianceUtils.getFilteredByPower;
import static com.stolser.javatraining.project01.controller.ApplianceUtils.getFilteredByPrice;
import static com.stolser.javatraining.project01.controller.ApplianceUtils.getFilteredByWeight;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ApplianceUtilsFilteringMethodsTest {
    private House house;
    private Set<ElectricalAppliance> appliances;

    @Before
    public void setUp() throws Exception {
        house = House.newEmptyHouse();
        appliances = ApplianceInitializer.getAppliances();
        house.addAppliances(appliances);
    }

    @Test
    public void getFilteredByPrice_Should_ReturnFilteredAppliances() throws Exception {
        double priceMin = 150;
        double priceMax = 430;

        printAll(appliances, "-----------Original unfiltered:");

        Set<ElectricalAppliance> filtered = getFilteredByPrice(appliances, priceMin, priceMax);
        printAll(filtered, "-----------Filtered results:");

        assertEquals(3, filtered.size());

        filtered.stream()
                .forEach(appliance -> {
                    double price = appliance.getPrice();
                    assertTrue((price >= priceMin) && (price <= priceMax));
                });

    }

    @Test
    public void getFilteredByPower_Should_ReturnFilteredAppliances() throws Exception {
        double powerMin = 300;
        double powerMax = 510;

        printAll(appliances, "-----------Original unfiltered:");

        Set<ElectricalAppliance> filtered = getFilteredByPower(appliances, powerMin, powerMax);
        printAll(filtered, "-----------Filtered results:");

        assertEquals(2, filtered.size());

        filtered.stream()
                .forEach(appliance -> {
                    double power = appliance.getMaxPower();
                    assertTrue((power >= powerMin) && (power <= powerMax));
                });
    }

    @Test
    public void getFilteredByWeight_Should_ReturnFilteredAppliances() throws Exception {
        double weightMin = 2;
        double weightMax = 50;

        printAll(appliances, "-----------Original unfiltered:");

        Set<ElectricalAppliance> filtered = getFilteredByWeight(appliances, weightMin, weightMax);
        printAll(filtered, "-----------Filtered results:");

        assertEquals(5, filtered.size());

        filtered.stream()
                .forEach(appliance -> {
                    double weight = appliance.getWeight();
                    assertTrue((weight >= weightMin) && (weight <= weightMax));
                });
    }

    private void printAll(Set<ElectricalAppliance> toPrint, String titleText) {
        System.out.println(titleText);
        toPrint.stream().forEach(System.out::println);
        System.out.println("-----------------------------------------------------------");
    }
}