package com.stolser.javatraining.designpatterns.creational.builder.builder;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

import static com.google.common.base.Preconditions.checkNotNull;

public class ConnectionPool {
    private static final String USER_NAME_DEFAULT = "test";
    private static final String USER_PASSWORD_DEFAULT = "test";
    private static final String DRIVER_NAME_DEFAULT = "com.mysql.jdbc.Driver";
    private static final int MAX_TOTAL_CONNECTIONS = 10;
    private BasicDataSource dataSource;
    private String urlWithDbName;

    private ConnectionPool(Builder builder) {
        dataSource = new BasicDataSource();
        dataSource.setDriverClassName(builder.driverClassName);
        dataSource.setUrl(builder.url + builder.dbName + "?useSSL=false");
        dataSource.setUsername(builder.userName);
        dataSource.setPassword(builder.password);
        dataSource.setMaxTotal(builder.maxConnections);
        urlWithDbName =  builder.url + builder.dbName;
    }

    public static Builder getBuilder(String url, String dbName) {
        return new Builder(url, dbName);
    }

    public Connection getConnection() throws SQLException {
        System.out.printf("datasource = %s%n", dataSource);
        return dataSource.getConnection();
    }

    public static class Builder {
        private String driverClassName;
        private String url;
        private String dbName;
        private String userName;
        private String password;
        private int maxConnections;

        public ConnectionPool build() {
            return new ConnectionPool(this);
        }

        private Builder(String url, String dbName) {
            checkNotNull(url, "url should not be null.");
            checkNotNull(url, "dbName should not be null.");
            this.url = url;
            this.dbName = dbName;
            this.driverClassName = DRIVER_NAME_DEFAULT;
            this.userName = USER_NAME_DEFAULT;
            this.password = USER_PASSWORD_DEFAULT;
            this.maxConnections = MAX_TOTAL_CONNECTIONS;
        }

        public Builder setDriverClassName(String driverClassName) {
            checkNotNull(driverClassName, "driverClassName should not be null.");
            this.driverClassName = driverClassName;
            return this;
        }

        public Builder setUserName(String userName) {
            checkNotNull(userName, "userName should not be null.");
            this.userName = userName;
            return this;
        }

        public Builder setPassword(String password) {
            checkNotNull(password, "password should not be null.");
            this.password = password;
            return this;
        }

        public Builder setMaxConnections(int maxConnections) {
            this.maxConnections = maxConnections;
            return this;
        }

    }
}
