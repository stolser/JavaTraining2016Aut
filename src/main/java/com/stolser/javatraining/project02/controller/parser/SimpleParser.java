package com.stolser.javatraining.project02.controller.parser;

import com.stolser.javatraining.project02.model.CharSequence;
import com.stolser.javatraining.project02.model.CharSequenceFactory;
import com.stolser.javatraining.project02.model.flyweight_factory.CachedCharSequenceFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.regex.Pattern;

import static com.stolser.javatraining.project02.model.CharSequence.*;

public class SimpleParser implements Parser {
    private CharSequenceFactory factory = new CachedCharSequenceFactory();
    private CharSequence text;
    private CharSequence sentence;
    private boolean currentIsSpace;
    private boolean previousWasSpace;
    private boolean currentIsWordChar;
    private boolean previousWasWordChar;
    private StringBuilder wordBuilder;
    private Reader reader;

    /**
     * @param reader a character stream to be used as a source for characters
     */
    public SimpleParser(Reader reader) {
        this.reader = reader;
    }

    @Override
    public CharSequence parse() throws IOException {
        try(BufferedReader in = new BufferedReader(reader)){
            readSymbolsUntilEnd(in);
            completeLastWordSpaceSentence();
        }

        return text;
    }

    void readSymbolsUntilEnd(BufferedReader in) throws IOException {
        text = factory.getText();
        sentence = factory.getSentence();
        wordBuilder = null;
        currentIsSpace = false;
        currentIsWordChar = false;
        int ch;
        while ((ch = in.read()) != -1) {
//            System.out.println(String.format(SYMBOL_S, ch));
            processSymbol((char) ch);
        }
    }

    private void processSymbol(char ch) {
        String currentSymbol = String.valueOf(ch);

        if (Pattern.matches(SPACE_REGEX, currentSymbol)) {
            currentIsSpace = true;
        } else {
            currentIsSpace = false;

            if (previousWasSpace) {
                addSymbolToSentence(' ');
            }
        }

        if (Pattern.matches(WORD_CHARACTER_REGEX, currentSymbol)) {
            appendSymbolToWord(currentSymbol);

        } else {
            currentIsWordChar = false;

            if (previousWasWordChar) {
                addWordToSentence();
            }
        }

        if (!currentIsSpace && !currentIsWordChar) {
            addSymbolToSentence(ch);
        }

        if (Pattern.matches(SENTENCE_END_REGEX, currentSymbol)) {
            text.add(sentence);
            sentence = factory.getSentence();
        }

        previousWasSpace = currentIsSpace;
        previousWasWordChar = currentIsWordChar;
    }

    private void appendSymbolToWord(String currentSymbol) {
        currentIsWordChar = true;

        if (wordBuilder == null) {
            wordBuilder = new StringBuilder();
        }

        wordBuilder.append(currentSymbol);
    }

    private void addWordToSentence() {
        CharSequence word = factory.getWord(wordBuilder.toString());
        sentence.add(word);
        wordBuilder = null;
    }

    private void completeLastWordSpaceSentence() {
        if (wordBuilder != null) {
            addWordToSentence();
        }

        if (currentIsSpace) {
            addSymbolToSentence(' ');
        }

        if ((sentence.size() != 0)) {
            text.add(sentence);
        }
    }

    private void addSymbolToSentence(char ch) {
        sentence.add(factory.getCharacter(ch));
    }
}
