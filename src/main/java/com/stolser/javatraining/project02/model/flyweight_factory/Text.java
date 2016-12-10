package com.stolser.javatraining.project02.model.flyweight_factory;

import com.stolser.javatraining.project02.model.CharSequence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;

class Text extends AbstractCharSequence {
    private static final Logger LOGGER = LoggerFactory.getLogger(Text.class);
    public Text() {
        LOGGER.debug("Creating a new Text.");
    }

    @Override
    public boolean canAdd(CharSequence component) {
        return component instanceof Sentence;
    }

    @Override
    public Iterator<CharSequence> iterator() {
        return components.iterator();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("=== The beginning of the Text...\n");
        components.forEach(component -> builder.append(component.toString()));
        builder.append("=== The beginning of the Text...");

        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Text text = (Text) o;

        return components != null ? components.equals(text.components) : text.components == null;

    }

    @Override
    public int hashCode() {
        return components != null ? components.hashCode() : 0;
    }
}
