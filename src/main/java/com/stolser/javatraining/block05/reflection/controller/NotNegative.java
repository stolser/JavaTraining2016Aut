package com.stolser.javatraining.block05.reflection.controller;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * Indicates that annotated variables cannot be negative. The variable should have one of the following
 * primitive types: {@code byte, short, int, long, float, double} otherwise this annotation
 * will have no affect.<br />
 * This invariant is verified after each method invocation of a proxied object,
 * so a class using this annotation should make all variables private and provide setters,
 * otherwise the annotation will not achieve its purpose.
 */
@Target(value = {FIELD, PARAMETER, LOCAL_VARIABLE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NotNegative {}
