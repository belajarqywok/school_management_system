package com.SchoolManagementSystem.Dtos;

public class StudentAttendanceDto {
  public StudentAttendanceDto() { }

  /**
   *  Student Attendance Id 
   */
  private String studentAttendanceId;

  // Student Attendance Id Setter
  public void setStudentAttendanceId(String id) {
    this.studentAttendanceId = id;
  }

  // Student Attendance Id Getter
  public String getStudentAttendanceId() {
    return this.studentAttendanceId;
  }


  /**
   *  Student Id 
   */
  private String studentId;

  // Student Id Setter
  public void setStudentId(String id) {
    this.studentId = id;
  }

  // Student Id Getter
  public String getStudentId() {
    return this.studentId;
  }


  /**
   *  Student Name
   */
  private String studentName;

  // Student Name Setter
  public void setStudentName(String name) {
    this.studentName = name;
  }

  // Student Name Getter
  public String getStudentName() {
    return this.studentName;
  }
  
  
  /**
   *  Student NISN
   */
  private String studentNisn;

  // Student NISN Setter
  public void setStudentNisn(String nisn) {
    this.studentNisn = nisn;
  }

  // Student NISN Getter
  public String getStudentNisn() {
    return this.studentNisn;
  }


  /**
   *  Student Attendance Date
   */
  private String studentAttendanceDate;

  // Student Attendance Date Setter
  public void setStudentAttendanceDate(String date) {
    this.studentAttendanceDate = date;
  }

  // Student Attendance Date Getter
  public String getStudentAttendanceDate() {
    return this.studentAttendanceDate;
  }


  /**
   *  Student Attendance Status
   */
  private String studentAttendanceStatus;

  // Student Attendance Status Setter
  public void setStudentAttendanceStatus(String status) {
    this.studentAttendanceStatus = status;
  }

  // Student Attendance Status Getter
  public String getStudentAttendanceStatus() {
    return this.studentAttendanceStatus;
  }
}
