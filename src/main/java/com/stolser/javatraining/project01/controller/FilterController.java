package com.stolser.javatraining.project01.controller;

import com.stolser.javatraining.generalMVC.controller.InputReader;
import com.stolser.javatraining.generalMVC.view.ViewPrinter;
import com.stolser.javatraining.project01.model.appliance.ElectricalAppliance;
import com.stolser.javatraining.project01.model.appliance.EmptyElectricalAppliance;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.NavigableSet;
import java.util.Properties;
import java.util.Set;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.stolser.javatraining.project01.controller.ApplianceUtils.getSorted;
import static com.stolser.javatraining.project01.controller.SortingType.*;
import static java.lang.String.format;

public class FilterController {
    private static final String FILTER_PARAMS_FILENAME = "src\\main\\resources\\" +
            "project01\\filterParams.properties";
    private static final String FILTERPARAMS_PRICE_MIN = "filterparams.price.min";
    private static final String FILTERPARAMS_PRICE_MAX = "filterparams.price.max";
    private static final String FILTERPARAMS_POWER_MIN = "filterparams.power.min";
    private static final String FILTERPARAMS_POWER_MAX = "filterparams.power.max";
    private static final String FILTERPARAMS_WEIGHT_MIN = "filterparams.weight.min";
    private static final String FILTERPARAMS_WEIGHT_MAX = "filterparams.weight.max";
    private static final String APPLIANCES_LIMITED_BY_PRICE_TEXT =
            "----------- Appliances limited by the price = [%.2f; %.2f]";
    private static final String SEPARATOR = "-----------------------------------------------------------";
    private static final String APPLIANCES_LIMITED_BY_POWER_TEXT = "----------- " +
            "Appliances limited by the power = [%.2f; %.2f]";
    private static final String APPLIANCES_LIMITED_BY_WEIGHT_TEXT = "----------- " +
            "Appliances limited by the weight = [%.2f; %.2f]";
    private static final EmptyElectricalAppliance LOWER_SORTING_LIMITS = new EmptyElectricalAppliance();
    private static final EmptyElectricalAppliance UPPER_SORTING_LIMITS = new EmptyElectricalAppliance();

    private ViewPrinter out;
    private InputReader in;
    private Properties filteringParams;
    private NavigableSet<ElectricalAppliance> filteringResult;
    private Set<ElectricalAppliance> unsortedAppliances;

    public FilterController(ViewPrinter out, InputReader in) {
        this.out = out;
        this.in = in;
        filteringParams = new Properties();
    }

    public void filterAppliances(Set<ElectricalAppliance> appliances) throws IOException {
        checkNotNull("A set of appliances for filtering cannot be null", appliances);

        loadFilteringParamsFromFile();
        unsortedAppliances = appliances;

        filterByPrice();
        filterByPower();
        filterByWeight();
    }

    private void filterByPrice() {
        double priceMin = Double.valueOf(filteringParams.getProperty(FILTERPARAMS_PRICE_MIN));
        double priceMax = Double.valueOf(filteringParams.getProperty(FILTERPARAMS_PRICE_MAX));

        if (priceMin < priceMax) {
            LOWER_SORTING_LIMITS.setPrice(priceMin);
            UPPER_SORTING_LIMITS.setPrice(priceMax);
            filteringResult = getSorted(unsortedAppliances, BY_PRICE_ASC)
                    .subSet(LOWER_SORTING_LIMITS, true, UPPER_SORTING_LIMITS, true);

            out.printlnString(format(APPLIANCES_LIMITED_BY_PRICE_TEXT, priceMin, priceMax));
            printFilteringResults();

        } else {
            updateUnsortedAppliances();
        }
    }

    private void filterByPower() {
        double powerMin = Double.valueOf(filteringParams.getProperty(FILTERPARAMS_POWER_MIN));
        double powerMax = Double.valueOf(filteringParams.getProperty(FILTERPARAMS_POWER_MAX));

        if (powerMin < powerMax) {
            LOWER_SORTING_LIMITS.setMaxPower(powerMin);
            UPPER_SORTING_LIMITS.setMaxPower(powerMax);
            filteringResult = getSorted(filteringResult, BY_POWER_ASC)
                    .subSet(LOWER_SORTING_LIMITS, true, UPPER_SORTING_LIMITS, true);

            out.printlnString(format(APPLIANCES_LIMITED_BY_POWER_TEXT, powerMin, powerMax));
            printFilteringResults();

        } else {
            updateUnsortedAppliances();
        }
    }

    private void filterByWeight() {
        double weightMin = Double.valueOf(filteringParams.getProperty(FILTERPARAMS_WEIGHT_MIN));
        double weightMax = Double.valueOf(filteringParams.getProperty(FILTERPARAMS_WEIGHT_MAX));

        if (weightMin < weightMax) {
            LOWER_SORTING_LIMITS.setWeight(weightMin);
            UPPER_SORTING_LIMITS.setWeight(weightMax);
            filteringResult = getSorted(filteringResult, BY_WEIGHT_ASC)
                    .subSet(LOWER_SORTING_LIMITS, true, UPPER_SORTING_LIMITS, true);
            out.printlnString(format(APPLIANCES_LIMITED_BY_WEIGHT_TEXT, weightMin, weightMax));
            printFilteringResults();

        } else {
            updateUnsortedAppliances();
        }
    }

    private void printFilteringResults() {
        filteringResult.stream()
                .map(Object::toString)
                .forEach(out::printlnString);
        out.printlnString(SEPARATOR);
    }

    private void updateUnsortedAppliances() {
        unsortedAppliances = (filteringResult == null) ? unsortedAppliances : filteringResult;
    }

    private void loadFilteringParamsFromFile() throws IOException {
        filteringParams.load(new FileInputStream(FILTER_PARAMS_FILENAME));
    }
}
