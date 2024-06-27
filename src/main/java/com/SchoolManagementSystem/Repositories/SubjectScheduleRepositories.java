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

import com.SchoolManagementSystem.Dtos.SubjectScheduleDto;
import com.SchoolManagementSystem.Configurations.DatabaseConfigurations;


/**
 *  Subject Schedule Repositories
 */
public class SubjectScheduleRepositories extends DatabaseConfigurations {
  // Table Name
  private final String TABLENAME = "subject_schedules";

  /**
   * Get Subject Schedules Repository
   *
   * @return List<SubjectScheduleDto>
   *
   */
  public List<SubjectScheduleDto> getSubjectSchedulesRepository() {
    List<SubjectScheduleDto> subjectSchedulesDataList = new ArrayList<>();
    Connection connection = getConnection();
    String queryString = String.format(
      "SELECT              " +
        "sc.schedule_id,   " +
        "c.class_name,     " +
        "s.subject_name,   " +
        "e.employee_name,  " +
        "sc.day_of_week,   " +
        "sc.time_start,    " +
        "sc.time_end       " +
      "FROM %s sc          " +
        "JOIN class     c ON sc.class_id    = c.class_id    " +
        "JOIN subjects  s ON sc.subject_id  = s.subject_id  " +
        "JOIN employees e ON sc.employee_id = e.employee_id " +
      "WHERE e.employee_type = 'Guru'"
      ,this.TABLENAME
    );

    try {
      PreparedStatement statement = connection
        .prepareStatement(queryString);

      ResultSet resultSet = statement.executeQuery();

      while (resultSet.next()) {
        SubjectScheduleDto subjectSchedule = new SubjectScheduleDto();

        subjectSchedule.setScheduleId(resultSet
          .getString("schedule_id"));
        subjectSchedule.setClassName(resultSet
          .getString("class_name"));
        subjectSchedule.setSubjectName(resultSet
          .getString("subject_name"));
        subjectSchedule.setEmployeeName(resultSet
          .getString("employee_name"));
        subjectSchedule.setScheduleDay(resultSet
          .getString("day_of_week"));
        subjectSchedule.setScheduleTimeStart(resultSet
          .getString("time_start"));
        subjectSchedule.setScheduleTimeEnd(resultSet
          .getString("time_end"));

        subjectSchedulesDataList.add(subjectSchedule);
      }

      return subjectSchedulesDataList;

    } catch (SQLException exception) {
      exception.printStackTrace();
      return subjectSchedulesDataList;
    }
  }



  /**
   * Create Subject Schedule Repository
   *
   * @param dto SubjectScheduleDto
   * @return Map<String, Object>
   *
   */
  public Map<String, Object> createSubjectScheduleRepository(SubjectScheduleDto dto) {
    Map<String, Object> response = new HashMap<>();

    Connection connection = getConnection();
    String queryString = String.format(
      "INSERT INTO %s  ( " +
        "class_id,       " +
        "subject_id,     " +
        "employee_id,    " +
        "day_of_week,    " +
        "time_start,     " +
        "time_end        " +
      ") VALUES        ( " + 
        "(  " + 
          "SELECT class_id FROM class WHERE class_name = ? " +
        "), " + 

        "(  " + 
          "SELECT subject_id FROM subjects WHERE subject_name = ? " +
        "), " + 

        "(  " + 
          "SELECT employee_id FROM employees WHERE employee_name = ? " +
        "), " + 

      "?, ?, ?)", this.TABLENAME
    );

    try {
      // Transaction Mode (ACID Principles)
      connection.setAutoCommit(false);

      PreparedStatement statement = connection
        .prepareStatement(queryString);

      statement.setString(1, dto.getClassName());
      statement.setString(2, dto.getSubjectName());
      statement.setString(3, dto.getEmployeeName());
      statement.setString(4, dto.getScheduleDay());
      statement.setString(5, dto.getScheduleTimeStart());
      statement.setString(6, dto.getScheduleTimeEnd());

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
   * Update Subject Schedule Repository
   *
   * @param dto SubjectScheduleDto
   * @return Map<String, Object>
   *
   */
  public Map<String, Object> updateSubjectScheduleRepository(SubjectScheduleDto dto) {
    Map<String, Object> response = new HashMap<>();

    Connection connection = getConnection();
    String queryString = String.format(
      "UPDATE %s SET           "  +
        "class_id         = (  "  +
          "SELECT class_id FROM class WHERE class_name = ? " + 
        "),                    "  +

        "subject_id       = (  "  +
          "SELECT subject_id FROM subjects WHERE subject_name = ? " + 
        "),                    "  +

        "employee_id      = (  "  +
          "SELECT employee_id FROM employees WHERE employee_name = ? " + 
        "),                    "  +

        "day_of_week      = ?, "  +
        "time_start       = ?, "  +
        "time_end         = ?  "  +
      "WHERE schedule_id  = ?  ", 
      this.TABLENAME
    );

    try {
      // Transaction Mode (ACID Principles)
      connection.setAutoCommit(false);

      PreparedStatement statement = connection.prepareStatement(queryString);

      statement.setString(1, dto.getClassName());
      statement.setString(2, dto.getSubjectName());
      statement.setString(3, dto.getEmployeeName());
      statement.setString(4, dto.getScheduleDay());
      statement.setString(5, dto.getScheduleTimeStart());
      statement.setString(6, dto.getScheduleTimeEnd());
      statement.setString(7, dto.getScheduleId());

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
   * Delete Subject Schedule Repository
   *
   * @param scheduleId String
   * @return Boolean
   *
   */
  public boolean deleteSubjectScheduleRepository(String scheduleId) {
    Connection connection = getConnection();
    String queryString = String.format(
      "DELETE FROM %s WHERE schedule_id = ?", this.TABLENAME
    );

    try {
      // Transaction Mode (ACID Principles)
      connection.setAutoCommit(false);

      PreparedStatement statement = connection.prepareStatement(queryString);

      statement.setString(1, scheduleId);

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
  public List<SubjectScheduleDto> searchSubjectSchedulesRepository(String key) {
    List<SubjectScheduleDto> subjectSchedulesDataList = new ArrayList<>();
    Connection connection = getConnection();
    String queryString = String.format(
      "SELECT              " +
        "sc.schedule_id,   " +
        "c.class_name,     " +
        "s.subject_name,   " +
        "e.employee_name,  " +
        "sc.day_of_week,   " +
        "sc.time_start,    " +
        "sc.time_end       " +
      "FROM %s sc          " +
        "JOIN class     c ON sc.class_id    = c.class_id    " +
        "JOIN subjects  s ON sc.subject_id  = s.subject_id  " +
        "JOIN employees e ON sc.employee_id = e.employee_id " +
      "WHERE (                              " +
        "c.class_name      LIKE '%%%s%%' OR " +
        "s.subject_name    LIKE '%%%s%%' OR " +
        "e.employee_name   LIKE '%%%s%%' OR " +
        "sc.day_of_week    LIKE '%%%s%%' OR " +
        "sc.time_start     LIKE '%%%s%%' OR " +
        "sc.time_end       LIKE '%%%s%%'    " +
      ")", this.TABLENAME,
      key, key, key,
      key, key, key
    );

    try {
      PreparedStatement statement = connection.prepareStatement(queryString);

      ResultSet resultSet = statement.executeQuery();

      while (resultSet.next()) {
        SubjectScheduleDto subjectSchedule = new SubjectScheduleDto();

        subjectSchedule.setScheduleId(resultSet
          .getString("schedule_id"));
        subjectSchedule.setClassName(resultSet
          .getString("class_name"));
        subjectSchedule.setSubjectName(resultSet
          .getString("subject_name"));
        subjectSchedule.setEmployeeName(resultSet
          .getString("employee_name"));
        subjectSchedule.setScheduleDay(resultSet
          .getString("day_of_week"));
        subjectSchedule.setScheduleTimeStart(resultSet
          .getString("time_start"));
        subjectSchedule.setScheduleTimeEnd(resultSet
          .getString("time_end"));

        subjectSchedulesDataList.add(subjectSchedule);
      }

      return subjectSchedulesDataList;

    } catch (SQLException exception) {
      exception.printStackTrace();
      return subjectSchedulesDataList;
    }
  }
  
}
