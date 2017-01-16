package com.stolser.javatraining.project02.controller.task_executor;

import com.stolser.javatraining.project02.model.CharSequence;
import com.stolser.javatraining.project02.model.CharSequenceFactory;
import com.stolser.javatraining.project02.model.flyweight_factory.CachedCharSequenceFactory;
import com.stolser.javatraining.project02.model.flyweight_factory.Character;

import java.util.List;

class SentenceGenerator {
    private CharSequenceFactory factory = new CachedCharSequenceFactory();
    private List<CharSequence> symbols;
    private CharSequence sentence;
    private CharSequence currentWord;
    private boolean wasPreviousWordChar;
    private boolean isCurrentWordChar;

    SentenceGenerator(List<CharSequence> symbols) {
        this.symbols = symbols;
    }

    CharSequence generate() {
        sentence = factory.getSentence();

        symbols.forEach(this::processSymbol);

        if (currentWord != null) {
            sentence.add(currentWord);
        }

        return sentence;
    }

    private void processSymbol(CharSequence symbol) {
        if (isSymbolWordChar((Character) symbol)) {
            addSymbolToWord(symbol);
        } else {
            addSymbolToSentence(symbol);
        }

        wasPreviousWordChar = isCurrentWordChar;
    }

    private boolean isSymbolWordChar(Character symbol) {
        return CharSequence.isWordCharacter(symbol);
    }

    private void addSymbolToWord(CharSequence symbol) {
        if (currentWord == null) {
            currentWord = factory.getWord();
        }

        isCurrentWordChar = true;
        currentWord.add(symbol);
    }

    private void addSymbolToSentence(CharSequence symbol) {
        isCurrentWordChar = false;

        if (wasPreviousWordChar) {
            sentence.add(currentWord);
            currentWord = null;
        }

        sentence.add(symbol);
    }
}
