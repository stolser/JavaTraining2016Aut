package com.stolser.javatraining.project01.controller;

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
import static com.stolser.javatraining.project01.controller.SortingOrder.*;
import static java.lang.String.format;

/**
 * Provides functionality for filtering electrical appliances.
 * Exploits {@link java.util.TreeSet#subSet(Object, Object)} to get appliances in the specified range.
 */
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
    /**
     * Represents the lower limits for specified filtering parameters. <br />
     * Used as an argument for {@link java.util.TreeSet#subSet(Object, boolean, Object, boolean)}.
     */
    private static final EmptyElectricalAppliance LOWER_FILTERING_LIMITS = new EmptyElectricalAppliance();
    /**
     * Represents the upper limits for specified filtering parameters. <br />
     * Used as an argument for {@link java.util.TreeSet#subSet(Object, boolean, Object, boolean)}.
     */
    private static final EmptyElectricalAppliance UPPER_FILTERING_LIMITS = new EmptyElectricalAppliance();

    /**
     * An output stream for all messages.
     */
    private ViewPrinter out;
    /**
     * Filtering parameters.
     */
    private Properties filteringParams;
    /**
     * The current results of filtering. Updated after each stage of filtering (by price/by power/by weight).
     */
    private NavigableSet<ElectricalAppliance> filteringResult;
    /**
     * Appliances to be filtered. They are passed into {@link ApplianceUtils#getSorted(Set, SortingOrder)}.
     */
    private Set<ElectricalAppliance> unfilteredAppliances;

    public FilterController(ViewPrinter out) {
        this.out = out;
        filteringParams = new Properties();
    }

    /**
     * Loads filtering params from a file, filters provide appliances according to the params and
     * prints the result after each stage of filtering.
     * @param appliances items to be filtered
     * @throws IOException if an exception errors during reading params from a file
     */
    public void filterAppliances(Set<ElectricalAppliance> appliances) throws IOException {
        checkNotNull("A set of appliances for filtering cannot be null", appliances);

        loadFilteringParamsFromFile();
        unfilteredAppliances = appliances;

        filterByPrice();
        filterByPower();
        filterByWeight();
    }

    private void filterByPrice() {
        double priceMin = Double.valueOf(filteringParams.getProperty(FILTERPARAMS_PRICE_MIN));
        double priceMax = Double.valueOf(filteringParams.getProperty(FILTERPARAMS_PRICE_MAX));

        if (priceMin < priceMax) {
            LOWER_FILTERING_LIMITS.setPrice(priceMin);
            UPPER_FILTERING_LIMITS.setPrice(priceMax);
            filteringResult = getSorted(unfilteredAppliances, BY_PRICE_ASC)
                    .subSet(LOWER_FILTERING_LIMITS, true, UPPER_FILTERING_LIMITS, true);

            out.printlnString(format(APPLIANCES_LIMITED_BY_PRICE_TEXT, priceMin, priceMax));
            printFilteringResults();

        } else {
            updateUnfilteredAppliances();
        }
    }

    private void filterByPower() {
        double powerMin = Double.valueOf(filteringParams.getProperty(FILTERPARAMS_POWER_MIN));
        double powerMax = Double.valueOf(filteringParams.getProperty(FILTERPARAMS_POWER_MAX));

        if (powerMin < powerMax) {
            LOWER_FILTERING_LIMITS.setMaxPower(powerMin);
            UPPER_FILTERING_LIMITS.setMaxPower(powerMax);
            filteringResult = getSorted(unfilteredAppliances, BY_POWER_ASC)
                    .subSet(LOWER_FILTERING_LIMITS, true, UPPER_FILTERING_LIMITS, true);

            out.printlnString(format(APPLIANCES_LIMITED_BY_POWER_TEXT, powerMin, powerMax));
            printFilteringResults();

        } else {
            updateUnfilteredAppliances();
        }
    }

    private void filterByWeight() {
        double weightMin = Double.valueOf(filteringParams.getProperty(FILTERPARAMS_WEIGHT_MIN));
        double weightMax = Double.valueOf(filteringParams.getProperty(FILTERPARAMS_WEIGHT_MAX));

        if (weightMin < weightMax) {
            LOWER_FILTERING_LIMITS.setWeight(weightMin);
            UPPER_FILTERING_LIMITS.setWeight(weightMax);
            filteringResult = getSorted(unfilteredAppliances, BY_WEIGHT_ASC)
                    .subSet(LOWER_FILTERING_LIMITS, true, UPPER_FILTERING_LIMITS, true);
            out.printlnString(format(APPLIANCES_LIMITED_BY_WEIGHT_TEXT, weightMin, weightMax));
            printFilteringResults();

        } else {
            updateUnfilteredAppliances();
        }
    }

    private void printFilteringResults() {
        filteringResult.stream()
                .map(Object::toString)
                .forEach(out::printlnString);
        out.printlnString(SEPARATOR);
    }

    private void updateUnfilteredAppliances() {
        unfilteredAppliances = (filteringResult == null) ? unfilteredAppliances : filteringResult;
    }

    private void loadFilteringParamsFromFile() throws IOException {
        filteringParams.load(new FileInputStream(FILTER_PARAMS_FILENAME));
    }
}
