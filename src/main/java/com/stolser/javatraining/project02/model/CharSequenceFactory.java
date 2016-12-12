package com.stolser.javatraining.project02.model;

/**
 * A flyweight factory for Character and Word elements.
 */
public interface CharSequenceFactory {

    /**
     * @return a new instance of Text;
     */
    CharSequence getText();

    /**
     * @return a new instance of Sentence;
     */
    CharSequence getSentence();

    /**
     * @return a new empty instance of Word;
     */
    CharSequence getWord();

    /**
     * @return a new instance of Word pre-populated with Character objects
     * that can be taken from cache or created a new one. The behavior is undetermined.
     */
    CharSequence getWord(String wordStr);

    /**
     * @return a cached Character object representing a passed symbol;
     */
    CharSequence getCharacter(char symbol);
}
