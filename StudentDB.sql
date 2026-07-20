CREATE DATABASE StudentDB;
USE StudentDB;

CREATE TABLE Account(
    username VARCHAR(50) PRIMARY KEY,
    password VARCHAR(50)
);

INSERT INTO Account
VALUES
('admin','123'),
('teacher','456');

-- Bảng ngành học
CREATE TABLE Major(
    major_id INT AUTO_INCREMENT PRIMARY KEY,
    major_name VARCHAR(100)
);

INSERT INTO Major(major_name)
VALUES
('Information Technology'),
('Business'),
('English');

-- Bảng sinh viên
CREATE TABLE Student(
    student_id INT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(100),
    gender VARCHAR(10),
    age INT,
    major_id INT,
    FOREIGN KEY (major_id) REFERENCES Major(major_id)
);

-- Dữ liệu sinh viên
INSERT INTO Student(full_name, gender, age, major_id)
VALUES
('Nguyen Van An', 'Male', 20, 1),
('Tran Thi Bich', 'Female', 19, 2),
('Le Minh Quan', 'Male', 21, 1),
('Pham Ngoc Han', 'Female', 20, 3),
('Vo Thanh Dat', 'Male', 22, 2),
('Do Thi Mai', 'Female', 18, 3),
('Huynh Gia Bao', 'Male', 19, 1),
('Nguyen Thu Trang', 'Female', 21, 2),
('Phan Hoang Nam', 'Male', 20, 1),
('Bui Khanh Linh', 'Female', 22, 3);