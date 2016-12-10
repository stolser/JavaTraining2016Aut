package com.stolser.javatraining.project02.model.flyweight_factory;

import com.stolser.javatraining.project02.model.CharSequence;

import java.util.*;

abstract class AbstractCharSequence implements CharSequence, Iterable<CharSequence> {
    public List<CharSequence> components = new ArrayList<>();
    protected int modCount;

    @Override
    public List<CharSequence> getComponents() {
        return components;
    }

    @Override
    public int size() {
        return components.size();
    }

    @Override
    public boolean add(CharSequence component) {

        if (canAdd(component)) {
            components.add(component);
            modCount++;

            return true;
        }

        throw new UnsupportedOperationException();
    }
}
