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

import com.SchoolManagementSystem.Dtos.EmployeeDto;
import com.SchoolManagementSystem.Configurations.DatabaseConfigurations;



/**
 *  Employee Repositories
 */
public class EmployeeRepositories extends DatabaseConfigurations {
    // Table Name
    private final String TABLENAME = "employees";
    
    /**
     * Get Employees Repository
     *
     * @return List<EmployeeDto>
     *
     */
    public List<EmployeeDto> getEmployeesRepository() {
        List<EmployeeDto> employeesDataList = new ArrayList<>();
        Connection connection = getConnection();
        String queryString = String.format(
          "SELECT * FROM %s", this.TABLENAME
        );
    
        try {
          PreparedStatement statement = connection.prepareStatement(queryString);
    
          ResultSet resultSet = statement.executeQuery();
    
          while (resultSet.next()) {
            EmployeeDto employee = new EmployeeDto();

            employee.setEmployeeId(resultSet
                .getString("employee_id"));
            employee.setEmployeeNip(resultSet
                .getString("employee_nip"));
            employee.setEmployeeName(resultSet
                .getString("employee_name"));
            employee.setEmployeeBirthDate(resultSet
                .getString("employee_birth"));
            employee.setEmployeeGender(resultSet
                .getString("employee_gender"));
            employee.setEmployeeType(resultSet
                .getString("employee_type"));
            employee.setEmployeePhone(resultSet
                .getString("employee_phone"));
            employee.setEmployeeAddress(resultSet
                .getString("employee_address"));
    
            employeesDataList.add(employee);
          }
    
          return employeesDataList;
    
        } catch (SQLException exception) {
          exception.printStackTrace();
          return employeesDataList;
        }
    }



    /**
   * Create Employee Repository
   *
   * @param dto EmployeeDto
   * @return Map<String, Object>
   *
   */
  public Map<String, Object> createEmployeeRepository(EmployeeDto dto) {
    Map<String, Object> response = new HashMap<>();

    Connection connection = getConnection();
    String queryString = String.format(
      "INSERT INTO %s   (  "  +
        "employee_nip,     "  +
        "employee_name,    "  +
        "employee_birth,   "  +
        "employee_gender,  "  +
        "employee_type,    "  +
        "employee_phone,   "  +
        "employee_address  "  +
      ")"                     +
      "VALUES (?, ?, ?, ?, ?, ?, ?)", this.TABLENAME
    );

    try {
      // Transaction Mode (ACID Principles)
      connection.setAutoCommit(false);

      PreparedStatement statement = connection
        .prepareStatement(queryString);

      statement.setString(1, dto.getEmployeeNip());
      statement.setString(2, dto.getEmployeeName());
      statement.setString(3, dto.getEmployeeBirthDate());
      statement.setString(4, dto.getEmployeeGender());
      statement.setString(5, dto.getEmployeeType());
      statement.setString(6, dto.getEmployeePhone());
      statement.setString(7, dto.getEmployeeAddress());

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
   * Update Employee Repository
   *
   * @param dto EmployeeDto
   * @return Map<String, Object>
   *
   */
  public Map<String, Object> updateEmployeeRepository(EmployeeDto dto) {
    Map<String, Object> response = new HashMap<>();

    Connection connection = getConnection();
    String queryString = String.format(
      "UPDATE %s SET            "  +
        "employee_nip      = ?, "  +
        "employee_name     = ?, "  +
        "employee_birth    = ?, "  +
        "employee_gender   = ?, "  +
        "employee_type     = ?, "  +
        "employee_phone    = ?, "  +
        "employee_address  = ?  "  +
      "WHERE employee_id   = ?  ", 
      this.TABLENAME
    );

    try {
      // Transaction Mode (ACID Principles)
      connection.setAutoCommit(false);

      PreparedStatement statement = connection.prepareStatement(queryString);

      statement.setString(1, dto.getEmployeeNip());
      statement.setString(2, dto.getEmployeeName());
      statement.setString(3, dto.getEmployeeBirthDate());
      statement.setString(4, dto.getEmployeeGender());
      statement.setString(5, dto.getEmployeeType());
      statement.setString(6, dto.getEmployeePhone());
      statement.setString(7, dto.getEmployeeAddress());
      statement.setString(8, dto.getEmployeeId());

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
   * Delete Employee Repository
   *
   * @param employeeId String
   * @return Boolean
   *
   */
  public boolean deleteEmployeeRepository(String employeeId) {
    Connection connection = getConnection();
    String queryString = String.format(
      "DELETE FROM %s WHERE employee_id = ?", this.TABLENAME
    );

    try {
      // Transaction Mode (ACID Principles)
      connection.setAutoCommit(false);

      PreparedStatement statement = connection.prepareStatement(queryString);

      statement.setString(1, employeeId);

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
   * Search Employee Repository
   *
   * @param key String
   * @return List<EmployeeDto>
   *
   */
  public List<EmployeeDto> searchEmployeesRepository(String key) {
    List<EmployeeDto> employeesDataList = new ArrayList<>();
    Connection connection = getConnection();
    String queryString = String.format(
      "SELECT * FROM %s WHERE (              " +
        "employee_nip       LIKE '%%%s%%' OR " +
        "employee_name      LIKE '%%%s%%' OR " +
        "employee_birth     LIKE '%%%s%%' OR " +
        "employee_gender    LIKE '%%%s%%' OR " +
        "employee_type      LIKE '%%%s%%' OR " +
        "employee_phone     LIKE '%%%s%%' OR " +
        "employee_address   LIKE '%%%s%%'    " +
      ")", this.TABLENAME,
      key, key, key, 
      key, key, key, key
    );

    try {
      PreparedStatement statement = connection.prepareStatement(queryString);

      ResultSet resultSet = statement.executeQuery();

      while (resultSet.next()) {
        EmployeeDto employee = new EmployeeDto();

        employee.setEmployeeId(resultSet
            .getString("employee_id"));
        employee.setEmployeeNip(resultSet
            .getString("employee_nip"));
        employee.setEmployeeName(resultSet
            .getString("employee_name"));
        employee.setEmployeeBirthDate(resultSet
            .getString("employee_birth"));
        employee.setEmployeeGender(resultSet
            .getString("employee_gender"));
        employee.setEmployeeType(resultSet
            .getString("employee_type"));
        employee.setEmployeePhone(resultSet
            .getString("employee_phone"));
        employee.setEmployeeAddress(resultSet
            .getString("employee_address"));
    
        employeesDataList.add(employee);
      }

      return employeesDataList;

    } catch (SQLException exception) {
      exception.printStackTrace();
      return employeesDataList;
    }
  }
  
}
