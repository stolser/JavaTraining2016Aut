package com.stolser.javatraining.block02.morelessgame.controller;

import com.stolser.javatraining.block02.morelessgame.model.Environment;
import com.stolser.javatraining.block02.morelessgame.model.menu.MenuGenerator;
import com.stolser.javatraining.block02.morelessgame.model.menu.MenuItem;
import com.stolser.javatraining.block02.morelessgame.view.ConsoleViewFactory;
import com.stolser.javatraining.block02.morelessgame.view.ViewFactory;
import com.stolser.javatraining.block02.morelessgame.view.ViewGenerator;
import com.stolser.javatraining.generalMVC.controller.ConsoleInputReader;
import com.stolser.javatraining.generalMVC.controller.InputReader;
import com.stolser.javatraining.generalMVC.view.ViewPrinter;

/**
 * The main class of the More-Less game.
 */
public class Application {
    /**
     * Instantiate all classes necessary for the application and
     * starts a new game.
     */
    public void start() {
        ViewFactory viewFactory = ConsoleViewFactory.newInstance();
        ViewPrinter viewPrinter = viewFactory.getViewPrinter();
        ViewGenerator viewGenerator = viewFactory.getViewGenerator();
        InputReader inputReader = new ConsoleInputReader(viewPrinter);
        Environment environment = Environment.newInstance(inputReader, viewPrinter, viewGenerator);

        MenuItem mainMenu = MenuGenerator.newMainMenu(environment);
        MenuController controller = new MenuController(environment, mainMenu);

        controller.processUserInput();
    }
}
