package com.SchoolManagementSystem.Repositories;

import java.util.List;
import java.util.ArrayList;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import com.SchoolManagementSystem.Configurations.DatabaseConfigurations;


/**
 *  Super Repositories
 */
public class SuperRepositories extends DatabaseConfigurations {

  /**
   * Get Class Name Repository
   *
   * @return List<String>
   *
   */
  public List<String> getClassNameRepository() {
    List<String> classNames = new ArrayList<>();
    Connection connection = getConnection();
    String queryString = "SELECT class_name FROM class";

    try {
      PreparedStatement statement = connection
        .prepareStatement(queryString);

      ResultSet resultSet = statement.executeQuery();

      while (resultSet.next()) {
        classNames.add(resultSet
          .getString("class_name"));
      }

      return classNames;

    } catch (SQLException exception) {
      exception.printStackTrace();
      return classNames;
    }
  }



  /**
   * Get Subject Name Repository
   *
   * @return List<String>
   *
   */
  public List<String> getSubjectNameRepository() {
    List<String> subjectNames = new ArrayList<>();
    Connection connection = getConnection();
    String queryString = "SELECT subject_name FROM subjects";

    try {
      PreparedStatement statement = connection
        .prepareStatement(queryString);

      ResultSet resultSet = statement.executeQuery();

      while (resultSet.next()) {
        subjectNames.add(resultSet
          .getString("subject_name"));
      }

      return subjectNames;

    } catch (SQLException exception) {
      exception.printStackTrace();
      return subjectNames;
    }
  }



  /**
   * Get Teacher Name Repository
   *
   * @return List<String>
   *
   */
  public List<String> getTeacherNameRepository() {
    List<String> TeacherNames = new ArrayList<>();
    Connection connection = getConnection();
    String queryString = "SELECT employee_name FROM employees WHERE employee_type = 'Guru'";

    try {
      PreparedStatement statement = connection
        .prepareStatement(queryString);

      ResultSet resultSet = statement.executeQuery();

      while (resultSet.next()) {
        TeacherNames.add(resultSet
          .getString("employee_name"));
      }

      return TeacherNames;

    } catch (SQLException exception) {
      exception.printStackTrace();
      return TeacherNames;
    }
  }
}
