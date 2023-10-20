Drop Table if exists major;
Drop table if exists minor;
Drop Table if exists degrees;
Drop Table if exists register;
Drop Table if exists courses;
Drop Table if exists students;
Drop Table if exists departments;

USE test;


CREATE TABLE departments (
	code int ,
	name varchar(50) UNIQUE,
	phone varchar(10),
	college varchar(20),
    PRIMARY KEY (code)
);

CREATE TABLE degrees (
	name varchar(50),
    level varchar(5),
    department_code int NOT NULL,
	FOREIGN KEY (department_code) REFERENCES departments(code) ON UPDATE CASCADE ON DELETE CASCADE,
	PRIMARY KEY (name, level)
);

CREATE TABLE courses (
	number int ,
	name varchar(50),
	descrption varchar(50),
	credithours int,
	level varchar(20),
    department_code int NOT NULL,
	FOREIGN KEY (department_code) REFERENCES departments(code) ON UPDATE CASCADE ON DELETE CASCADE,
	PRIMARY KEY (number)
);

CREATE TABLE students (
	snum int UNIQUE,
    ssn int,
    name varchar(10),
    gender varchar(1),
    dob datetime,
    c_addr varchar(20),
    c_phone varchar(10),
    p_addr varchar(20),
    p_phone varchar(10),
    PRIMARY KEY (ssn)
);

CREATE TABLE major (
    snum int,
    name varchar(50),
    level varchar(5),
    FOREIGN KEY (snum) REFERENCES students(snum) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (name, level) REFERENCES degrees(name, level) ON UPDATE CASCADE ON DELETE CASCADE,
    PRIMARY KEY(snum, name, level)
);

CREATE TABLE minor (
	snum int,
    name varchar(50),
    level varchar(5),
    FOREIGN KEY (snum) REFERENCES students(snum) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (name, level) REFERENCES degrees(name, level) ON UPDATE CASCADE ON DELETE CASCADE,
    PRIMARY KEY(snum, name, level)
);

CREATE TABLE register (
	snum int,
    course_number int,
    regtime varchar(20),
    grade int,
    FOREIGN KEY (snum) REFERENCES students(snum) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (course_number) REFERENCES courses(number) ON UPDATE CASCADE ON DELETE CASCADE,
    PRIMARY KEY(snum, course_number)
);