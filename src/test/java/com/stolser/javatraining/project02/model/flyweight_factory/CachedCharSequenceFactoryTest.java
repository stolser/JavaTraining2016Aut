package com.stolser.javatraining.project02.model.flyweight_factory;

import com.stolser.javatraining.project02.model.CharSequence;
import com.stolser.javatraining.project02.model.CharSequenceFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class CachedCharSequenceFactoryTest {

    private CharSequenceFactory factory;

    @Before
    public void setUp() throws Exception {
        factory = new CachedCharSequenceFactory();
    }

    @Test
    public void getCharacter_Should_ReturnElementsFromCache() throws Exception {
        CharSequence charA1 = factory.getCharacter('A');
        CharSequence charA2 = factory.getCharacter('A');

        assertTrue(charA1 == charA2);
    }

    @Test
    public void getWord_Should_ReturnElementsFromCache() throws Exception {
        CharSequence wordHello1 = factory.getWord("Hello");
        CharSequence wordHello2 = factory.getWord("Hello");

        assertTrue(wordHello1 == wordHello2);
    }
}