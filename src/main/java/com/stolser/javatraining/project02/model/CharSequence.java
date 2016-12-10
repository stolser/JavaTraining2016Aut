package com.stolser.javatraining.project02.model;

import com.stolser.javatraining.project02.model.flyweight_factory.Character;

import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

public interface CharSequence {
    String SPACE_REGEX = "\\s";
    String SENTENCE_END_REGEX = "[.!?\n]";
    String WORD_CHARACTER_REGEX = "\\w";

    int size();

    List<CharSequence> getComponents();

    boolean canAdd(CharSequence component);

    boolean add(CharSequence component);

    Iterator<CharSequence> iterator();

    default void print() {
        Iterator<CharSequence> it = iterator();

        while (it.hasNext()) {
            it.next().print();
        }
    }

    static boolean isWordCharacter(Character symbol) {
        return Pattern.matches(WORD_CHARACTER_REGEX, String.valueOf(symbol.getSymbol()));
    }
}
