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
    private static final String GETTING_A_WORD_S_FROM_CACHE = "Getting a Word(\"%s\") from cache.";
    private static final String GETTING_A_CHARACTER_S_FROM_CACHE = "Getting a Character('%s') from cache.";
    private static Map<java.lang.Character, Character> characterCache = new HashMap<>();
    private static Map<String, Word> wordCache = new WeakHashMap<>();

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
        if (!wordCache.containsKey(wordStr)) {
            wordCache.put(wordStr, new Word(wordStr));
        } else {
            LOGGER.debug(String.format(GETTING_A_WORD_S_FROM_CACHE, wordStr));
        }

        return wordCache.get(wordStr);
    }

    @Override
    public CharSequence getCharacter(char symbol) {
        if (!characterCache.containsKey(symbol)) {
            characterCache.put(symbol, new Character(symbol));
        } else {
            LOGGER.debug(String.format(GETTING_A_CHARACTER_S_FROM_CACHE, symbol));
        }

        return characterCache.get(symbol);
    }
}
