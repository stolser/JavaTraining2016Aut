package com.stolser.javatraining.block02.morelessgame.model.game;

import com.google.common.collect.Range;

/**
 * Represents an abstraction of user attempt to guess a number picked by the computer.
 */
public interface UserAttempt {

    void setNewRange(Range<Integer> newRange);

    void setNumber(int number);

    void setResult(UserAttemptImpl.AttemptResult result);

    int getSerialNo();

    Range<Integer> getCurrentRange();

    Range<Integer> getNewRange();

    int getNumber();

    UserAttemptImpl.AttemptResult getResult();
}
