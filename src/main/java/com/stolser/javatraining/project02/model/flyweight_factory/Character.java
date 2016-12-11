package com.stolser.javatraining.project02.model.flyweight_factory;

import com.stolser.javatraining.project02.model.CharSequence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.List;

public class Character extends AbstractCharSequence {
    private static final Logger LOGGER = LoggerFactory.getLogger(Character.class);
    private static final String CREATING_A_NEW_CHARACTER = "Creating a new Character for '%s'.";
    private char symbol;

    Character(char symbol) {
        LOGGER.debug(String.format(CREATING_A_NEW_CHARACTER, symbol));
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
    public List<CharSequence> getComponents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean canAdd(CharSequence component) {
        return false;
    }

    @Override
    public void print() {
        System.out.print(symbol);
    }

    /**
     * @return a Null-Iterator
     */
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
