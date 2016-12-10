package com.stolser.javatraining.project02.model;

import java.util.*;

abstract class AbstractCharSequence implements CharSequence, Iterable<CharSequence> {
    public List<CharSequence> components = new ArrayList<>();
    protected int modCount;
    protected int size;

    @Override
    public List<CharSequence> getComponents() {
        return components;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean add(CharSequence component) {

        if (canAdd(component)) {
            components.add(component);
            modCount++;
            size++;

            return true;
        }

        throw new UnsupportedOperationException();
    }
}
