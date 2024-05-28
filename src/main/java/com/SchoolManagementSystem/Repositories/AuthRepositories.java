package com.SchoolManagementSystem.Repositories;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import com.SchoolManagementSystem.Configurations.DatabaseConfigurations;

/**
 *  Auth Repositories
 */
public class AuthRepositories extends DatabaseConfigurations {
  /**
   * Login Repository
   * 
   * @param username
   * @param password
   * @return
   */
  public boolean loginRepo(String username, String password) {
    Connection connection = getConnection();
    String query = (
      "SELECT * FROM admin_auth WHERE auth_username = ?" +
      "AND auth_password = ?"
    );
        
    try {
      PreparedStatement statement = connection
        .prepareStatement(query);
                
      statement.setString(1, username);
      statement.setString(2, password);

      ResultSet resultSet = statement.executeQuery();
      return resultSet.next() ? true : false;

    } catch (SQLException exception) {
      exception.printStackTrace();
      return false;

    }
  }

  // Manual testing
  public static void main(String[] args) {
    AuthRepositories repository = new AuthRepositories();
    boolean result = repository.loginRepo("", "");

    System.out.println(result);
  }
}
