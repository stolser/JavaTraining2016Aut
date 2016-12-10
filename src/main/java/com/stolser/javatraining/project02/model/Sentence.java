package com.stolser.javatraining.project02.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayDeque;
import java.util.ConcurrentModificationException;
import java.util.Deque;
import java.util.Iterator;

class Sentence extends AbstractCharSequence {
    private static final Logger LOGGER = LoggerFactory.getLogger(Sentence.class);
    public Sentence() {
        LOGGER.debug("Creating a new Sentence.");
    }

    @Override
    public boolean canAdd(CharSequence component) {
        return (component instanceof Word)
                || (component instanceof Character);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("Sentence: \"");
        components.forEach(component -> builder.append(component.toString()));
        builder.append("\"\n");

        return builder.toString();
    }

    @Override
    public Iterator<CharSequence> iterator() {
        return new IteratorOnCharacters(components.iterator());
    }

    private class IteratorOnCharacters implements Iterator<CharSequence> {
        Deque<Iterator<CharSequence>> iteratorStack = new ArrayDeque<>();
        private int expectedModCount = modCount;

        private IteratorOnCharacters(Iterator<CharSequence> iterator) {
            iteratorStack.push(iterator);
        }

        public boolean hasNext() {
            if (iteratorStack.isEmpty()) {
                return false;
            }

            if (iteratorStack.peek().hasNext()) {
                return true;
            } else {
                iteratorStack.pop();
            }

            return hasNext();
        }

        public CharSequence next() {
            checkForModification();

            if (!hasNext())
                return null;

            CharSequence charSequence = iteratorStack.peek().next();
            iteratorStack.push(charSequence.iterator());

            if (charSequence instanceof Character) {
                return charSequence;
            } else {
                return next();
            }
        }

        public void remove() {
            // size--;
            throw new UnsupportedOperationException();
        }

        private void checkForModification() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Sentence sentence = (Sentence) o;

        return components != null ? components.equals(sentence.components) : sentence.components == null;

    }

    @Override
    public int hashCode() {
        return components != null ? components.hashCode() : 0;
    }
}
