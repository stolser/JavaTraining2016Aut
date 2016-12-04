package com.stolser.javatraining.block02.morelessgame.controller;

import com.stolser.javatraining.block02.morelessgame.model.Environment;
import com.stolser.javatraining.block02.morelessgame.model.menu.MenuItem;
import com.stolser.javatraining.block02.morelessgame.view.ViewGenerator;
import com.stolser.javatraining.generalMVC.controller.InputReader;
import com.stolser.javatraining.generalMVC.view.ViewPrinter;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class MenuControllerTest {

    public static final String MAIN_MENU_VIEW = "Main Menu";
    private MenuItem mainMenu;
    private MenuItem menuItem2;
    private MenuItem menuItem3;
    private MenuItem menuItemExit;
    private InputReader input;
    private ViewPrinter output;
    private ViewGenerator viewGenerator;
    private MenuController menuController;

    @Before
    public void setup() {
        mainMenu = mock(MenuItem.class);
        menuItem2 = mock(MenuItem.class);
        menuItem3 = mock(MenuItem.class);
        menuItemExit = mock(MenuItem.class);
        input = mock(InputReader.class);
        output = mock(ViewPrinter.class);
        viewGenerator = mock(ViewGenerator.class);

        Environment environment = Environment.newInstance(input, output, viewGenerator);

        menuController = new MenuController(environment, mainMenu);

        // stubbing input:
        when(input.readIntValue())
                .thenReturn(2)
                .thenReturn(10)
                .thenReturn(11)
                .thenReturn(3)
                .thenReturn(4);

        // stubbing mainMenu and selected menu items:
        when(mainMenu.getItemByOptionId(2)).thenReturn(menuItem2);
        when(mainMenu.getItemByOptionId(3)).thenReturn(menuItem3);
        when(mainMenu.getItemByOptionId(4)).thenReturn(menuItemExit);
        when(mainMenu.getItemByOptionId(10)).thenReturn(null);
        when(mainMenu.getItemByOptionId(11)).thenReturn(null);

        when(menuItem2.getSystemName()).thenReturn("Play");
        when(menuItem3.getSystemName()).thenReturn("Change the language");
        when(menuItemExit.getSystemName()).thenReturn(MenuController.EXIT_SYSTEM_NAME);

        // stubbing viewGenerator:
        when(viewGenerator.getMainMenuView(mainMenu)).thenReturn(MAIN_MENU_VIEW);

    }

    @Test
    public void showMenu_Should_UseOutputAndViewGenerator() {
        menuController.showMenu();

        verify(viewGenerator).getMainMenuView(mainMenu);
        verify(output).printString(MAIN_MENU_VIEW);
    }

    @Test
    public void readUserChoice_Should_ReturnInteger() {
        assertEquals(2, menuController.readUserChoice());
    }

    @Test
    public void askUserAndGetChosenMenuItem_Should_ReturnMenuItem() {
        assertEquals(menuItem2, menuController.askUserAndGetChosenMenuItem());
    }

    @Test
    public void askUserAndGetChosenMenuItem_Should_AskAgainIfUserEnteredIncorrectValue() {
        menuController.askUserAndGetChosenMenuItem();
        assertEquals(menuItem3, menuController.askUserAndGetChosenMenuItem());

        verify(input, times(4)).readIntValue();
        verify(output, times(4)).printMessageWithKey(MenuController.GENERAL_MESSAGE_BUNDLE,
                MenuController.CHOOSE_MENU_ITEM_TEXT);
        verify(output, times(2)).printMessageWithKey(MenuController.GENERAL_MESSAGE_BUNDLE,
                MenuController.INPUT_MENU_OPTION_ERROR);
        verify(mainMenu, times(4)).getItemByOptionId(anyInt());
    }

    @Test
    public void processUserInput_Should_WorkInLoop_UntilUserChoosesExit() {
        menuController.processUserInput();

        verify(output, times(3)).printString(MAIN_MENU_VIEW);
        verify(menuItem2, times(1)).chooseMenu();
        verify(menuItem3, times(1)).chooseMenu();
        verify(menuItemExit, never()).chooseMenu();

    }
}