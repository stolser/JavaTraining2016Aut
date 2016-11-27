package com.stolser.javatraining.project01.controller;

import com.stolser.javatraining.project01.model.ApplianceInitializer;
import com.stolser.javatraining.project01.model.House;
import com.stolser.javatraining.project01.model.appliance.ElectricalAppliance;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.stolser.javatraining.project01.controller.ApplianceUtils.*;
import static com.stolser.javatraining.project01.controller.SortingOrder.*;

public class ApplianceUtilsSortingMethodsTest {
    private House house;
    private List<ElectricalAppliance> appliances;

    @Before
    public void setUp() throws Exception {
        house = House.newEmptyHouse();
        appliances = ApplianceInitializer.getAppliances();
        house.addAppliances(appliances);

    }

    @Test
    public void getSorted_Should_Sort_ByPowerDesc_Correctly() {
        sortAppliances(appliances, BY_POWER_DESC);

        printAppliances();

        for (int i = 0; i < appliances.size() - 1; i++) {
            double firstPower = appliances.get(i).getMaxPower();
            double nextPower = appliances.get(i + 1).getMaxPower();

            System.out.printf("i = %d; firstPower = %s; nextPower = %s\n", i, firstPower, nextPower);

            Assert.assertTrue(firstPower >= nextPower);
        }

    }

    @Test
    public void getSorted_Should_Sort_ByPowerAsc_Correctly() {
        sortAppliances(appliances, BY_POWER_ASC);

        printAppliances();

        for (int i = 0; i < appliances.size() - 1; i++) {
            double firstPower = appliances.get(i).getMaxPower();
            double nextPower = appliances.get(i + 1).getMaxPower();

            System.out.printf("i = %d; firstPower = %s; nextPower = %s\n", i, firstPower, nextPower);

            Assert.assertTrue(firstPower <= nextPower);
        }

    }

    @Test
    public void getSorted_Should_Sort_ByPriceDesc_Correctly() {
        sortAppliances(appliances, BY_PRICE_DESC);

        printAppliances();

        for (int i = 0; i < appliances.size() - 1; i++) {
            double firstPrice = appliances.get(i).getPrice();
            double nextPrice = appliances.get(i + 1).getPrice();

            System.out.printf("i = %d; firstPrice = %s; nextPrice = %s\n", i, firstPrice, nextPrice);

            Assert.assertTrue(firstPrice >= nextPrice);
        }

    }

    @Test
    public void getSorted_Should_Sort_ByPriceAsc_Correctly() {
        sortAppliances(appliances, BY_PRICE_ASC);

        printAppliances();

        for (int i = 0; i < appliances.size() - 1; i++) {
            double firstPrice = appliances.get(i).getPrice();
            double nextPrice = appliances.get(i + 1).getPrice();

            System.out.printf("i = %d; firstPrice = %s; nextPrice = %s\n", i, firstPrice, nextPrice);

            Assert.assertTrue(firstPrice <= nextPrice);
        }

    }

    @Test
    public void getSorted_Should_Sort_ByWeightDesc_Correctly() {
        sortAppliances(appliances, BY_WEIGHT_DESC);

        printAppliances();

        for (int i = 0; i < appliances.size() - 1; i++) {
            double firstWeight = appliances.get(i).getWeight();
            double nextWeight = appliances.get(i + 1).getWeight();

            System.out.printf("i = %d; firstWeight = %s; nextWeight = %s\n", i, firstWeight, nextWeight);

            Assert.assertTrue(firstWeight >= nextWeight);
        }

    }

    @Test
    public void getSorted_Should_Sort_ByWeightAsc_Correctly() {
        sortAppliances(appliances, BY_WEIGHT_ASC);

        printAppliances();

        for (int i = 0; i < appliances.size() - 1; i++) {
            double firstWeight = appliances.get(i).getWeight();
            double nextWeight = appliances.get(i + 1).getWeight();

            System.out.printf("i = %d; firstWeight = %s; nextWeight = %s\n", i, firstWeight, nextWeight);

            Assert.assertTrue(firstWeight <= nextWeight);
        }

    }

    private void printAppliances() {
        appliances.stream().forEach(System.out::println);
    }

}