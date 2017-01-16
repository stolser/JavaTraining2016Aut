package com.stolser.javatraining.project02.model.flyweight_factory;

import com.stolser.javatraining.project02.model.CharSequence;

import java.util.*;

/**
 * Implements some common functionality for all subtypes. Some subclasses can override this
 * behavior implementing more appropriate one.
 */
abstract class AbstractCharSequence implements CharSequence, Iterable<CharSequence> {
    protected List<CharSequence> components = new ArrayList<>();
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
