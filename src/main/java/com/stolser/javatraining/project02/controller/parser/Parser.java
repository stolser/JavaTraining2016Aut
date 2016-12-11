package com.stolser.javatraining.project02.controller.parser;

import com.stolser.javatraining.project02.model.CharSequence;

import java.io.IOException;

/**
 * A functional interface containing a method for parsing character stream into a
 * composite structure that consists of the following elements: Text, Sentences, Words, and
 * Character. During parsing several space symbols going in a row are replaced by 1 space Character.
 */
public interface Parser {
    /**
     * @return a Text root element
     * @throws IOException
     */
    CharSequence parse() throws IOException;
}
