package com.SchoolManagementSystem.Dtos;


/**
 *  Employee Attendance Dto
 */
public class EmployeeAttendanceDto {
  public EmployeeAttendanceDto() { }

  /**
   *  Employee Attendance Id 
   */
  private String employeeAttendanceId;

  // Employee Attendance Id Setter
  public void setEmployeeAttendanceId(String id) {
    this.employeeAttendanceId = id;
  }

  // Employee Attendance Id Getter
  public String getEmployeeAttendanceId() {
    return this.employeeAttendanceId;
  }


  /**
   *  Employee Id 
   */
  private String employeeId;

  // Employee Id Setter
  public void setEmployeeId(String id) {
    this.employeeId = id;
  }

  // Employee Id Getter
  public String getEmployeeId() {
    return this.employeeId;
  }


  /**
   *  Employee Name
   */
  private String employeeName;

  // Employee Name Setter
  public void setEmployeeName(String name) {
    this.employeeName = name;
  }

  // Employee Name Getter
  public String getEmployeeName() {
    return this.employeeName;
  }
  
  
  /**
   *  Employee NIP
   */
  private String employeeNip;

  // Employee NIP Setter
  public void setEmployeeNip(String nip) {
    this.employeeNip = nip;
  }

  // Employee NIP Getter
  public String getEmployeeNip() {
    return this.employeeNip;
  }


  /**
   *  Employee Attendance Date
   */
  private String employeeAttendanceDate;

  // Employee Attendance Date Setter
  public void setEmployeeAttendanceDate(String date) {
    this.employeeAttendanceDate = date;
  }

  // Employee Attendance Date Getter
  public String getEmployeeAttendanceDate() {
    return this.employeeAttendanceDate;
  }


  /**
   *  Employee Attendance Status
   */
  private String employeeAttendanceStatus;

  // Employee Attendance Status Setter
  public void setEmployeeAttendanceStatus(String status) {
    this.employeeAttendanceStatus = status;
  }

  // Employee Attendance Status Getter
  public String getEmployeeAttendanceStatus() {
    return this.employeeAttendanceStatus;
  }
}
