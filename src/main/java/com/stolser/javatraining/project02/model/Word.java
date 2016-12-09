package com.stolser.javatraining.project02.model;

class Word extends AbstractCharSequence {
    @Override
    public boolean canAdd(CharSequence component) {
        return component instanceof Character;
    }

    @Override
    public void print() {
        System.out.print("\'");
        super.print();
        System.out.print("\'");
    }
}
