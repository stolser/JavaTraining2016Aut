package com.stolser.javatraining.project02.model;

import java.util.Iterator;

public interface CharSequence {
    String SPACE_REGEX = "\\s";
    String SENTENCE_END_REGEX = "[.!?]";
    String WORD_CHARACTER_REGEX = "\\w";

    boolean canAdd(CharSequence component);
    boolean add(CharSequence component);
    Iterator<CharSequence> iterator();

    default void print() {
        Iterator<CharSequence> it = iterator();

        while (it.hasNext()) {
            it.next().print();
        }
    }
}
