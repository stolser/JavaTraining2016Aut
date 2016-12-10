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
    private CharSequenceFactory factory = new CachedCharSequenceFactory();
    private CharSequence first;
    private CharSequence last;
    private CharSequence text;

    public DeleteSubstringFromEachComponent(CharSequence text, char first, char last) {
        this.text = text;
        this.first = factory.getCharacter(first);
        this.last = factory.getCharacter(last);
    }

    @Override
    public CharSequence execute() {
        CharSequence newText = factory.getText();
        Iterator<CharSequence> sentenceIt = text.iterator();

        while (sentenceIt.hasNext()) {
            List<List<CharSequence>> charLists = new ArrayList<>();

            Iterator<CharSequence> symbolIt = sentenceIt.next().iterator();
            boolean firstFound = false;

            List<CharSequence> currentCharList = null;
            while (symbolIt.hasNext()) {
                CharSequence thisSymbol = symbolIt.next();

                if (!firstFound && thisSymbol.equals(first)) {
                    firstFound = true;
                    charLists.add(currentCharList);

                    LOGGER.debug("Adding currentCharList = " + currentCharList);
                    currentCharList = null;

                    continue;
                } else {
                    if (firstFound && thisSymbol.equals(last)) {
                        if (currentCharList != null) {
                            charLists.add(currentCharList);

                            LOGGER.debug("Adding currentCharList = " + currentCharList);
                            currentCharList = null;
                        }

                        continue;
                    }
                }

                if (currentCharList == null) {
                    currentCharList = new ArrayList<>();
                }

                currentCharList.add(thisSymbol);
            }

            if (currentCharList != null) {
                LOGGER.debug("Adding currentCharList = " + currentCharList);

                charLists.add(currentCharList);
            }

            List<CharSequence> notDeletedSymbols = getNotDeletedSymbols(charLists);
            LOGGER.debug("notDeletedSymbols = " + notDeletedSymbols);

            if (notDeletedSymbols.size() > 0) {
                newText.add(getSentenceFromSymbols(notDeletedSymbols));
            }

        }

        return newText;
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
