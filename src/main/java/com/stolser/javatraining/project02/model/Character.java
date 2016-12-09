package com.stolser.javatraining.project02.model;

import java.util.Iterator;

class Character extends AbstractCharSequence {
    private char symbol;

    public Character(char symbol) {
        this.symbol = symbol;
    }

    @Override
    public boolean canAdd(CharSequence component) {
        return false;
    }

    @Override
    public void print() {
        System.out.println(symbol);
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
}
