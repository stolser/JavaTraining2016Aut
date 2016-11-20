package com.stolser.javatraining.project01.controller;

import com.stolser.javatraining.project01.model.ApplianceInitializer;
import com.stolser.javatraining.project01.model.House;
import com.stolser.javatraining.project01.model.appliance.ElectricalAppliance;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.NavigableSet;
import java.util.Set;

public class ApplianceUtilsSortingMethodsTest {
    private House house;
    private Set<ElectricalAppliance> appliances;

    @Before
    public void setUp() throws Exception {
        house = House.newEmptyHouse();
        appliances = ApplianceInitializer.getAppliances();
        house.addAppliances(appliances);

    }

    @Test
    public void getSorted_Should_Sort_ByPowerDesc_Correctly() {
        NavigableSet<ElectricalAppliance> sorted =
                ApplianceUtils.getSorted(appliances, SortingOrder.BY_POWER_DESC);
        int sortedElementsNumber = sorted.size()/2;

        sorted.stream().forEach(System.out::println);

        for(int i = 1; i <= sortedElementsNumber; i++) {
            double firstPower = sorted.pollFirst().getMaxPower();
            double secondPower = sorted.pollFirst().getMaxPower();

            System.out.printf("i = %d; firstPower = %s; secondPower = %s\n", i, firstPower, secondPower);

            Assert.assertTrue(firstPower >= secondPower);
        }

    }

    @Test
    public void getSorted_Should_Sort_ByPowerAsc_Correctly() {
        NavigableSet<ElectricalAppliance> sorted =
                ApplianceUtils.getSorted(appliances, SortingOrder.BY_POWER_ASC);
        int sortedElementsNumber = sorted.size()/2;

        sorted.stream().forEach(System.out::println);

        for(int i = 1; i <= sortedElementsNumber; i++) {
            double firstPower = sorted.pollFirst().getMaxPower();
            double secondPower = sorted.pollFirst().getMaxPower();

            System.out.printf("i = %d; firstPower = %s; secondPower = %s\n", i, firstPower, secondPower);

            Assert.assertTrue(firstPower <= secondPower);
        }

    }

    @Test
    public void getSorted_Should_Sort_ByPriceDesc_Correctly() {
        NavigableSet<ElectricalAppliance> sorted =
                ApplianceUtils.getSorted(appliances, SortingOrder.BY_PRICE_DESC);
        int sortedElementsNumber = sorted.size()/2;

        sorted.stream().forEach(System.out::println);

        for(int i = 1; i <= sortedElementsNumber; i++) {
            double firstPrice = sorted.pollFirst().getPrice();
            double secondPrice = sorted.pollFirst().getPrice();

            System.out.printf("i = %d; firstPrice = %s; secondPrice = %s\n", i, firstPrice, secondPrice);

            Assert.assertTrue(firstPrice >= secondPrice);
        }

    }

    @Test
    public void getSorted_Should_Sort_ByPriceAsc_Correctly() {
        NavigableSet<ElectricalAppliance> sorted =
                ApplianceUtils.getSorted(appliances, SortingOrder.BY_PRICE_ASC);
        int sortedElementsNumber = sorted.size()/2;

        sorted.stream().forEach(System.out::println);

        for(int i = 1; i <= sortedElementsNumber; i++) {
            double firstPrice = sorted.pollFirst().getPrice();
            double secondPrice = sorted.pollFirst().getPrice();

            System.out.printf("i = %d; firstPrice = %s; secondPrice = %s\n", i, firstPrice, secondPrice);

            Assert.assertTrue(firstPrice <= secondPrice);
        }

    }

    @Test
    public void getSorted_Should_Sort_ByWeightDesc_Correctly() {
        NavigableSet<ElectricalAppliance> sorted =
                ApplianceUtils.getSorted(appliances, SortingOrder.BY_WEIGHT_DESC);
        int sortedElementsNumber = sorted.size()/2;

        sorted.stream().forEach(System.out::println);

        for(int i = 1; i <= sortedElementsNumber; i++) {
            double firstWeight = sorted.pollFirst().getWeight();
            double secondWeight = sorted.pollFirst().getWeight();

            System.out.printf("i = %d; firstWeight = %s; secondWeight = %s\n", i, firstWeight, secondWeight);

            Assert.assertTrue(firstWeight >= secondWeight);
        }

    }

    @Test
    public void getSorted_Should_Sort_ByWeightAsc_Correctly() {
        NavigableSet<ElectricalAppliance> sorted =
                ApplianceUtils.getSorted(appliances, SortingOrder.BY_WEIGHT_ASC);
        int sortedElementsNumber = sorted.size()/2;

        sorted.stream().forEach(System.out::println);

        for(int i = 1; i <= sortedElementsNumber; i++) {
            double firstWeight = sorted.pollFirst().getWeight();
            double secondWeight = sorted.pollFirst().getWeight();

            System.out.printf("i = %d; firstWeight = %s; secondWeight = %s\n", i, firstWeight, secondWeight);

            Assert.assertTrue(firstWeight <= secondWeight);
        }

    }

}