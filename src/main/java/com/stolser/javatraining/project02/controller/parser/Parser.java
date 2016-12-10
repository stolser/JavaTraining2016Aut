package com.stolser.javatraining.project02.controller.parser;

import com.stolser.javatraining.project02.model.CharSequence;

import java.io.IOException;
import java.io.Reader;

public interface Parser {
    CharSequence parse(Reader reader) throws IOException;
}
