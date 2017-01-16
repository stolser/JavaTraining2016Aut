package com.stolser.javatraining.project02.controller.task_executor;

import com.stolser.javatraining.project02.model.CharSequence;
import com.stolser.javatraining.project02.model.CharSequenceFactory;
import com.stolser.javatraining.project02.model.flyweight_factory.CachedCharSequenceFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SentenceGeneratorTest {

    private CharSequenceFactory factory;
    private List<CharSequence> symbols;

    @Before
    public void setUp() throws Exception {
        factory = new CachedCharSequenceFactory();
        symbols = new ArrayList<>();
        symbols.add(factory.getCharacter('H'));
        symbols.add(factory.getCharacter('e'));
        symbols.add(factory.getCharacter('l'));
        symbols.add(factory.getCharacter('l'));
        symbols.add(factory.getCharacter('o'));
        symbols.add(factory.getCharacter(' '));
        symbols.add(factory.getCharacter('W'));
        symbols.add(factory.getCharacter('o'));
        symbols.add(factory.getCharacter('r'));
        symbols.add(factory.getCharacter('l'));
        symbols.add(factory.getCharacter('d'));
        symbols.add(factory.getCharacter('!'));

    }

    @Test
    public void generate() throws Exception {
        CharSequence hello = factory.getWord("Hello");
        CharSequence space = factory.getCharacter(' ');
        CharSequence world = factory.getWord("World");
        CharSequence exMark = factory.getCharacter('!');

        CharSequence expectedSentence = factory.getSentence();
        expectedSentence.add(hello);
        expectedSentence.add(space);
        expectedSentence.add(world);
        expectedSentence.add(exMark);

        CharSequence actualSentence = new SentenceGenerator(symbols).generate();

        assertEquals(expectedSentence, actualSentence);
    }
}