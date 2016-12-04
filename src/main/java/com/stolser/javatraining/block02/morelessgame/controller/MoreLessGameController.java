package com.stolser.javatraining.block02.morelessgame.controller;

import com.google.common.collect.Range;
import com.stolser.javatraining.block02.morelessgame.model.Environment;
import com.stolser.javatraining.block02.morelessgame.model.game.Game;
import com.stolser.javatraining.block02.morelessgame.model.game.MoreLessGame;
import com.stolser.javatraining.block02.morelessgame.model.game.UserAttempt;
import com.stolser.javatraining.block02.morelessgame.model.game.UserAttemptImpl;
import com.stolser.javatraining.block02.morelessgame.view.ViewGenerator;
import com.stolser.javatraining.generalMVC.controller.InputReader;
import com.stolser.javatraining.generalMVC.view.ViewPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;

/**
 * Represents an abstraction of a game process. It contains methods for
 * <ul><li>checking and changing lower and upper bounds of the range
 * from which computer pick a secret number;</li>
 * <li>starting a new game;</li>
 * </ul>
 */
public class MoreLessGameController implements Game {
    private static final Logger LOGGER = LoggerFactory.getLogger(MoreLessGameController.class);
    static final String GENERAL_MESSAGE_BUNDLE = "generalMessages";
    static final String GAME_START_MESSAGE = "game.startMessage";
    static final String MENU_ENTER_NEXT_NUMBER = "menu.enterNextNumber";
    static final String INPUT_ENTER_NEXT_NUMBER_ERROR = "input.enterNextNumber.error";
    static final String GAME_TARGET_IS_HIT = "game.targetIsHit";

    /**
     * Used to get and print i18ned messages.
     */
    private ViewPrinter output;

    /**
     * User to get view representations for some blocks.
     */
    private ViewGenerator viewGenerator;

    /**
     * Used to display messages to a user.
     */
    private InputReader input;

    /**
     * A flag showing whether a user has guessed the secret number or not.
     */
    private boolean targetIsNotHit;

    /**
     * The number picked by a user during the current attempt.
     */
    private int userInput;

    /**
     * Indicates that this game has been already set up and it is ready to start.
     */
    private boolean isGameReady;

    private MoreLessGame gameModel;

    public MoreLessGameController(MoreLessGame gameModel) {
        this.gameModel = gameModel;
    }

    @Override
    public void setup(Environment environment) {
        if (isGameReady) {
            throw new IllegalStateException("The game has been already set up.");
        }

        output = environment.getViewPrinter();
        viewGenerator = environment.getViewGenerator();
        input = environment.getInputReader();
        targetIsNotHit = true;
        isGameReady = true;
    }

    @Override
    public void start() {
        if (! isGameReady) {
            throw new IllegalStateException("The game has NOT been set up yet. " +
                    "setup() must be called before start().");
        }

        LOGGER.debug("Starting a new MoreLessGameController. Default range: {}...", gameModel.getCurrentRange());

        displayGameStartMessage();

        do {
            getNewNumberFromUser();
            UserAttempt currentAttempt = createNewAttempt();

            checkInputNumberAndUpdateCurrentAttempt(currentAttempt);

            LOGGER.debug("newAttempt: {}", currentAttempt);
            gameModel.addNewUserAttempt(currentAttempt);

        } while (targetIsNotHit);

        printStatisticsAboutGame();

    }

    private void displayGameStartMessage() {
        output.printlnMessageWithKey(GENERAL_MESSAGE_BUNDLE, GAME_START_MESSAGE);
    }

    void getNewNumberFromUser() {
        int userNumber;
        String enterNextNumberMessage = MessageFormat.format(
                output.getMessageWithKey(GENERAL_MESSAGE_BUNDLE, MENU_ENTER_NEXT_NUMBER),
                gameModel.getCurrentRange());

        do {
            output.printString(enterNextNumberMessage);
            userNumber = input.readIntValue();
            LOGGER.debug("userNumber = {}", userNumber);
            if (userEnteredIncorrectValue(userNumber)) {
                output.printMessageWithKey(GENERAL_MESSAGE_BUNDLE, INPUT_ENTER_NEXT_NUMBER_ERROR);
            }

        } while (userEnteredIncorrectValue(userNumber));

        userInput = userNumber;
    }

    UserAttempt createNewAttempt() {
        UserAttempt newAttempt = new UserAttemptImpl(gameModel.generateAndGetAttemptSerialNo(),
                gameModel.getCurrentRange());
        newAttempt.setNumber(userInput);

        return newAttempt;
    }

    void checkInputNumberAndUpdateCurrentAttempt(UserAttempt currentAttempt) {
        if (userGuessedTheNumber()) {
            currentAttempt.setResult(UserAttemptImpl.AttemptResult.ATTEMPT_RESULT_SCORE);
            targetIsNotHit = false;
        } else {
            if ( gameModel.secretNumberGraterThan(userInput)) {
                currentAttempt.setResult(UserAttemptImpl.AttemptResult.ATTEMPT_RESULT_TOO_SMALL);
                gameModel.setCurrentRange(getUpperSubRange());
            } else {
                currentAttempt.setResult(UserAttemptImpl.AttemptResult.ATTEMPT_RESULT_TOO_LARGE);
                gameModel.setCurrentRange(getLowerSubRange());
            }

            currentAttempt.setNewRange(gameModel.getCurrentRange());
        }
    }

    private boolean userGuessedTheNumber() {
        return gameModel.secretNumberEqualsTo(userInput);
    }

    private Range<Integer> getLowerSubRange() {
        return Range.closed(gameModel.getCurrentRange().lowerEndpoint(), userInput - 1);
    }

    private Range<Integer> getUpperSubRange() {
        return Range.closed(userInput + 1, gameModel.getCurrentRange().upperEndpoint());
    }

    private boolean userEnteredIncorrectValue(int userNumber) {
        return ! gameModel.getCurrentRange().contains(userNumber);
    }

    void printStatisticsAboutGame() {
        LOGGER.debug("...the secretNumber has been hit. The game is finished.");
        output.printlnMessageWithKey(GENERAL_MESSAGE_BUNDLE, GAME_TARGET_IS_HIT);
        output.printlnString(viewGenerator.getGameStatisticsView(gameModel.getUserAttempts()));
    }
}
