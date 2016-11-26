package com.stolser.javatraining.designpatterns.behavioral.chainOfResponsibility.handler;


import com.stolser.javatraining.designpatterns.behavioral.chainOfResponsibility.object.File;

public interface Handler {
    void setHandler(Handler handler);
    void process(File file);
    String getHandlerName();
}
