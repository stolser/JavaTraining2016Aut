package com.stolser.javatraining.project02.controller.parser;

import com.stolser.javatraining.project02.model.CharSequence;

import java.io.IOException;
import java.io.Reader;

/**
 * A functional interface containing a method for parsing character stream into a
 * composite structure that consists of the following elements: Text, Sentences, Words, and
 * Character. During parsing several space symbols going in a row are replaced by 1 space Character.
 */
public interface Parser {
    /**
     * @param reader a character stream to be used as a source for characters
     * @return a Text root element
     * @throws IOException
     */
    CharSequence parse(Reader reader) throws IOException;
}
