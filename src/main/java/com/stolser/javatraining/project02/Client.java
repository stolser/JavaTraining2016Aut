package com.stolser.javatraining.project02;

import com.stolser.javatraining.project02.controller.Parser;
import com.stolser.javatraining.project02.model.CharSequence;

import java.io.IOException;
import java.io.StringReader;

public class Client {
    public static void main(String[] args) throws IOException {

        String text = "Hello world! This a text. Sym$bols";
//        String text = "Hello world! Abc. oooo&oooo";
        CharSequence parsed = new Parser(new StringReader(text)).parse();

        System.out.println("text's size = " + parsed.size());
        System.out.println(parsed.toString());
        parsed.print();
    }
}
