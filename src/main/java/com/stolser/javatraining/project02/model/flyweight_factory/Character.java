package com.stolser.javatraining.project02.model.flyweight_factory;

import com.stolser.javatraining.project02.model.CharSequence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;

public class Character extends AbstractCharSequence {
    private static final Logger LOGGER = LoggerFactory.getLogger(Character.class);
    private char symbol;

    public Character(char symbol) {
        LOGGER.debug(String.format("Creating a new Character for '%s'.", symbol));
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    @Override
    public int size() {
        return 1;
    }

    @Override
    public boolean canAdd(CharSequence component) {
        return false;
    }

    @Override
    public void print() {
        System.out.print(symbol);
    }

    @Override
    public Iterator<CharSequence> iterator() {
        return new Iterator<CharSequence>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public CharSequence next() {
                return null;
            }
        };
    }

    @Override
    public String toString() {
        return String.valueOf(symbol);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        return symbol == ((Character) o).symbol;
    }

    @Override
    public int hashCode() {
        return (int) symbol;
    }
}
