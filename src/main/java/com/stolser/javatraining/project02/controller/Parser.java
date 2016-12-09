package com.stolser.javatraining.project02.controller;

import com.stolser.javatraining.project02.model.CharSequence;
import com.stolser.javatraining.project02.model.CharSequenceFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.regex.Pattern;

import static com.stolser.javatraining.project02.model.CharSequence.*;
import static java.lang.String.*;

public class Parser {
    private Reader reader;

    public Parser(Reader reader) {
        this.reader = reader;
    }

    CharSequence parse() throws IOException {
        CharSequence text = CharSequenceFactory.getText();
        CharSequence sentence = CharSequenceFactory.getSentence();
        boolean isWord = false;

        try(BufferedReader in = new BufferedReader(reader)){
            int ch;
            String symbol;

            while ((ch = in.read()) != -1) {
                symbol = valueOf((char) ch);

                if (Pattern.matches(WORD_CHARACTER_REGEX, symbol)) {

                }

                if (Pattern.matches(SENTENCE_END_REGEX, symbol)) {

                }
            }

        }
    }
}
