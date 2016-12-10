package com.stolser.javatraining.project02.model;

public interface CharSequenceFactory {

    CharSequence getText();

    CharSequence getSentence();

    CharSequence getWord(String wordStr);

    CharSequence getCharacter(char symbol);
}
