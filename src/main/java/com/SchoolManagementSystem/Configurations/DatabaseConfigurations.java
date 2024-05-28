package com.SchoolManagementSystem.Configurations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConfigurations {

    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/school_management_system_db";
    private static final String USER = "root";
    private static final String PASS = "";

    private Connection connection;

    public DatabaseConfigurations() {
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
