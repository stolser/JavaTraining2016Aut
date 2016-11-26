package com.stolser.javatraining.designpatterns.structural.facade;

import com.stolser.javatraining.designpatterns.structural.facade.complex_system.ScheduleServer;
import com.stolser.javatraining.designpatterns.structural.facade.facade.ScheduleServerFacade;

public class Client {
    public static void main(String[] args) {
        ScheduleServer scheduleServer = new ScheduleServer();
        ScheduleServerFacade facadeServer = new ScheduleServerFacade(scheduleServer);

        facadeServer.startServer();

        System.out.println("Start working......");
        System.out.println("After work done.........");

        facadeServer.stopServer();
    }
}
