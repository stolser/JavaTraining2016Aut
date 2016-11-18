package com.stolser.javatraining.designpatterns.creational.builder;

import java.sql.SQLException;

public class ClientRunner {
    public static void main(String[] args) throws SQLException {
        new Client().start();
    }
}

class Client {
    private final String DRIVER_NAME = "com.mysql.jdbc.Driver";
    private final String URL = "jdbc:mysql://localhost:3306/";
    private final String DB_NAME = "store_chain";
    private final String USER_NAME = "test";
    private final String USER_PASSWORD = "test";

    void start() throws SQLException {
        ConnectionPool pool = ConnectionPool.getBuilder(URL, DB_NAME)
                .setDriverClassName(DRIVER_NAME)
                .setUserName(USER_NAME)
                .setPassword(USER_PASSWORD)
                .build();

        System.out.println("getting a connection...");
//        Connection conn = pool.getConnection();
    }
}
