package com.stolser.javatraining.designpatterns.behavioral.state;

import com.stolser.javatraining.designpatterns.behavioral.state.robot.Robot;

public class Client {
    public static void main(String[] args) {
        Robot robot = new Robot();

        robot.walk();
        robot.cook();
        robot.walk();
        robot.off();
        robot.walk();
        robot.off();
        robot.cook();
    }
}
