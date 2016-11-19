package com.stolser.javatraining.project01.model.appliance;

/**
 * Provides the most general API for appliances. Some fields, such as 'brand' and 'weight' cannot be changed,
 * so they must be specified during creation of an appliance. Because of that there is no setters for them.
 */
public interface Appliance {
    String getBrand();
    double getPrice();
    void setPrice(double price);
    double getWeight();
}
