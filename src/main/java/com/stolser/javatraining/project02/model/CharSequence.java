package com.stolser.javatraining.project02.model;

import java.util.Iterator;
import java.util.List;

public interface CharSequence {
    String SPACE_REGEX = "\\s";
    String SENTENCE_END_REGEX = "[.!?]";
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
}
