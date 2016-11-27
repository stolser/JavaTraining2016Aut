package com.stolser.javatraining.project01.controller;

import com.stolser.javatraining.generalMVC.view.ViewPrinter;
import com.stolser.javatraining.project01.model.appliance.ElectricalAppliance;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.stolser.javatraining.project01.controller.ApplianceUtils.*;
import static java.lang.String.format;

/**
 * Filters and prints electrical appliances.
 */
public class FilteringController {
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
     * An output stream for all messages.
     */
    private ViewPrinter out;
    /**
     * Filtering parameters.
     */
    private Properties filteringParams;

    public FilteringController(ViewPrinter out) {
        this.out = out;
        filteringParams = new Properties();
    }

    /**
     * Loads filtering params from a file, filters provide appliances according to the params and
     * prints the result after each stage of filtering.
     * @param appliances items to be filtered
     * @throws IOException if an exception errors during reading params from a file
     */
    public void filterAppliancesAndPrintResult(List<ElectricalAppliance> appliances) throws IOException {
        checkNotNull("A set of appliances for filtering cannot be null", appliances);

        loadFilteringParamsFromFile();

        filterByWeight(filterByPower(filterByPrice(appliances)));
    }

    private List<ElectricalAppliance> filterByPrice(List<ElectricalAppliance> unfiltered) {
        double priceMin = Double.valueOf(filteringParams.getProperty(FILTERPARAMS_PRICE_MIN));
        double priceMax = Double.valueOf(filteringParams.getProperty(FILTERPARAMS_PRICE_MAX));

        List<ElectricalAppliance> results = getFilteredByPrice(unfiltered, priceMin, priceMax);

        out.printlnString(format(APPLIANCES_LIMITED_BY_PRICE_TEXT, priceMin, priceMax));
        printAppliances(results);

        return results;
    }

    private List<ElectricalAppliance> filterByPower(List<ElectricalAppliance> unfiltered) {
        double powerMin = Double.valueOf(filteringParams.getProperty(FILTERPARAMS_POWER_MIN));
        double powerMax = Double.valueOf(filteringParams.getProperty(FILTERPARAMS_POWER_MAX));

        List<ElectricalAppliance> results = getFilteredByPower(unfiltered, powerMin, powerMax);

        out.printlnString(format(APPLIANCES_LIMITED_BY_POWER_TEXT, powerMin, powerMax));
        printAppliances(results);

        return results;
    }

    private List<ElectricalAppliance> filterByWeight(List<ElectricalAppliance> unfiltered) {
        double weightMin = Double.valueOf(filteringParams.getProperty(FILTERPARAMS_WEIGHT_MIN));
        double weightMax = Double.valueOf(filteringParams.getProperty(FILTERPARAMS_WEIGHT_MAX));

        List<ElectricalAppliance> results = getFilteredByWeight(unfiltered, weightMin, weightMax);

        out.printlnString(format(APPLIANCES_LIMITED_BY_WEIGHT_TEXT, weightMin, weightMax));
        printAppliances(results);

        return results;
    }

    private void printAppliances(List<ElectricalAppliance> appliances) {
        appliances.stream()
                .map(Object::toString)
                .forEach(out::printlnString);

        out.printlnString(SEPARATOR);
    }

    private void loadFilteringParamsFromFile() throws IOException {
        filteringParams.load(new FileInputStream(FILTER_PARAMS_FILENAME));
    }
}
