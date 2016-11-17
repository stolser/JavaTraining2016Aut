package com.stolser.javatraining.block02.morelessgame.model;

import com.stolser.javatraining.generalMVC.controller.utils.NumberUtils;
import org.junit.Test;

public class UtilsTest {
    @Test(expected = IllegalArgumentException.class)
    public void randomInt_WithMinArgGraterThanMaxArg_Should_ThrowException() {
        int min = 100;
        int max = 50;

        NumberUtils.randomInt(min, max);
    }
}
