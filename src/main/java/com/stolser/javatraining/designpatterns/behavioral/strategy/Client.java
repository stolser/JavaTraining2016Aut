package com.stolser.javatraining.designpatterns.behavioral.strategy;

import com.stolser.javatraining.designpatterns.behavioral.strategy.context.TextEditor;
import com.stolser.javatraining.designpatterns.behavioral.strategy.strategy.CapTextFormatter;
import com.stolser.javatraining.designpatterns.behavioral.strategy.strategy.LowerTextFormatter;
import com.stolser.javatraining.designpatterns.behavioral.strategy.strategy.TextFormatter;

public class Client {

    public static void main(String[] args) {
        TextFormatter formatter = new CapTextFormatter();

        TextEditor editor = new TextEditor(formatter);
        editor.publishText("Testing text in caps formatter");

        formatter = new LowerTextFormatter();

        editor = new TextEditor(formatter);
        editor.publishText("Testing text in lower formatter");
    }
}
