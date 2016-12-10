package com.stolser.javatraining.project02.controller.task_executor;

import com.stolser.javatraining.project02.controller.parser.Parser;
import com.stolser.javatraining.project02.controller.parser.SimpleParser;
import com.stolser.javatraining.project02.model.CharSequence;
import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;

import static org.junit.Assert.assertEquals;

public class DeleteSubstringFromEachComponentTest {
    private static final String ORIGINAL_TEXT = "te$xt    $.   Sym$bol\n    s$s$s&kjfgnfkgkfmgss$s      ";
    private static final String EXPECTED_TEXT = " te. Sym\n ss ";
    private CharSequence expected;
    private CharSequence actual;

    @Before
    public void setUp() throws Exception {
        Parser parser = new SimpleParser();
        String textToParse = ORIGINAL_TEXT;
        String expectedStr = EXPECTED_TEXT;

        expected = parser.parse(new StringReader(expectedStr));
        actual = new DeleteSubstringFromEachComponent(
                parser.parse(new StringReader(textToParse)), '$', '$').execute();
    }

    @Test
    public void execute_Should_ReturnCorrectText() throws Exception {
        assertEquals(expected.toString(), actual.toString());
    }
}