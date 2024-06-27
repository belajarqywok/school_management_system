package com.SchoolManagementSystem.Dtos;


/**
 *  Subject DTO
 */
public class SubjectScheduleDto {
  public SubjectScheduleDto() { }

  /**
   *  Schedule ID
   */
  private String scheduleId;

  // Schedule ID Setter
  public void setScheduleId(String id) {
      this.scheduleId = id;
  }

  // Schedule ID Getter
  public String getScheduleId() {
      return this.scheduleId;
  }


  /**
   *  Class ID
   */
  private String classId;

  // Class ID Setter
  public void setClassId(String id) {
      this.classId = id;
  }

  // Class ID Getter
  public String getClassId() {
      return this.classId;
  }


  /**
   *  Class Name
   */
  private String className;

  // Class Name Setter
  public void setClassName(String name) {
      this.className = name;
  }

  // Class Name Getter
  public String getClassName() {
      return this.className;
  }


  /**
   *  Subject ID
   */
  private String subjectId;

  // Subject ID Setter
  public void setSubjectId(String id) {
      this.subjectId = id;
  }

  // Subject ID Getter
  public String getSubjectId() {
      return this.subjectId;
  }


  /**
   *  Subject Name
   */
  private String subjectName;

  // Subject Name Setter
  public void setSubjectName(String name) {
      this.subjectName = name;
  }

  // Subject Name Getter
  public String getSubjectName() {
      return this.subjectName;
  }


  /**
   *  Employee ID
   */
  private String employeeId;

  // Employee ID Setter
  public void setEmployeeId(String id) {
      this.employeeId = id;
  }

  // Employee ID Getter
  public String getEmployeeId() {
      return this.employeeId;
  }


  /**
   *  Employee Name
   */
  private String employeeName;

  // Employee Name Setter
  public void setEmployeeName(String id) {
      this.employeeName = id;
  }

  // Employee Name Getter
  public String getEmployeeName() {
      return this.employeeName;
  }


  /**
   *  Schedule Day of Week
   */
  private String scheduleDay;

  // Schedule Day of Week Setter
  public void setScheduleDay(String day) {
      this.scheduleDay = day;
  }

  // Schedule Day of Week Getter
  public String getScheduleDay() {
      return this.scheduleDay;
  }


  /**
   *  Schedule Time Start
   */
  private String scheduleTimeStart;

  // Schedule Time Start Setter
  public void setScheduleTimeStart(String time) {
      this.scheduleTimeStart = time;
  }

  // Schedule Time Start Getter
  public String getScheduleTimeStart() {
      return this.scheduleTimeStart;
  }


  /**
   *  Schedule Time End
   */
  private String scheduleTimeEnd;

  // Schedule Time End Setter
  public void setScheduleTimeEnd(String time) {
      this.scheduleTimeEnd = time;
  }

  // Schedule Time End Getter
  public String getScheduleTimeEnd() {
      return this.scheduleTimeEnd;
  }
}
