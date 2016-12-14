package com.stolser.javatraining.project02.controller;

import com.stolser.javatraining.project02.controller.parser.Parser;
import com.stolser.javatraining.project02.controller.parser.SimpleParser;
import com.stolser.javatraining.project02.model.CharSequence;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class SimpleParserTest {
    private static final String ORIGINAL_TEXT = "   Hello   world!   This  a      \ntext.   Sym$bol\n    ssssss      ";
    private static final String EXPECTED_TEXT = " Hello world! This a \ntext. Sym$bol\n ssssss ";
    private CharSequence parsedText;
    private CharSequence parsedText2;
    @Mock(name = "reader")
    private Reader mockReader = mock(Reader.class);
    @InjectMocks
    private Parser parserWithMockReader = new SimpleParser(null);

    @Before
    public void setUp() throws Exception {
        String textToParse = "Hello world! Abcd.holeee xworld$ last";

        parsedText = new SimpleParser(new StringReader(textToParse)).parse();
        parsedText2 = new SimpleParser(new StringReader(textToParse)).parse();

        when(mockReader.read()).thenReturn((int)'A').thenReturn((int)'b')
                .thenReturn((int)'c').thenReturn(-1);

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void parse_Should_ReturnEmptyText() throws IOException {
        String textToParse = "";
        parsedText = new SimpleParser(new StringReader(textToParse)).parse();

        assertTrue(parsedText.size() == 0);
    }

    @Test
    public void parse_Should_ParseStringsCorrectly() throws IOException {
        String text = ORIGINAL_TEXT;
        String expectedStr = EXPECTED_TEXT;
        CharSequence actual = new SimpleParser(new StringReader(text)).parse();
        CharSequence expected = new SimpleParser(new StringReader(expectedStr)).parse();

        assertEquals(expected, actual);
    }

    @Test
    public void parse_Should_returnEqualCharSequencesForTheSameText() throws Exception {
        assertEquals(parsedText, parsedText2);
    }

    @Ignore
    @Test
    public void parse_Should_CallReaderFourTimes() throws IOException {
        parserWithMockReader.parse();

        verify(mockReader, times(4));
    }

}