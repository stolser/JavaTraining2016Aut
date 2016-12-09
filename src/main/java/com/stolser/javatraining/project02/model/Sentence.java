package com.stolser.javatraining.project02.model;

class Sentence extends AbstractCharSequence {
    @Override
    public boolean canAdd(CharSequence component) {
        return (component instanceof Word)
                || (component instanceof Character);
    }

    @Override
    public void print() {
        System.out.print("Sentence: \"");
        super.print();
        System.out.println("\"");
    }
}
