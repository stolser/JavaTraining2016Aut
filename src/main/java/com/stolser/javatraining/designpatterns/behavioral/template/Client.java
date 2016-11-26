package com.stolser.javatraining.designpatterns.behavioral.template;

import com.stolser.javatraining.designpatterns.behavioral.template.template.ConnectionTemplate;
import com.stolser.javatraining.designpatterns.behavioral.template.template.MySqlCsvConnection;
import com.stolser.javatraining.designpatterns.behavioral.template.template.OracleTxtConnection;

public class Client {
    public static void main(String[] args) {
        System.out.println("-------------- For MySQL....");
        ConnectionTemplate template = new MySqlCsvConnection();
        template.run();

        System.out.println("-------------- For Oracle...");
        template = new OracleTxtConnection();
        template.run();
    }
}
