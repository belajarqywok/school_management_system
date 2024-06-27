package com.SchoolManagementSystem.Repositories;

import java.util.List;
import java.util.ArrayList;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import com.SchoolManagementSystem.Dtos.StudentAttendanceDto;
import com.SchoolManagementSystem.Configurations.DatabaseConfigurations;



/**
 *  Student Attendance Repositories
 */
public class StudentAttendanceRepositories extends DatabaseConfigurations {
  // Table Name
  private final String TABLENAME = "student_attendances";

  /**
   * Get Student Attendance Repository
   *
   * @return List<StudentAttendanceDto>
   *
   */
  public List<StudentAttendanceDto> getStudentAttendancesRepository() {
    List<StudentAttendanceDto> studentAttendancesDataList = new ArrayList<>();
    Connection connection = getConnection();
    String queryString = String.format(
      "SELECT               " +
        "s.student_name,    " +
        "s.student_nisn     " +
      "FROM %s sa           " +
        "JOIN students s ON sa.student_id = s.student_id  " +
      "WHERE sa.student_attendance_status  = 'Hadir'      "
      ,this.TABLENAME
    );

    try {
      PreparedStatement statement = connection
        .prepareStatement(queryString);

      ResultSet resultSet = statement.executeQuery();

      while (resultSet.next()) {
        StudentAttendanceDto studentAttendance = new StudentAttendanceDto();

        studentAttendance.setStudentName(resultSet
          .getString("student_name"));
        studentAttendance.setStudentNisn(resultSet
          .getString("student_nisn"));

        studentAttendancesDataList.add(studentAttendance);
      }

      return studentAttendancesDataList;

    } catch (SQLException exception) {
      exception.printStackTrace();
      return studentAttendancesDataList;
    }
  }



  /**
   * Search Student Attendance Repository
   *
   * @param key String
   * @return List<StudentAttendanceDto>
   *
   */
  public List<StudentAttendanceDto> searchStudentAttendancesRepository(String key) {
    List<StudentAttendanceDto> studentAttendancesDataList = new ArrayList<>();
    Connection connection = getConnection();
    String queryString = String.format(
      "SELECT               " +
        "s.student_name,    " +
        "s.student_nisn     " +
      "FROM %s sa           " +
        "JOIN students s ON sa.student_id = s.student_id " +
      "WHERE (                                   " +
        "s.student_name    LIKE '%%%s%%' OR      " +
        "s.student_nisn    LIKE '%%%s%%' AND     " +
        "sa.student_attendance_status  = 'Hadir' " +
      ")", this.TABLENAME,
      key, key, key
    );

    try {
      PreparedStatement statement = connection.prepareStatement(queryString);

      ResultSet resultSet = statement.executeQuery();

      while (resultSet.next()) {
        StudentAttendanceDto studentAttendance = new StudentAttendanceDto();

        studentAttendance.setStudentName(resultSet
          .getString("student_name"));
        studentAttendance.setStudentNisn(resultSet
          .getString("student_nisn"));

        studentAttendancesDataList.add(studentAttendance);
      }

      return studentAttendancesDataList;

    } catch (SQLException exception) {
      exception.printStackTrace();
      return studentAttendancesDataList;
    }
  }
  
}
