package com.SchoolManagementSystem.Repositories;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.SQLIntegrityConstraintViolationException;

import com.SchoolManagementSystem.Dtos.ClassesDto;
import com.SchoolManagementSystem.Configurations.DatabaseConfigurations;



/**
 *  Class Repositories
 */
public class ClassesRepositories extends DatabaseConfigurations {
  // Table Name
  private final String TABLENAME = "class";

  /**
   * Get Classes Repository
   *
   * @return List<ClassesDto>
   *
   */
  public List<ClassesDto> getClassesRepository() {
    List<ClassesDto> classesDataList = new ArrayList<>();
    Connection connection = getConnection();
    String queryString = String.format(
      "SELECT * FROM %s", this.TABLENAME
    );

    try {
      PreparedStatement statement = connection.prepareStatement(queryString);

      ResultSet resultSet = statement.executeQuery();

      while (resultSet.next()) {
        ClassesDto classes = new ClassesDto();

        classes.setClassId(resultSet
          .getString("class_id"));
        classes.setClassName(resultSet
          .getString("class_name"));
        classes.setClassCapacity(Integer.toString(resultSet
        .getInt("class_capacity")));

        classesDataList.add(classes);
      }

      return classesDataList;

    } catch (SQLException exception) {
      exception.printStackTrace();
      return classesDataList;
    }
  }



  /**
   * Create Class Repository
   *
   * @param dto ClassesDto
   * @return Map<String, Object>
   *
   */
  public Map<String, Object> createClassRepository(ClassesDto dto) {
    Map<String, Object> response = new HashMap<>();

    Connection connection = getConnection();
    String queryString = String.format(
      "INSERT INTO %s   ( "  +
        "class_name,      "  +
        "class_capacity   "  +
      ")"                    +
      "VALUES (?, ?)", this.TABLENAME
    );

    try {
      // Transaction Mode (ACID Principles)
      connection.setAutoCommit(false);

      PreparedStatement statement = connection
        .prepareStatement(queryString);

      statement.setString(1, dto.getClassName());
      statement.setInt(
        2, Integer.parseInt(dto.getClassCapacity()
      ));

      if (statement.executeUpdate() > 0) {
        connection.commit();

        response.put("result", true);
        response.put("message", "Data Berhasil Ditambahkan.");

        return response;

      } else {
        connection.rollback();

        response.put("result", false);
        response.put("message", "Data Gagal Ditambahkan, Coba Lagi.");

        return response;
      }

    } catch (SQLIntegrityConstraintViolationException exception) {
      exception.printStackTrace();

      try { connection.rollback(); }
      catch (SQLException rollbackException) {
        rollbackException.printStackTrace();
      }

      response.put("result", false);
      response.put(
        "message", 
        "Data gagal ditambahkan karena nama kelas sudah dipakai"
      );

      return response;

    } catch (SQLException exception) {
      exception.printStackTrace();

      try { connection.rollback(); }
      catch (SQLException rollbackException) {
        rollbackException.printStackTrace();
      }

      response.put("result", false);
      response.put("message", "Data Gagal Ditambahkan, Coba Lagi.");

      return response;

    } finally {
      try { connection.setAutoCommit(true); }
      catch (SQLException exception) { exception.printStackTrace(); }
    }
  }



  /**
   * Update Class Repository
   *
   * @param dto ClassesDto
   * @return Map<String, Object>
   *
   */
  public Map<String, Object> updateClassRepository(ClassesDto dto) {
    Map<String, Object> response = new HashMap<>();

    Connection connection = getConnection();
    String queryString = String.format(
      "UPDATE %s SET            "  +
        "class_name        = ?, "  +
        "class_capacity    = ?  "  +
      "WHERE class_id      = ?  ", 
      this.TABLENAME
    );

    try {
      // Transaction Mode (ACID Principles)
      connection.setAutoCommit(false);

      PreparedStatement statement = connection.prepareStatement(queryString);

      statement.setString(1, dto.getClassName());
      statement.setInt(2, Integer.parseInt(
        dto.getClassCapacity()));
      statement.setString(3, dto.getClassId());

      if (statement.executeUpdate() > 0) {
        connection.commit();

        response.put("result", true);
        response.put("message", "Data Berhasil Diperbarui.");

        return response;

      } else {
        connection.rollback();

        response.put("result", false);
        response.put("message", "Data Gagal Diperbarui, Coba Lagi.");

        return response;
      }

    } catch (SQLException exception) {
      exception.printStackTrace();

      try { connection.rollback(); }
      catch (SQLException rollbackException) {
        rollbackException.printStackTrace();
      }

      response.put("result", false);
      response.put("message", "Data Gagal Diperbarui, Coba Lagi.");

      return response;

    } finally {
      try { connection.setAutoCommit(true); }
      catch (SQLException exception) { exception.printStackTrace(); }
    }
  }



  /**
   * Delete Class Repository
   *
   * @param classId String
   * @return Boolean
   *
   */
  public boolean deleteClassRepository(String classId) {
    Connection connection = getConnection();
    String queryString = String.format(
      "DELETE FROM %s WHERE class_id = ?", this.TABLENAME
    );

    try {
      // Transaction Mode (ACID Principles)
      connection.setAutoCommit(false);

      PreparedStatement statement = connection.prepareStatement(queryString);

      statement.setString(1, classId);

      if (statement.executeUpdate() > 0) {
        connection.commit();
        return true;

      } else {
        connection.rollback();
        return false;
      }

    } catch (SQLException exception) {
      exception.printStackTrace();

      try { connection.rollback(); }
      catch (SQLException rollbackException) {
        rollbackException.printStackTrace();
      }

      return false;

    } finally {
      try {
        connection.setAutoCommit(true);
      }
      catch (SQLException exception) { exception.printStackTrace(); }
    }
  }



  /**
   * Search Classes Repository
   *
   * @param key String
   * @return List<ClassesDto>
   *
   */
  public List<ClassesDto> searchClassesRepository(String key) {
    List<ClassesDto> classesDataList = new ArrayList<>();
    Connection connection = getConnection();
    String queryString = String.format(
      "SELECT * FROM %s WHERE (           " +
        "class_name      LIKE '%%%s%%' OR " +
        "class_capacity  LIKE '%%%s%%'    " +
      ")", this.TABLENAME,
      key, key, key
    );

    try {
      PreparedStatement statement = connection.prepareStatement(queryString);

      ResultSet resultSet = statement.executeQuery();

      while (resultSet.next()) {
        ClassesDto classes = new ClassesDto();

        classes.setClassId(resultSet
          .getString("class_id"));
        classes.setClassName(resultSet
          .getString("class_name"));
        classes.setClassCapacity(Integer.toString(resultSet
          .getInt("class_capacity")));

        classesDataList.add(classes);
      }

      return classesDataList;

    } catch (SQLException exception) {
      exception.printStackTrace();
      return classesDataList;
    }
  }
}
