/*	Database Analyse/Design
The administrator can:
● Set up a master list of all the subjects for all the classes
● Set up a master list of all the teachers
● Set up a master list of all the classes
● Assign classes for subjects from the master list
● Assign teachers to a class for a subject (A teacher can be assigned to different classes for different subjects)
● Get a master list of students (Each student must be assigned to a single class)
     
There will be an option to view a Class Report which will show all the information about the class, such as the list of students, subjects, and teachers

*/

CREATE DATABASE IF NOT EXISTS academy DEFAULT CHARACTER SET utf8 ;
USE academy;

/* tables
		- subjects	n : m classes				-> class_subject
		- teachers	1 : m classes/subjects		
        - classes 	n : m subjects				-> class_subject
        - students	1 : 1 classes
*/

-- MySQL Workbench Forward Engineering

-- -----------------------------------------------------
-- Table academy.class
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS class (
  clsid INT NOT NULL AUTO_INCREMENT,
  label VARCHAR(45) NOT NULL,
  start DATE NOT NULL,
  end DATE NOT NULL,
  PRIMARY KEY (clsid)
);

-- -----------------------------------------------------
-- Table student
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS student (
  stuid INT NOT NULL,
  firstname VARCHAR(45) NOT NULL,
  lastname VARCHAR(45) NOT NULL,
  dob DATE NOT NULL,
  email VARCHAR(45) NOT NULL,
  phone VARCHAR(45) NULL,
  created TIMESTAMP NOT NULL DEFAULT now(),
  modified TIMESTAMP NULL,
  clsid_class INT NOT NULL,
  PRIMARY KEY (stuid, clsid_class),
  INDEX fk_student_class_idx (clsid_class ASC) VISIBLE,
  CONSTRAINT fk_student_class
    FOREIGN KEY (clsid_class)
    REFERENCES class (clsid)
);


-- -----------------------------------------------------
-- Table teacher
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS teacher (
  teaid INT NOT NULL,
  firstname VARCHAR(45) NOT NULL,
  lastname VARCHAR(45) NOT NULL,
  dob DATE NULL,
  email VARCHAR(45) NOT NULL,
  phone VARCHAR(45) NOT NULL,
  created TIMESTAMP NOT NULL DEFAULT now(),
  modified TIMESTAMP NULL,
  PRIMARY KEY (teaid)
);

-- -----------------------------------------------------
-- Table subject
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS subject (
  sbjid INT NOT NULL AUTO_INCREMENT,
  topic VARCHAR(45) NOT NULL,
  teaid_expert INT NOT NULL,
  PRIMARY KEY (sbjid, teaid_expert),
  INDEX fk_subject_teacher1_idx (teaid_expert ASC) VISIBLE,
  CONSTRAINT fk_subject_teacher1
    FOREIGN KEY (teaid_expert)
    REFERENCES teacher (teaid)
);

-- -----------------------------------------------------
-- Table classsubject
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS classsubject (
  clsid INT NOT NULL,
  sbjid INT NOT NULL,
  start DATE NULL,
  end DATE NULL,
  teaid_teacher INT NULL,
  PRIMARY KEY (clsid, sbjid),
  INDEX fk_classsubject_subject1_idx (sbjid ASC) VISIBLE,
  INDEX fk_classsubject_teacher1_idx (teaid_teacher ASC) VISIBLE,
  CONSTRAINT fk_classsubject_class1
    FOREIGN KEY (clsid)
    REFERENCES class (clsid),
  CONSTRAINT fk_classsubject_subject1
    FOREIGN KEY (sbjid)
    REFERENCES subject (sbjid),
  CONSTRAINT fk_classsubject_teacher1
    FOREIGN KEY (teaid_teacher)
    REFERENCES teacher (teaid)
);