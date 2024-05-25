-- Seeder Data for Visual Programming Assignment - Anitaaaa

USE school_management_system_db;


/*
    Seeder: Class
*/
INSERT INTO class (
    class_name,
    class_capacity
) VALUES
('Kelas 1A', 30),
('Kelas 1B', 25),
('Kelas 1C', 20);

/*
    Seeder: Students
*/
INSERT INTO students (
    class_id,
    student_nisn,
    student_name,
    student_birth,
    student_gender,
    student_phone,
    student_address
) VALUES 
(
    (SELECT class_id FROM class WHERE class_name = 'Kelas 1A'),
    'NISN001', 'Student One', '2005-01-01', 'Male', '081234567890', 'Address 1'
),
(
    (SELECT class_id FROM class WHERE class_name = 'Kelas 1B'),
    'NISN002', 'Student Two', '2006-02-02', 'Female', '081234567891', 'Address 2'
),
(
    (SELECT class_id FROM class WHERE class_name = 'Kelas 1C'),
    'NISN003', 'Student Three', '2007-03-03', 'Male', '081234567892', 'Address 3'
);

/*
    Seeder: Employees
*/
INSERT INTO employees (
    employee_nip,
    employee_name,
    employee_birth,
    employee_gender,
    employee_type,
    employee_phone,
    employee_address
) VALUES 
(
    'NIP001', 'Employee One', '1980-01-01', 'Male',
    'Tata Usaha', '081234567890', 'Address 1'
),
(
    'NIP002', 'Employee Two', '1981-02-02', 'Female',
    'Guru', '081234567891', 'Address 2'
),
(
    'NIP003', 'Employee Three', '1982-03-03', 'Male',
    'Admin', '081234567892', 'Address 3'
);

/*
    Seeder: Student Attendance
*/
INSERT INTO student_attendances (
    student_id,
    student_attendance_date,
    student_attendance_status
) VALUES 
(
    (SELECT student_id FROM students WHERE student_nisn = 'NISN001'),
    '2024-05-01', 'Hadir'
),
(
    (SELECT student_id FROM students WHERE student_nisn = 'NISN002'),
    '2024-05-01', 'Hadir'
),
(
    (SELECT student_id FROM students WHERE student_nisn = 'NISN003'),
    '2024-05-01', 'Tidak Hadir'
);

/*
    Seeder: Employee Attendance
*/
INSERT INTO employee_attendances (
    employee_id,
    employee_attendance_date,
    employee_attendance_status
) VALUES 
(
    (SELECT employee_id FROM employees WHERE employee_nip = 'NIP001'),
    '2024-05-01', 'Hadir'
),
(
    (SELECT employee_id FROM employees WHERE employee_nip = 'NIP002'),
    '2024-05-01', 'Hadir'
),
(
    (SELECT employee_id FROM employees WHERE employee_nip = 'NIP003'),
    '2024-05-01', 'Tidak Hadir'
);

/*
    Seeder: Subjects
*/
INSERT INTO subjects (
    subject_name,
    subject_desc
) VALUES 
('Math', 'Mathematics subject'),
('Science', 'Science subject'),
('History', 'History subject');

/*
    Seeder: Subject Schedules
*/
INSERT INTO subject_schedules (
    class_id,
    subject_id,
    employee_id,
    day_of_week,
    time_start,
    time_end
) VALUES 
(
    (SELECT class_id FROM class WHERE class_name = 'Kelas 1A'), 
    (SELECT subject_id FROM subjects WHERE subject_name = 'Math'), 
    (SELECT employee_id FROM employees WHERE employee_nip = 'NIP002'), 
    'Monday', '08:00:00', '09:30:00'
),

(
    (SELECT class_id FROM class WHERE class_name = 'Kelas 1B'), 
    (SELECT subject_id FROM subjects WHERE subject_name = 'Science'), 
    (SELECT employee_id FROM employees WHERE employee_nip = 'NIP002'), 
    'Tuesday', '10:00:00', '11:30:00'
),

(
    (SELECT class_id FROM class WHERE class_name = 'Kelas 1C'), 
    (SELECT subject_id FROM subjects WHERE subject_name = 'History'), 
    (SELECT employee_id FROM employees WHERE employee_nip = 'NIP002'), 
    'Wednesday', '13:00:00', '14:30:00'
);




