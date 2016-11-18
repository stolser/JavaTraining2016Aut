package com.stolser.javatraining.project01;

import com.stolser.javatraining.generalMVC.controller.ConsoleInputReader;
import com.stolser.javatraining.generalMVC.controller.InputReader;
import com.stolser.javatraining.generalMVC.view.ViewPrinter;
import com.stolser.javatraining.generalMVC.view.ViewPrinterImpl;
import com.stolser.javatraining.project01.controller.MainController;

public class AppRunner {
    public static void main(String[] args) throws Exception {
        ViewPrinter output = new ViewPrinterImpl(System.out);
        InputReader input = new ConsoleInputReader(output);

        new MainController(output, input).start();
    }
}
