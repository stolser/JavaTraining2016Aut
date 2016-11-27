package com.stolser.javatraining.designpatterns.structural.flyweight.entity.platform;

import com.stolser.javatraining.designpatterns.structural.flyweight.entity.Code;

public class CPlatform implements Platform {
    public CPlatform() {
        System.out.println("CPlatform object created");
    }

    @Override
    public void execute(Code code) {
        System.out.println("Compiling and executing C code.");
    }
}