package com.botscrewtest.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
    private static Connection connection;

    public static Connection getConnection() {
        FileInputStream fis;
        Properties property = new Properties();
        try {
            fis = new FileInputStream("src/main/resources/application.properties");
            property.load(fis);
            String url = property.getProperty("db.url");
            String user = property.getProperty("db.user");
            String password = property.getProperty("db.password");
            connection = DriverManager.getConnection(url, user, password);
        } catch (IOException ex) {
            throw new RuntimeException("Cannot find file", ex);
        } catch (SQLException ex) {
            throw new RuntimeException("Cannot get connection to db", ex);
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            connection.close();
        } catch (SQLException ex) {
            throw new RuntimeException("Cannot close connection", ex);
        }
    }
}
