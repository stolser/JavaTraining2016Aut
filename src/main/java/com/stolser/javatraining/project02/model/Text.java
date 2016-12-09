package com.stolser.javatraining.project02.model;

class Text extends AbstractCharSequence {
    @Override
    public boolean canAdd(CharSequence component) {
        return component instanceof Sentence;
    }

    @Override
    public void print() {
        System.out.println("=== The beginning of the Text...");
        super.print();
        System.out.println("=== ... the end of the Text");
    }
}
