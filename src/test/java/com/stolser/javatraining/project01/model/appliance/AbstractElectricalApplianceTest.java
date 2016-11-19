package com.stolser.javatraining.project01.model.appliance;

import com.stolser.javatraining.project01.model.appliance.household.Hoover;
import org.junit.Test;

public class AbstractElectricalApplianceTest {

    @Test(expected = IllegalArgumentException.class)
    public void setPrice_Should_ThrowExceptionIfPriceIsNegative() throws Exception {
        Hoover hoover = new Hoover(ApplianceType.CLEANING, "Hoover", 6.3);
        hoover.setPrice(-250);
    }

    @Test(expected = NullPointerException.class)
    public void constructor_Should_ThrowExceptionIfBrandIsNull() throws Exception {
        Hoover hoover = new Hoover(ApplianceType.CLEANING, null, 6.3);
    }
}