package com.stolser.javatraining.project02.model;

/**
 * A flyweight factory for Character and Word elements.
 */
public interface CharSequenceFactory {

    CharSequence getText();

    CharSequence getSentence();

    CharSequence getWord();

    CharSequence getWord(String wordStr);

    CharSequence getCharacter(char symbol);
}
