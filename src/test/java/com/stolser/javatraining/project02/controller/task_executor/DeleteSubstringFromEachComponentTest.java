package com.stolser.javatraining.project02.controller.task_executor;

import com.stolser.javatraining.project02.controller.parser.Parser;
import com.stolser.javatraining.project02.controller.parser.SimpleParser;
import com.stolser.javatraining.project02.model.CharSequence;
import org.junit.Before;
import org.junit.Test;

import java.io.FileReader;
import java.io.StringReader;

import static org.junit.Assert.assertEquals;

public class DeleteSubstringFromEachComponentTest {
    private static final String ORIGINAL_TEXT = "te$xt    $.   Sym$bol\n    s$s$s&kjfgnfkgkfmgss$s      ";
    private static final String EXPECTED_TEXT = "te. Sym\n ss ";
    private CharSequence expected;
    private CharSequence actual;

    @Before
    public void setUp() throws Exception {
        String textToParse = ORIGINAL_TEXT;
        String expectedStr = EXPECTED_TEXT;
        Parser parser = new SimpleParser(new StringReader(expectedStr));

        expected = parser.parse();
        parser = new SimpleParser(new StringReader(textToParse));
        actual = new DeleteSubstringFromEachComponent(
                parser.parse(), '$', '$').execute();
    }

    @Test
    public void execute_Should_ReturnFullString() throws Exception {
        String textToParse = " Hello World! abcdef^  #aaaa";

        expected = new SimpleParser(new StringReader(textToParse)).parse();
        actual = new DeleteSubstringFromEachComponent(
                new SimpleParser(new StringReader(textToParse)).parse(),
                'x', '$').execute();

        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void execute_Should_ReturnEmptyString() throws Exception {
        String textToParse = "Hello World!H abcdef^  #aaaa";

        expected = new SimpleParser(new StringReader("")).parse();
        actual = new DeleteSubstringFromEachComponent(
                new SimpleParser(new StringReader(textToParse)).parse(),
                'H', 'a').execute();

        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void execute_FromStrings_Should_ReturnCorrectText() throws Exception {
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void execute_Should_ProcessTextFromFile() throws Exception {
        CharSequence originalParsed = new SimpleParser(new FileReader("original.txt")).parse();
        String actual = new DeleteSubstringFromEachComponent(
                originalParsed, 'a', '$').execute().toString();
        String expected = new SimpleParser(new FileReader("expected.txt")).parse().toString();

        assertEquals(expected, actual);
    }


}