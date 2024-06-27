package com.SchoolManagementSystem.Dtos;


/**
 *  Subject DTO
 */
public class SubjectDto {
  public SubjectDto() { }

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
   *  Subject Description
   */
  private String subjectDesc;

  // Subject Description Setter
  public void setSubjectDesc(String description) {
      this.subjectDesc = description;
  }

  // Subject Description Getter
  public String getSubjectDesc() {
      return this.subjectDesc;
  }
}
