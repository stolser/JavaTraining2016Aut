package com.stolser.javatraining.designpatterns.structural.decorator;

import com.stolser.javatraining.designpatterns.structural.decorator.component.Printer;
import com.stolser.javatraining.designpatterns.structural.decorator.component.PrinterImpl;
import com.stolser.javatraining.designpatterns.structural.decorator.decorator.LeftBracketDecorator;
import com.stolser.javatraining.designpatterns.structural.decorator.decorator.NewLineDecorator;
import com.stolser.javatraining.designpatterns.structural.decorator.decorator.QuotesDecorator;
import com.stolser.javatraining.designpatterns.structural.decorator.decorator.RightBracketDecorator;

public class Client {

    public static final String HELLO_WORLD_TEXT = "Hello World";

    public static void main(String[] args) {
        Printer printer = new NewLineDecorator(new QuotesDecorator(new PrinterImpl()));
        printer.print(HELLO_WORLD_TEXT);

        printer = new NewLineDecorator(new LeftBracketDecorator(new RightBracketDecorator(
                new QuotesDecorator(new PrinterImpl()))));
        printer.print(HELLO_WORLD_TEXT);


    }
}
