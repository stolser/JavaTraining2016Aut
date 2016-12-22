package com.stolser.javatraining.project02.controller.parser;

import com.stolser.javatraining.project02.model.CharSequence;
import com.stolser.javatraining.project02.model.CharSequenceFactory;
import com.stolser.javatraining.project02.model.flyweight_factory.CachedCharSequenceFactory;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class SimpleParserTest {
    private static final String ORIGINAL_TEXT = "   Hello   world!   This  a      \ntext.   Sym$bol\n    ssssss      ";
    private static final String EXPECTED_TEXT = " Hello world! This a \ntext. Sym$bol\n ssssss ";
    private CharSequence parsedText;
    private CharSequence parsedText2;
    @Mock
    private CharSequenceFactory mockFactory;
    private CharSequenceFactory realFactory  = new CachedCharSequenceFactory();
    private BufferedReader mockReader = mock(BufferedReader.class);
    @InjectMocks
    private SimpleParser parserWithMockReader = new SimpleParser(null);

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        String textToParse = "Hello world! Abcd.holeee xworld$ last";

        parsedText = new SimpleParser(new StringReader(textToParse)).parse();
        parsedText2 = new SimpleParser(new StringReader(textToParse)).parse();

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

    @Test
    public void parse_Should_CallReaderFourTimes() throws IOException {
        when(mockReader.read()).thenReturn((int)'H', (int)'e', (int)'l', (int)'l', (int)'o',
                (int)' ', (int)'W', (int)'o', (int)'r', (int)'l', (int)'d', (int)'!', -1);

        when(mockFactory.getText()).thenReturn(realFactory.getText());
        when(mockFactory.getSentence()).thenReturn(realFactory.getSentence());
        when(mockFactory.getWord("Hello")).thenReturn(realFactory.getWord("Hello"));
        when(mockFactory.getWord("World")).thenReturn(realFactory.getWord("World"));
        when(mockFactory.getCharacter(' ')).thenReturn(realFactory.getCharacter(' '));
        when(mockFactory.getCharacter('!')).thenReturn(realFactory.getCharacter('!'));

        parserWithMockReader.readSymbolsUntilEnd(mockReader);

        verify(mockReader, times(13)).read();
        verify(mockFactory).getText();
        verify(mockFactory, times(2)).getSentence();
        verify(mockFactory, times(2)).getCharacter(anyChar());
        verify(mockFactory).getWord("Hello");
        verify(mockFactory).getWord("World");

    }

}