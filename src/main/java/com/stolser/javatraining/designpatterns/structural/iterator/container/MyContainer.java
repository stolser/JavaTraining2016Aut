package com.stolser.javatraining.designpatterns.structural.iterator.container;

import com.stolser.javatraining.designpatterns.structural.iterator.iterator.Iterable;
import com.stolser.javatraining.designpatterns.structural.iterator.iterator.Iterator;

public class MyContainer implements Iterable<String> {
    private String[] words;

    public MyContainer(String[] words) {
        this.words = words;
    }

    @Override
    public Iterator<String> iterator() {
        return new WordsIterator();
    }

    private class WordsIterator implements Iterator<String> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return (currentIndex < words.length);
        }

        @Override
        public String next() {
            String nextWord = words[currentIndex];
            currentIndex++;

            return nextWord;
        }
    }
}
