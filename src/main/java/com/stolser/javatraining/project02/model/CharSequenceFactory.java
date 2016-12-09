package com.stolser.javatraining.project02.model;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

public final class CharSequenceFactory {
    private static Map<java.lang.Character, Character> characterMap = new HashMap<>();
    private static Map<String, Word> wordMap = new WeakHashMap<>();

    public static CharSequence getText() {
        return new Text();
    }

    public static CharSequence getSentence() {
        return new Sentence();
    }

    public static CharSequence getWord(String word) {
        if (!wordMap.containsKey(word)) {
            wordMap.put(word, new Word());
        }

        return wordMap.get(word);
    }

    public static CharSequence getCharacter(char symbol) {
        if (!characterMap.containsKey(symbol)) {
            characterMap.put(symbol, new Character(symbol));
        }

        return characterMap.get(symbol);
    }
}
