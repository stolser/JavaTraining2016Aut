package com.stolser.javatraining.project02;

import com.stolser.javatraining.project02.controller.parser.SimpleParser;
import com.stolser.javatraining.project02.controller.task_executor.DeleteSubstringFromEachComponent;
import com.stolser.javatraining.project02.model.CharSequence;

import java.io.IOException;
import java.io.StringReader;

public class Client {
    public static void main(String[] args) throws IOException {

//        String textToParse = "Hello   world!   This  a      \ntext.   Sym$bol\n    ssssss      ";
        String textToParse = "te$xt    $.   Sym$bol\n" +
                "    s$s$s&kjfgnfkgkfmgss$s      ";
//        String textToParse = "Hello   world";
        CharSequence parsed = new SimpleParser().parse(new StringReader(textToParse));

        System.out.println("text's size = " + parsed.size());
        System.out.println(parsed.toString());
        System.out.println("Printing the text using the print() method:");
        parsed.print();
        System.out.println("");

        System.out.println(new DeleteSubstringFromEachComponent(parsed, '$', '$').execute().toString());
    }
}
