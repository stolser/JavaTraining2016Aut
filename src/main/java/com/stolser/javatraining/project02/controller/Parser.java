package com.stolser.javatraining.project02.controller;

import com.stolser.javatraining.project02.model.CachedCharSequenceFactory;
import com.stolser.javatraining.project02.model.CharSequence;
import com.stolser.javatraining.project02.model.CharSequenceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.regex.Pattern;

import static com.stolser.javatraining.project02.model.CharSequence.*;

public class Parser {
    private static final Logger LOGGER = LoggerFactory.getLogger(Parser.class);
    private CharSequenceFactory factory = new CachedCharSequenceFactory();
    private Reader reader;
    private CharSequence text;
    private CharSequence sentence;
    private boolean currentIsSpace;
    private boolean previousIsSpace;
    private boolean currentIsWordChar;
    private boolean previousIsWordChar;
    private StringBuilder wordBuilder;

    public Parser(Reader reader) {
        this.reader = reader;
    }

    public CharSequence parse() throws IOException {
        text = factory.getText();

        try(BufferedReader in = new BufferedReader(reader)){
            int ch;
            currentIsSpace = false;
            currentIsWordChar = false;
            sentence = factory.getSentence();

            while ((ch = in.read()) != -1) {
                LOGGER.debug("symbol = '" + (char) ch + "'");
                processSymbol((char) ch);

            }

            if (wordBuilder != null) {
                addWordToSentence();
            }

            if ((sentence.size() != 0)) {
                text.add(sentence);
            }
        }

        return text;
    }

    private void processSymbol(char ch) {
        String currentSymbol = String.valueOf(ch);

        if (Pattern.matches(SPACE_REGEX, currentSymbol)) {
            currentIsSpace = true;
        } else if (previousIsSpace) {
            sentence.add(factory.getCharacter(' '));
            currentIsSpace = false;
        }

        if (Pattern.matches(WORD_CHARACTER_REGEX, currentSymbol)) {
            currentIsWordChar = true;

            if (wordBuilder == null) {
                LOGGER.debug("word's beginning...");
                // a word's beginning;
                wordBuilder = new StringBuilder();
            }

            wordBuilder.append(currentSymbol);

        } else {
            currentIsWordChar = false;

            if (previousIsWordChar) {
                // a word's end - create a new Word and add to this sentence;
                addWordToSentence();
            }
        }

        if (!currentIsSpace && !currentIsWordChar) {
            sentence.add(factory.getCharacter(ch));
        }

        if (Pattern.matches(SENTENCE_END_REGEX, currentSymbol)) {
            LOGGER.debug("A new sentence: " + sentence.toString());

            text.add(sentence);
            sentence = factory.getSentence();
        }

        previousIsSpace = currentIsSpace;
        previousIsWordChar = currentIsWordChar;
    }

    private void addWordToSentence() {
        CharSequence word = factory.getWord(wordBuilder.toString());
        LOGGER.debug("a new Word: \"" + word.toString() + "\"");
        sentence.add(word);
        wordBuilder = null;
    }
}
