package com.stolser.javatraining.designpatterns.structural.facade.complex_system;

public class ScheduleServer {
    public void startBooting() {
        System.out.println("start booting ...");
    }

    public void readSystemConfigFile() {
        System.out.println("read system config file ...");
    }

    public void init() {
        System.out.println("initialization ...");
    }

    public void initializeContext() {
        System.out.println("initialization of the context ...");
    }

    public void initializeListeners() {
        System.out.println("initialization of the listeners ...");
    }

    public void createSystemObjects() {
        System.out.println("creating system objects ...");
    }

    public void releaseProcesses() {
        System.out.println("releasing processes ...");
    }

    public void destroy() {
        System.out.println("destroying ...");
    }

    public void destroySystemObjects() {
        System.out.println("destroying system objects ...");
    }

    public void destroyListeners() {
        System.out.println("destroying all the listeners ...");
    }

    public void destroyContext() {
        System.out.println("destroying the context ...");
    }

    public void shutdown() {
        System.out.println("shutting down ...");
    }
}
