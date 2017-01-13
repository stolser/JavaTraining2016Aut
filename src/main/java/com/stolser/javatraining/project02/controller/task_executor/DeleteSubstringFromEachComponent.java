package com.stolser.javatraining.project02.controller.task_executor;

import com.stolser.javatraining.project02.model.CharSequence;
import com.stolser.javatraining.project02.model.CharSequenceFactory;
import com.stolser.javatraining.project02.model.flyweight_factory.CachedCharSequenceFactory;
import com.stolser.javatraining.project02.model.flyweight_factory.Character;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DeleteSubstringFromEachComponent implements TaskExecutor {
    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteSubstringFromEachComponent.class);
    private static final String ADDING_CURRENT_CHAR_LIST_S = "Adding currentCharList = %s";
    private static final String ADDING_CURRENT_CHAR_LIST = "Adding currentCharList = []";
    private static final String ADDING_CURRENT_CHAR_LIST_S1 = "Adding currentCharList = %s";
    private static final String ADDING_CURRENT_CHAR_LIST_S2 = "Adding currentCharList = %s";
    private static final String NOT_DELETED_SYMBOLS_S = "notDeletedSymbols = %s";
    private CharSequenceFactory factory = new CachedCharSequenceFactory();
    private CharSequence first;
    private CharSequence last;
    private CharSequence text;
    private boolean firstFound;
    private List<List<CharSequence>> charLists;
    private Iterator<CharSequence> symbolIt;
    private List<CharSequence> currentCharList;
    private CharSequence newText;
    private Iterator<CharSequence> sentenceIt;

    public DeleteSubstringFromEachComponent(CharSequence text, char first, char last) {
        this.text = text;
        this.first = factory.getCharacter(first);
        this.last = factory.getCharacter(last);
    }

    public void setFactory(CharSequenceFactory factory) {
        this.factory = factory;
    }

    @Override
    public CharSequence execute() {
        newText = factory.getText();
        sentenceIt = text.iterator();

        while (sentenceIt.hasNext()) {
            resetValuesForCurrentSentence();
            processCurrentSentence();
            addCurrentCharListToLists();
            makeSentenceAndAddToText();
        }

        return newText;
    }

    private void resetValuesForCurrentSentence() {
        charLists = new ArrayList<>();
        symbolIt = sentenceIt.next().iterator();
        firstFound = false;
        currentCharList = null;
    }

    private void processCurrentSentence() {
        while (symbolIt.hasNext()) {
            CharSequence currentSymbol = symbolIt.next();
            boolean addSymbol = true;

            if (firstSymbolOccurredFirstTime(currentSymbol)) {
                processFirstSymbolOccurrence();
                addSymbol = false;
            } else if (lastSymbolOccurredAfterFirst(currentSymbol)) {
                processLastSymbolOccurrence();
                addSymbol = false;
            }

            if (addSymbol) {
                addSymbolToCurrentCharList(currentSymbol);
            }
        }
    }

    private void processLastSymbolOccurrence() {
        if (currentCharList != null) {
            LOGGER.debug(String.format(ADDING_CURRENT_CHAR_LIST_S1, currentCharList));
            charLists.add(currentCharList);

            currentCharList = new ArrayList<>();
        }
    }

    private void processFirstSymbolOccurrence() {
        firstFound = true;

        if (currentCharList != null) {
            LOGGER.debug(String.format(ADDING_CURRENT_CHAR_LIST_S, currentCharList));
            charLists.add(currentCharList);
        } else if (charLists.isEmpty()) {
            charLists.add(new ArrayList<>());
            LOGGER.debug(ADDING_CURRENT_CHAR_LIST);
        }

        currentCharList = null;
    }

    private boolean lastSymbolOccurredAfterFirst(CharSequence currentSymbol) {
        return firstFound && currentSymbol.equals(last);
    }

    private boolean firstSymbolOccurredFirstTime(CharSequence currentSymbol) {
        return !firstFound && currentSymbol.equals(first);
    }

    private void addCurrentCharListToLists() {
        if (currentCharList != null) {
            LOGGER.debug(String.format(ADDING_CURRENT_CHAR_LIST_S2, currentCharList));
            charLists.add(currentCharList);
        }
    }

    private void makeSentenceAndAddToText() {
        List<CharSequence> notDeletedSymbols = getNotDeletedSymbols(charLists);
        LOGGER.debug(String.format(NOT_DELETED_SYMBOLS_S, notDeletedSymbols));

        if (!notDeletedSymbols.isEmpty()) {
            newText.add(getSentenceFromSymbols(notDeletedSymbols));
        }
    }

    private void addSymbolToCurrentCharList(CharSequence currentSymbol) {
        if (currentCharList == null) {
            currentCharList = new ArrayList<>();
        }

        currentCharList.add(currentSymbol);
    }

    private List<CharSequence> getNotDeletedSymbols(List<List<CharSequence>> charLists) {
        List<CharSequence> notDeletedSymbols = new ArrayList<>();

        int charListsSize = charLists.size();

        if (charListsSize != 0) {
            notDeletedSymbols.addAll(charLists.get(0));
        }

        if (charListsSize >= 3) {
            notDeletedSymbols.addAll(charLists.get(charListsSize - 1));
        }

        return notDeletedSymbols;
    }

    private CharSequence getSentenceFromSymbols(List<CharSequence> symbols) {
        CharSequence sentence = factory.getSentence();
        CharSequence currentWord = null;
        Iterator<CharSequence> it = symbols.iterator();
        boolean previousWasWordChar = false;
        boolean currentIsWordChar;

        while (it.hasNext()) {
            CharSequence currentSymbol = it.next();

            if (CharSequence.isWordCharacter((Character) currentSymbol)) {
                if (currentWord == null) {
                    currentWord = factory.getWord();
                }

                currentIsWordChar = true;
                currentWord.add(currentSymbol);
            } else {    // currentSymbol is not word character;
                currentIsWordChar = false;

                if (previousWasWordChar) {
                    sentence.add(currentWord);
                    currentWord = null;
                }

                sentence.add(currentSymbol);
            }

            previousWasWordChar = currentIsWordChar;
        }

        if (currentWord != null) {
            sentence.add(currentWord);
        }

        return sentence;
    }
}
