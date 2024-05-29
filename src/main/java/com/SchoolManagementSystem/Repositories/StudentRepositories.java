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

import com.SchoolManagementSystem.Dtos.StudentDto;
import com.SchoolManagementSystem.Configurations.DatabaseConfigurations;



/**
 *  Student Repositories
 */
public class StudentRepositories extends DatabaseConfigurations {
  // Table Name
  private final String TABLENAME = "students";

  /**
   * Get Students Repository
   *
   * @return List<StudentDto>
   *
   */
  public List<StudentDto> getStudentsRepository() {
    List<StudentDto> studentsDataList = new ArrayList<>();
    Connection connection = getConnection();
    String queryString = String.format(
      "SELECT                " +
        "s.student_id,       " +
        "c.class_name,       " +
        "s.student_nisn,     " +
        "s.student_name,     " +
        "s.student_birth,    " +
        "s.student_gender,   " +
        "s.student_phone,    " +
        "s.student_address   " +
      "FROM %s s             " +
      "JOIN class c ON s.class_id = c.class_id"
      , this.TABLENAME
    );

    try {
      PreparedStatement statement = connection
        .prepareStatement(queryString);

      ResultSet resultSet = statement.executeQuery();

      while (resultSet.next()) {
        StudentDto student = new StudentDto();

        student.setStudentId(resultSet
          .getString("student_id"));
        student.setClassName(resultSet
          .getString("class_name"));
        student.setStudentNisn(resultSet
          .getString("student_nisn"));
        student.setStudentName(resultSet
          .getString("student_name"));
        student.setStudentBirth(resultSet
          .getString("student_birth"));
        student.setStudentGender(resultSet
          .getString("student_gender"));
        student.setStudentPhone(resultSet
          .getString("student_phone"));
        student.setStudentAddress(resultSet
          .getString("student_address"));

        studentsDataList.add(student);
      }

      return studentsDataList;

    } catch (SQLException exception) {
      exception.printStackTrace();
      return studentsDataList;
    }
  }



  /**
   * Create Student Repository
   *
   * @param dto StudentDto
   * @return Map<String, Object>
   *
   */
  public Map<String, Object> createStudentRepository(StudentDto dto) {
    Map<String, Object> response = new HashMap<>();

    Connection connection = getConnection();
    String queryString = String.format(
      "INSERT INTO %s   ( " +
        "class_id,        " +
        "student_nisn,     " +
        "student_name,     " +
        "student_birth,    " +
        "student_gender,   " +
        "student_phone,    " +
        "student_address  " +
      ") VALUES ( " + 
        "( " + 
          "SELECT class_id FROM class WHERE class_name = ? " +
        "), " + 
      "?, ?, ?, ?, ?, ?)", this.TABLENAME
    );

    try {
      // Transaction Mode (ACID Principles)
      connection.setAutoCommit(false);

      PreparedStatement statement = connection
        .prepareStatement(queryString);

      statement.setString(1, dto.getClassName());
      statement.setString(2, dto.getStudentNisn());
      statement.setString(3, dto.getStudentName());
      statement.setString(4, dto.getStudentBirth());
      statement.setString(5, dto.getStudentGender());
      statement.setString(6, dto.getStudentPhone());
      statement.setString(7, dto.getStudentAddress());

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
        "Data gagal ditambahkan karena tidak terdapat nama kelas yang dituju"
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
   * Update Student Repository
   *
   * @param dto StudentDto
   * @return Map<String, Object>
   *
   */
  public Map<String, Object> updateStudentRepository(StudentDto dto) {
    Map<String, Object> response = new HashMap<>();

    Connection connection = getConnection();
    String queryString = String.format(
      "UPDATE %s SET             "  +
        "class_id            = ( "  +
          "SELECT class_id FROM class WHERE class_name = ? " + 
        "),                      "  +
        "student_nisn       = ?, "  +
        "student_name       = ?, "  +
        "student_birth      = ?, "  +
        "student_gender     = ?, "  +
        "student_phone      = ?, "  +
        "student_address    = ?  "  +
      "WHERE student_id     = ?  ", 
      this.TABLENAME
    );

    try {
      // Transaction Mode (ACID Principles)
      connection.setAutoCommit(false);

      PreparedStatement statement = connection.prepareStatement(queryString);

      statement.setString(1, dto.getClassName());
      statement.setString(2, dto.getStudentNisn());
      statement.setString(3, dto.getStudentName());
      statement.setString(4, dto.getStudentBirth());
      statement.setString(5, dto.getStudentGender());
      statement.setString(6, dto.getStudentPhone());
      statement.setString(7, dto.getStudentAddress());
      statement.setString(8, dto.getStudentId());

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
   * Delete Student Repository
   *
   * @param classId String
   * @return Boolean
   *
   */
  public boolean deleteStudentRepository(String classId) {
    Connection connection = getConnection();
    String queryString = String.format(
      "DELETE FROM %s WHERE student_id = ?", this.TABLENAME
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
  public List<StudentDto> searchStudentsRepository(String key) {
    List<StudentDto> studentsDataList = new ArrayList<>();
    Connection connection = getConnection();
    String queryString = String.format(
      "SELECT              " +
        "s.student_id,     " +
        "c.class_name,     " +
        "s.student_nisn,   " +
        "s.student_name,   " +
        "s.student_birth,  " +
        "s.student_gender, " +
        "s.student_phone,  " +
        "s.student_address " +
      "FROM %s s           " +
      "JOIN class c ON s.class_id = c.class_id " +
      "WHERE (                                 " +
        "c.class_name         LIKE '%%%s%%' OR " +
        "s.student_nisn       LIKE '%%%s%%' OR " +
        "s.student_name       LIKE '%%%s%%' OR " +
        "s.student_birth      LIKE '%%%s%%' OR " +
        "s.student_gender     LIKE '%%%s%%' OR " +
        "s.student_phone      LIKE '%%%s%%' OR " +
        "s.student_address    LIKE '%%%s%%'    " +
      ")", this.TABLENAME,
      key, key, key,
      key, key, key, key
    );

    try {
      PreparedStatement statement = connection.prepareStatement(queryString);

      ResultSet resultSet = statement.executeQuery();

      while (resultSet.next()) {
        StudentDto student = new StudentDto();

        student.setStudentId(resultSet
          .getString("student_id"));
        student.setClassName(resultSet
          .getString("class_name"));
        student.setStudentNisn(resultSet
          .getString("student_nisn"));
        student.setStudentName(resultSet
          .getString("student_name"));
        student.setStudentBirth(resultSet
          .getString("student_birth"));
        student.setStudentGender(resultSet
          .getString("student_gender"));
        student.setStudentPhone(resultSet
          .getString("student_phone"));
        student.setStudentAddress(resultSet
          .getString("student_address"));

        studentsDataList.add(student);
      }

      return studentsDataList;

    } catch (SQLException exception) {
      exception.printStackTrace();
      return studentsDataList;
    }
  }
}
