package com.SchoolManagementSystem.Configurations;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConfigurations {
    /*
        Hikari Configuration
    */
    public Connection hikariConfiguration() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost/uts_visual_programming");
        config.setUsername("root");
        config.setPassword("");
        config.setMaximumPoolSize(10);

        HikariDataSource dataSource = new HikariDataSource(config);
        
        try {
            return dataSource.getConnection();
        } catch (SQLException exception) {
            exception.printStackTrace();
            return null;
        }
    }
}
