package com.stolser.javatraining.project02.model;

import com.stolser.javatraining.project02.model.flyweight_factory.Character;

import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Represents a Component for the Composite pattern.
 */
public interface CharSequence {
    String SPACE_REGEX = "\\s";
    String SENTENCE_END_REGEX = "[.!?\n]";
    String WORD_CHARACTER_REGEX = "\\w";

    /**
     * @return the number of child-elements on the next level of the structure
     */
    int size();

    /**
     * @return all children elements from the next sub-level of the structure
     */
    List<CharSequence> getComponents();

    /**
     * Checks whether this subtype of components can be added as direct child
     */
    boolean canAdd(CharSequence component);

    /**
     * Adds this component to the children of this component. Before this operation it is
     * recommended to check whether this particular object be added to this component via
     * the {@code canAdd()} method.
     */
    boolean add(CharSequence component);

    Iterator<CharSequence> iterator();

    /**
     * Prints the whole structure.
     */
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
