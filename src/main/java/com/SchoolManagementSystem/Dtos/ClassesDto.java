package com.SchoolManagementSystem.Dtos;


/**
 *  Classes DTO
 */
public class ClassesDto {
  public ClassesDto() {}

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
   *  Class Capacity
   */
  private String classCapacity;

  // Class Capacity Setter
  public void setClassCapacity(String capacity) {
    this.classCapacity = capacity;
  }

  // Class Capacity Getter
  public String getClassCapacity() {
    return this.classCapacity;
  }
}