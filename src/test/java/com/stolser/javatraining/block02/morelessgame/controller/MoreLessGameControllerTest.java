package com.stolser.javatraining.block02.morelessgame.controller;

import com.google.common.collect.Range;
import com.stolser.javatraining.block02.morelessgame.model.Environment;
import com.stolser.javatraining.block02.morelessgame.model.game.MoreLessGame;
import com.stolser.javatraining.block02.morelessgame.model.game.UserAttempt;
import com.stolser.javatraining.block02.morelessgame.view.ViewGenerator;
import com.stolser.javatraining.generalMVC.controller.InputReader;
import com.stolser.javatraining.generalMVC.view.ViewPrinter;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class MoreLessGameControllerTest {
    private InputReader input;
    private ViewPrinter output;
    private ViewGenerator viewGenerator;
    private MoreLessGame gameModel;
    private MoreLessGameController controller;
    private UserAttempt currentAttempt;


    @Before
    public void setUp() throws Exception {
        input = mock(InputReader.class);
        output = mock(ViewPrinter.class);
        viewGenerator = mock(ViewGenerator.class);
        Environment environment = Environment.newInstance(input, output, viewGenerator);

        gameModel = mock(MoreLessGame.class);
        controller = new MoreLessGameController(gameModel);
        controller.setup(environment);

        // stubbing input:
        when(input.readIntValue())
                .thenReturn(0)
                .thenReturn(6)
                .thenReturn(4);

        // stubbing gameModel:
        when(gameModel.getCurrentRange()).thenReturn(Range.closed(1, 5));
        when(gameModel.secretNumberEqualsTo(100)).thenReturn(false);
        when(gameModel.secretNumberEqualsTo(120)).thenReturn(false);
        when(gameModel.secretNumberEqualsTo(77)).thenReturn(true);  // 77 - is the secret number;
        when(gameModel.secretNumberGraterThan(100)).thenReturn(false);
        when(gameModel.secretNumberGraterThan(100)).thenReturn(false);

//
//        Range<Integer> currentRange = Range.closed(50, 150);
//        when(gameModel.getCurrentRange()).thenReturn(currentRange);


        // stubbing output:
        when(output.getMessageWithKey(anyString(), anyString())).thenReturn("");


        currentAttempt = mock(UserAttempt.class);



    }

    @Test
    public void getNewNumberFromUser_Should_WorkInLoopUntilUserEntersCorrectValue() {
        controller.getNewNumberFromUser();

        verify(output, times(3)).printString(anyString());
        verify(input, times(3)).readIntValue();
        verify(output, times(2)).printMessageWithKey(MoreLessGameController.GENERAL_MESSAGE_BUNDLE,
                MoreLessGameController.INPUT_ENTER_NEXT_NUMBER_ERROR);

    }

    @Test
    public void createNewAttempt_Should_GetCurrentRangeFromGameModel() {
        controller.createNewAttempt();

        verify(gameModel, times(1)).getCurrentRange();
    }

    @Test
    public void printStatisticsAboutGame_Should_UseViewGeneratorAndUserAttempts() {
        controller.printStatisticsAboutGame();

        verify(viewGenerator, times(1)).getGameStatisticsView(anyListOf(UserAttempt.class));
        verify(gameModel, times(1)).getUserAttempts();
    }


}