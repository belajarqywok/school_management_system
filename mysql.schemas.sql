/*

    Visual Programming Assignment - Anitaaaa

    -- DB Schemas --

*/


-- Database Configuration
DROP DATABASE IF EXISTS school_management_system_db;
CREATE DATABASE IF NOT EXISTS school_management_system_db;

USE school_management_system_db;


/*
    Table: Admin Auth
*/ 
SET @username = "anita";
SET @password = "tugasjava";

CREATE TABLE admin_auth (
  auth_meta_id   VARCHAR(36)  DEFAULT (UUID()) PRIMARY KEY,
  auth_username  VARCHAR(16)  UNIQUE NOT NULL,
  auth_password  VARCHAR(512) NOT NULL
);

CREATE INDEX idx_auth_username
  ON admin_auth (auth_username);

CREATE INDEX idx_auth_password 
  ON admin_auth (auth_password);

INSERT INTO admin_auth (auth_username, auth_password)
SELECT * FROM (SELECT @username, @password) AS _tmp
WHERE NOT EXISTS (
    SELECT 1 FROM admin_auth
    WHERE auth_username = @username 
    AND auth_password   = @password
);

DESCRIBE admin_auth; SELECT " ";


/*
    Table: Class
*/ 
CREATE TABLE class (
  class_id        VARCHAR(36)  DEFAULT (UUID()) PRIMARY KEY,

  class_name      VARCHAR(128) UNIQUE NOT NULL,
  class_capacity  INT NOT NULL
);

CREATE INDEX idx_class_name
  ON class (class_name);

CREATE INDEX idx_class_capacity
  ON class (class_capacity);

DESCRIBE class; SELECT " ";


/*
    Table: Students
*/
CREATE TABLE students (
  student_id       VARCHAR(36)  DEFAULT (UUID()) PRIMARY KEY,
  class_id         VARCHAR(36)  NOT NULL,

  -- Natural Key
  student_nisn     VARCHAR(64) UNIQUE NOT NULL,

  student_name     VARCHAR(64) NOT NULL,
  student_birth    DATE        NOT NULL,
  student_gender   ENUM('Laki-Laki', 'Perempuan') NOT NULL,

  student_phone    VARCHAR(32),
  student_address  TEXT,

  FOREIGN KEY (class_id)
  REFERENCES class(class_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

CREATE INDEX idx_student_nisn
  ON students (student_nisn);

CREATE INDEX idx_student_name
  ON students (student_name);

CREATE INDEX idx_student_birth
  ON students (student_birth);

CREATE INDEX idx_student_gender
  ON students (student_gender);

CREATE INDEX idx_student_address
  ON students (student_address);

CREATE INDEX idx_student_phone
  ON students (student_phone);

DESCRIBE students; SELECT " ";


/*
    Table: Employees
*/
CREATE TABLE employees (
  employee_id      VARCHAR(36)  DEFAULT (UUID()) PRIMARY KEY,

  -- Natural Key
  employee_nip      VARCHAR(64) UNIQUE NOT NULL,

  employee_name    VARCHAR(64) NOT NULL,
  employee_birth   DATE        NOT NULL,
  employee_gender  ENUM('Male', 'Female') NOT NULL,

  employee_type    ENUM('Guru', 'Tata Usaha', 'Admin', 'Satpam', 'OB') NOT NULL,

  employee_phone   VARCHAR(32),
  employee_address TEXT
);

CREATE INDEX idx_employee_nip
  ON employees (employee_nip);

CREATE INDEX idx_employee_name
  ON employees (employee_name);
  
CREATE INDEX idx_employee_birth
  ON employees (employee_birth);

CREATE INDEX idx_employee_gender
  ON employees (employee_gender);

CREATE INDEX idx_employee_address
  ON employees (employee_address);

CREATE INDEX idx_employee_phone
  ON employees (employee_phone);

DESCRIBE employees; SELECT " ";


/*
    Table: Student Attendance
*/
CREATE TABLE student_attendances (
  student_attendance_id    VARCHAR(36)  DEFAULT (UUID()) PRIMARY KEY,
  student_id               VARCHAR(36)  NOT NULL,

  student_attendance_date   DATE NOT NULL,
  student_attendance_status ENUM('Hadir', 'Tidak Hadir') NOT NULL,

  FOREIGN KEY (student_id) 
  REFERENCES students(student_id) 
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

CREATE INDEX idx_student_attendance_date
  ON student_attendances (student_attendance_date);

CREATE INDEX idx_student_attendance_status
  ON student_attendances (student_attendance_status);

DESCRIBE student_attendances; SELECT " ";


/*
    Table: Employee Attendance
*/
CREATE TABLE employee_attendances (
  employee_attendance_id     VARCHAR(36)  DEFAULT (UUID()) PRIMARY KEY,
  employee_id                VARCHAR(36)  NOT NULL,

  employee_attendance_date   DATE NOT NULL,
  employee_attendance_status ENUM('Hadir', 'Tidak Hadir') NOT NULL,

  FOREIGN KEY (employee_id) 
  REFERENCES employees(employee_id) 
    ON DELETE CASCADE
    ON UPDATE CASCADE
);


CREATE INDEX idx_employee_attendance_date
  ON employee_attendances (employee_attendance_date);

CREATE INDEX idx_employee_attendance_status
  ON employee_attendances (employee_attendance_status);

DESCRIBE employee_attendances; SELECT " ";


/*
    Table: Subject
*/
CREATE TABLE subjects (
  subject_id       VARCHAR(36)  DEFAULT (UUID()) PRIMARY KEY,

  subject_name     VARCHAR(128) UNIQUE NOT NULL,
  subject_desc     TEXT
);

CREATE INDEX idx_subject_name
  ON subjects (subject_name);

CREATE INDEX idx_subject_desc
  ON subjects (subject_desc);

DESCRIBE subjects; SELECT " ";


/*
    Table: Subject Schedules
*/
CREATE TABLE subject_schedules (
  schedule_id      VARCHAR(36)  DEFAULT (UUID()) PRIMARY KEY,
  class_id         VARCHAR(36)  NOT NULL,
  subject_id       VARCHAR(36)  NOT NULL,
  employee_id      VARCHAR(36)  NOT NULL,

  day_of_week      ENUM('Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday') NOT NULL,
  time_start       TIME NOT NULL,
  time_end         TIME NOT NULL,

  FOREIGN KEY (class_id) 
  REFERENCES class(class_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,

  FOREIGN KEY (subject_id)
  REFERENCES subjects(subject_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,

  FOREIGN KEY (employee_id)
  REFERENCES employees(employee_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

CREATE INDEX idx_day_of_week
  ON subject_schedules (day_of_week);

CREATE INDEX idx_time_start
  ON subject_schedules (time_start);

CREATE INDEX idx_time_end
  ON subject_schedules (time_end);

DESCRIBE subject_schedules; SELECT " ";


