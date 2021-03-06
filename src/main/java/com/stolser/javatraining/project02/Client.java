package com.stolser.javatraining.project02;

import com.stolser.javatraining.project02.controller.parser.SimpleParser;
import com.stolser.javatraining.project02.model.CharSequence;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

public class Client {
    public static void main(String[] args) throws IOException {

        Reader stringReader = new StringReader("Hello World!H abcdef^  #aaaa");
        Reader fileReader = new FileReader("original.txt");
        CharSequence parsed = new SimpleParser(fileReader).parse();

        System.out.println(parsed.toString());
        System.out.println("Printing the text using the print() method:");
        parsed.print();
    }
}
