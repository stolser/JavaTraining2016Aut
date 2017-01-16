package com.stolser.javatraining.project02.controller.task_executor;

import com.stolser.javatraining.project02.controller.parser.Parser;
import com.stolser.javatraining.project02.controller.parser.SimpleParser;
import com.stolser.javatraining.project02.model.CharSequence;
import com.stolser.javatraining.project02.model.CharSequenceFactory;
import com.stolser.javatraining.project02.model.flyweight_factory.CachedCharSequenceFactory;
import org.junit.Before;
import org.junit.Test;

import java.io.FileReader;
import java.io.StringReader;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyChar;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class DeleteSubstringFromEachComponentTest {
    private static final String ORIGINAL_TEXT = "te$xt    $.   Sym$bol\n    s$s$s&kjfgnfkgkfmgss$s      ";
    private static final String EXPECTED_TEXT = "te. Sym\n ss ";
    private CharSequence expected;
    private CharSequence actual;
    private CharSequenceFactory mockFactory;
    private CharSequence parsedText;

    @Before
    public void setUp() throws Exception {
        String textToParse = ORIGINAL_TEXT;
        String expectedStr = EXPECTED_TEXT;
        Parser parser = new SimpleParser(new StringReader(expectedStr));

        expected = parser.parse();
        parser = new SimpleParser(new StringReader(textToParse));
        actual = new DeleteSubstringFromEachComponent(
                parser.parse(), '$', '$').execute();

        String textForMock = "Hello world! Abcd.holeee xworld$ last";
        parsedText = new SimpleParser(new StringReader(textForMock)).parse();
        CharSequenceFactory realFactory = new CachedCharSequenceFactory();
        CharSequence symbolA = realFactory.getCharacter('a');
        CharSequence word = realFactory.getWord("mock");

        mockFactory = mock(CharSequenceFactory.class);
        when(mockFactory.getCharacter(anyChar())).thenReturn(symbolA);
        when(mockFactory.getWord(anyString())).thenReturn(word);
        when(mockFactory.getWord()).thenReturn(word);
        when(mockFactory.getSentence()).thenReturn(realFactory.getSentence());
        when(mockFactory.getText()).thenReturn(realFactory.getText());
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