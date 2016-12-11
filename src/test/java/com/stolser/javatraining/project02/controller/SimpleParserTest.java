package com.stolser.javatraining.project02.controller;

import com.stolser.javatraining.project02.controller.parser.SimpleParser;
import com.stolser.javatraining.project02.controller.task_executor.DeleteSubstringFromEachComponent;
import com.stolser.javatraining.project02.controller.task_executor.TaskExecutor;
import com.stolser.javatraining.project02.model.CharSequence;
import com.stolser.javatraining.project02.model.CharSequenceFactory;
import com.stolser.javatraining.project02.model.flyweight_factory.CachedCharSequenceFactory;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
    @Mock (name = "factory")
    CharSequenceFactory mockFactory;
    @InjectMocks
    TaskExecutor executor;

    @Before
    public void setUp() throws Exception {
        String textToParse = "Hello world! Abcd.heleee aw $";

        parsedText = new SimpleParser(new StringReader(textToParse)).parse();
        parsedText2 = new SimpleParser(new StringReader(textToParse)).parse();

        CharSequenceFactory realFactory = new CachedCharSequenceFactory();
        mockFactory = mock(CharSequenceFactory.class);
        CharSequence word = realFactory.getWord("word");
        when(mockFactory.getCharacter(anyChar())).thenReturn(realFactory.getCharacter('a'));
        when(mockFactory.getWord(anyString())).thenReturn(word, word, word, word);
        when(mockFactory.getWord()).thenReturn(word, word, word, word);
        when(mockFactory.getSentence()).thenReturn(realFactory.getSentence());
        when(mockFactory.getText()).thenReturn(realFactory.getText());

        executor = new DeleteSubstringFromEachComponent(parsedText, 'e', 'x');
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

    @Test
    public void parse_Should_callSentenceFactoryTreeTimes() {
        executor.execute();

        verify(mockFactory, times(1)).getText();
        verify(mockFactory, times(1)).getText();
        verify(mockFactory, times(6)).getSentence();

    }
}