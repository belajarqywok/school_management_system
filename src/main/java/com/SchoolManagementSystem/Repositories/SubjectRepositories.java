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

import com.SchoolManagementSystem.Dtos.SubjectDto;
import com.SchoolManagementSystem.Configurations.DatabaseConfigurations;



/**
 *  Subject Repositories
 */
public class SubjectRepositories extends DatabaseConfigurations {
  // Table Name
  private final String TABLENAME = "subjects";

  /**
   * Get Subjects Repository
   *
   * @return List<SubjectDto>
   *
   */
  public List<SubjectDto> getSubjectsRepository() {
    List<SubjectDto> subjectsDataList = new ArrayList<>();
    Connection connection = getConnection();
    String queryString = String.format(
      "SELECT * FROM %s", this.TABLENAME
    );

    try {
      PreparedStatement statement = connection.prepareStatement(queryString);

      ResultSet resultSet = statement.executeQuery();

      while (resultSet.next()) {
        SubjectDto subject = new SubjectDto();

        subject.setSubjectId(resultSet
          .getString("subject_id"));
        subject.setSubjectName(resultSet
          .getString("subject_name"));
        subject.setSubjectDesc(resultSet
          .getString("subject_desc"));

        subjectsDataList.add(subject);
      }

      return subjectsDataList;

    } catch (SQLException exception) {
      exception.printStackTrace();
      return subjectsDataList;
    }
  }



  /**
   * Create Subject Repository
   *
   * @param dto SubjectDto
   * @return Map<String, Object>
   *
   */
  public Map<String, Object> createSubjectRepository(SubjectDto dto) {
    Map<String, Object> response = new HashMap<>();

    Connection connection = getConnection();
    String queryString = String.format(
      "INSERT INTO %s  ( "  +
        "subject_name,   "  +
        "subject_desc    "  +
      ")"                   +
      "VALUES (?, ?)", this.TABLENAME
    );

    try {
      // Transaction Mode (ACID Principles)
      connection.setAutoCommit(false);

      PreparedStatement statement = connection
        .prepareStatement(queryString);

      statement.setString(1, dto.getSubjectName());
      statement.setString(2, dto.getSubjectDesc());

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
   * Update Subject Repository
   *
   * @param dto SubjectDto
   * @return Map<String, Object>
   *
   */
  public Map<String, Object> updateSubjectRepository(SubjectDto dto) {
    Map<String, Object> response = new HashMap<>();

    Connection connection = getConnection();
    String queryString = String.format(
      "UPDATE %s SET            "  +
        "subject_name      = ?, "  +
        "subject_desc      = ?  "  +
      "WHERE subject_id    = ?  ", 
      this.TABLENAME
    );

    try {
      // Transaction Mode (ACID Principles)
      connection.setAutoCommit(false);

      PreparedStatement statement = connection.prepareStatement(queryString);

      statement.setString(1, dto.getSubjectName());
      statement.setString(2, dto.getSubjectDesc());
      statement.setString(3, dto.getSubjectId());

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
   * Delete Subject Repository
   *
   * @param subjectId String
   * @return Boolean
   *
   */
  public boolean deleteSubjectRepository(String subjectId) {
    Connection connection = getConnection();
    String queryString = String.format(
      "DELETE FROM %s WHERE subject_id = ?", this.TABLENAME
    );

    try {
      // Transaction Mode (ACID Principles)
      connection.setAutoCommit(false);

      PreparedStatement statement = connection.prepareStatement(queryString);

      statement.setString(1, subjectId);

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
   * Search Subject Repository
   *
   * @param key String
   * @return List<ClassesDto>
   *
   */
  public List<SubjectDto> searchSubjectsRepository(String key) {
    List<SubjectDto> subjectsDataList = new ArrayList<>();
    Connection connection = getConnection();
    String queryString = String.format(
      "SELECT * FROM %s WHERE (           " +
        "subject_name    LIKE '%%%s%%' OR " +
        "subject_desc    LIKE '%%%s%%'    " +
      ")", this.TABLENAME,
      key, key
    );

    try {
      PreparedStatement statement = connection.prepareStatement(queryString);

      ResultSet resultSet = statement.executeQuery();

      while (resultSet.next()) {
        SubjectDto subject = new SubjectDto();

        subject.setSubjectId(resultSet
          .getString("subject_id"));
        subject.setSubjectName(resultSet
          .getString("subject_name"));
        subject.setSubjectDesc(resultSet
          .getString("subject_desc"));

        subjectsDataList.add(subject);
      }

      return subjectsDataList;

    } catch (SQLException exception) {
      exception.printStackTrace();
      return subjectsDataList;
    }
  }
}
