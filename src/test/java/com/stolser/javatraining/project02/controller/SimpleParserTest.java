package com.stolser.javatraining.project02.controller;

import com.stolser.javatraining.project02.controller.parser.Parser;
import com.stolser.javatraining.project02.controller.parser.SimpleParser;
import com.stolser.javatraining.project02.model.CharSequence;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;

import static org.junit.Assert.assertEquals;

public class SimpleParserTest {
    private static final String ORIGINAL_TEXT = " Hello   world!   This  a      \ntext.   Sym$bol\n    ssssss      ";
    private static final String EXPECTED_TEXT = "Hello world! This a \ntext. Sym$bol\n ssssss ";
    private CharSequence text1;
    private CharSequence text2;

    @Before
    public void setUp() throws Exception {
        Parser parser = new SimpleParser();
        String textToParse = "Hello world!!!";

        text1 = parser.parse(new StringReader(textToParse));
        text2 = parser.parse(new StringReader(textToParse));
    }

    @Test
    public void parse_Should_ParseStringsCorrectly() throws IOException {
        Parser parser = new SimpleParser();
        String text = ORIGINAL_TEXT;
        String expectedStr = EXPECTED_TEXT;
        CharSequence actual = parser.parse(new StringReader(text));
        CharSequence expected = parser.parse(new StringReader(expectedStr));

        assertEquals(expected, actual);
    }

    @Test
    public void parse_Should_returnEqualCharSequencesForTheSameText() throws Exception {
        assertEquals(text1, text2);
    }
}