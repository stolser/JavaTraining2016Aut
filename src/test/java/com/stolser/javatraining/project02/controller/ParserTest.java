package com.stolser.javatraining.project02.controller;

import com.stolser.javatraining.project02.model.CharSequence;
import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;

import static org.junit.Assert.assertEquals;

public class ParserTest {
    private CharSequence text1;
    private CharSequence text2;

    @Before
    public void setUp() throws Exception {
        Parser parser;
        String textToParse = "Hello world!!!";

        parser = new Parser(new StringReader(textToParse));
        text1 = parser.parse();
        parser = new Parser(new StringReader(textToParse));
        text2 = parser.parse();
    }

    @Test
    public void parse_Should_returnEqualCharSequencesForTheSameText() throws Exception {
        assertEquals(text1, text2);
    }
}