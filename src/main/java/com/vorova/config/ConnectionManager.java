package com.vorova.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class ConnectionManager {

    private static final String URL = "jdbc:postgresql://localhost:5434/postgres";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    public static Connection open() {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Не удалось открыть соединение");
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            System.err.println("Не удалось зарегистрировать драйвер");
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

}