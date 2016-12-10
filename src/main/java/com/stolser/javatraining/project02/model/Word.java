package com.stolser.javatraining.project02.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;

class Word extends AbstractCharSequence {
    private static final Logger LOGGER = LoggerFactory.getLogger(Word.class);
    private CharSequenceFactory factory = new CachedCharSequenceFactory();

    public Word() {}

    public Word(String wordStr) {
        for (char symbol: wordStr.toCharArray()) {
            components.add(factory.getCharacter(symbol));
        }

        LOGGER.debug("Creating a new Word for \"" + wordStr + "\".");
    }

    @Override
    public boolean canAdd(CharSequence component) {
        return component instanceof Character;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("'");
        components.forEach(component -> builder.append(component.toString()));
        builder.append("'");

        return builder.toString();
    }

    @Override
    public Iterator<CharSequence> iterator() {
        return components.iterator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Word word = (Word) o;

        return components != null ? components.equals(word.components) : word.components == null;

    }

    @Override
    public int hashCode() {
        return components != null ? components.hashCode() : 0;
    }
}
