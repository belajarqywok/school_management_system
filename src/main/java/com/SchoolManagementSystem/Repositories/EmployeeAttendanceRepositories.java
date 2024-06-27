package com.SchoolManagementSystem.Repositories;

import java.util.List;
import java.util.ArrayList;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import com.SchoolManagementSystem.Dtos.EmployeeAttendanceDto;
import com.SchoolManagementSystem.Configurations.DatabaseConfigurations;



/**
 *  Employee Attendance Repositories
 */
public class EmployeeAttendanceRepositories extends DatabaseConfigurations {
  // Table Name
  private final String TABLENAME = "employee_attendances";

  /**
   * Get Employee Attendance Repository
   *
   * @return List<EmployeeAttendanceDto>
   *
   */
  public List<EmployeeAttendanceDto> getEmployeeAttendancesRepository() {
    List<EmployeeAttendanceDto> employeeAttendancesDataList = new ArrayList<>();
    Connection connection = getConnection();
    String queryString = String.format(
      "SELECT                           " +
        "e.employee_name,               " +
        "e.employee_nip                 " +
      "FROM %s ea                       " +
        "JOIN employees e ON ea.employee_id = e.employee_id " +
      "WHERE ea.employee_attendance_status  = 'Hadir'"
      ,this.TABLENAME
    );

    try {
      PreparedStatement statement = connection
        .prepareStatement(queryString);

      ResultSet resultSet = statement.executeQuery();

      while (resultSet.next()) {
        EmployeeAttendanceDto employeeAttendance = new EmployeeAttendanceDto();

        employeeAttendance.setEmployeeName(resultSet
          .getString("employee_name"));
        employeeAttendance.setEmployeeNip(resultSet
          .getString("employee_nip"));

        employeeAttendancesDataList.add(employeeAttendance);
      }

      return employeeAttendancesDataList;

    } catch (SQLException exception) {
      exception.printStackTrace();
      return employeeAttendancesDataList;
    }
  }



  /**
   * Search Employee Attendance Repository
   *
   * @param key String
   * @return List<EmployeeAttendanceDto>
   *
   */
  public List<EmployeeAttendanceDto> searchEmployeeAttendancesRepository(String key) {
    List<EmployeeAttendanceDto> employeeAttendancesDataList = new ArrayList<>();
    Connection connection = getConnection();
    String queryString = String.format(
      "SELECT                           " +
        "e.employee_name,               " +
        "e.employee_nip                 " +
      "FROM %s ea                       " +
        "JOIN employees e ON ea.employee_id = e.employee_id " +
      "WHERE (                                        " +
        "e.employee_name    LIKE '%%%s%%' OR          " +
        "e.employee_nip     LIKE '%%%s%%' AND         " +
        "ea.employee_attendance_status = 'Hadir'      " +
      ")", this.TABLENAME,
      key, key, key
    );

    try {
      PreparedStatement statement = connection.prepareStatement(queryString);

      ResultSet resultSet = statement.executeQuery();

      while (resultSet.next()) {
        EmployeeAttendanceDto employeeAttendance = new EmployeeAttendanceDto();

        employeeAttendance.setEmployeeName(resultSet
          .getString("employee_name"));
        employeeAttendance.setEmployeeNip(resultSet
          .getString("employee_nip"));

        employeeAttendancesDataList.add(employeeAttendance);
      }

      return employeeAttendancesDataList;

    } catch (SQLException exception) {
      exception.printStackTrace();
      return employeeAttendancesDataList;
    }
  }
  
}
