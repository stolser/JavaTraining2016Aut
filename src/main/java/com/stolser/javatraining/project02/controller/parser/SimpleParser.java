package com.stolser.javatraining.project02.controller.parser;

import com.stolser.javatraining.project02.model.flyweight_factory.CachedCharSequenceFactory;
import com.stolser.javatraining.project02.model.CharSequence;
import com.stolser.javatraining.project02.model.CharSequenceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.regex.Pattern;

import static com.stolser.javatraining.project02.model.CharSequence.*;

public class SimpleParser implements Parser {
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleParser.class);
    private static final String SYMBOL_S = "symbol = '%s'";
    private static final String A_NEW_SENTENCE = "A new sentence: %s";
    private CharSequenceFactory factory = new CachedCharSequenceFactory();
    private CharSequence text;
    private CharSequence sentence;
    private boolean currentIsSpace;
    private boolean previousWasSpace;
    private boolean currentIsWordChar;
    private boolean previousWasWordChar;
    private StringBuilder wordBuilder;

    @Override
    public CharSequence parse(Reader reader) throws IOException {
        text = factory.getText();

        try(BufferedReader in = new BufferedReader(reader)){
            int ch;
            currentIsSpace = false;
            currentIsWordChar = false;
            sentence = factory.getSentence();

            while ((ch = in.read()) != -1) {
                LOGGER.debug(String.format(SYMBOL_S, ch));
                processSymbol((char) ch);
            }

            completeLasWordSpaceSentence();
        }

        return text;
    }

    private void processSymbol(char ch) {
        String currentSymbol = String.valueOf(ch);

        if (Pattern.matches(SPACE_REGEX, currentSymbol)) {
            currentIsSpace = true;
        } else if (previousWasSpace) {
            addOneSpace();
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
            LOGGER.debug(String.format(A_NEW_SENTENCE, sentence.toString()));

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

    private void addOneSpace() {
        addSymbolToSentence(' ');
        currentIsSpace = false;
    }

    private void addWordToSentence() {
        CharSequence word = factory.getWord(wordBuilder.toString());
        LOGGER.debug("a new Word: \"" + word.toString() + "\"");
        sentence.add(word);
        wordBuilder = null;
    }

    private void completeLasWordSpaceSentence() {
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
