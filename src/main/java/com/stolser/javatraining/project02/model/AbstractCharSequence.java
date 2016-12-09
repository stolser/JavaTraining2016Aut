package com.stolser.javatraining.project02.model;

import java.util.*;

abstract class AbstractCharSequence implements CharSequence, Iterable<CharSequence> {
    private List<CharSequence> components = new ArrayList<>();
    protected int modCount;

    public boolean add(CharSequence component) {
        if (canAdd(component)) {
            components.add(component);
            modCount++;

            return true;
        }

        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<CharSequence> iterator() {
        return new CompositeIterator(components.iterator());
    }

    private class CompositeIterator implements Iterator<CharSequence> {
        Deque<Iterator<CharSequence>> iteratorStack = new ArrayDeque<>();
        private int expectedModCount = modCount;

        private CompositeIterator(Iterator<CharSequence> iterator) {
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

            return charSequence;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        private void checkForModification() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }
    }
}
