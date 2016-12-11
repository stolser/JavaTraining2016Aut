package com.stolser.javatraining.project02.model.flyweight_factory;

import com.stolser.javatraining.project02.model.CharSequence;
import com.stolser.javatraining.project02.model.CharSequenceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

public final class CachedCharSequenceFactory implements CharSequenceFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(CachedCharSequenceFactory.class);
    private static Map<java.lang.Character, Character> characterMap = new HashMap<>();
    private static Map<String, Word> wordMap = new WeakHashMap<>();

    @Override
    public CharSequence getText() {
        return new Text();
    }

    @Override
    public CharSequence getSentence() {
        return new Sentence();
    }

    @Override
    public CharSequence getWord() {
        return new Word();
    }

    @Override
    public CharSequence getWord(String wordStr) {
        if (!wordMap.containsKey(wordStr)) {
            wordMap.put(wordStr, new Word(wordStr));
        } else {
            LOGGER.debug(String.format("Getting a Word(\"%s\") from cache.", wordStr));
        }

        return wordMap.get(wordStr);
    }

    @Override
    public CharSequence getCharacter(char symbol) {
        if (!characterMap.containsKey(symbol)) {
            characterMap.put(symbol, new Character(symbol));
        } else {
            LOGGER.debug("Getting a Character('" + symbol + "') from cache.");
        }

        return characterMap.get(symbol);
    }
}