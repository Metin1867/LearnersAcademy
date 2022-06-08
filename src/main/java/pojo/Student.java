package pojo;

import java.sql.Date;
import java.sql.Timestamp;

/* -----------------------------------------------------
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
); */
public class Student {
	int stuid;
	String firstname;
	String lastname;
	Date dob;
	String email;
	String phone;
	Timestamp created;
	Timestamp modified;
	int clsid_class = -1;

	public Student() {
		created = new Timestamp(System.currentTimeMillis());

	}

	public Student(int stuid, String firstname, String lastname, Date dob, String email, String phone,
			Timestamp created, Timestamp modified, int clsid_class) {
		this.stuid = stuid;
		this.firstname = firstname;
		this.lastname = lastname;
		this.dob = dob;
		this.email = email;
		this.phone = phone;
		this.created = created;
		this.modified = modified;
		this.clsid_class = -1;
	}

	public int getStuid() {
		return stuid;
	}

	public void setStuid(int stuid) {
		// modified = new Timestamp(System.currentTimeMillis());
		this.stuid = stuid;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		modified = new Timestamp(System.currentTimeMillis());
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		modified = new Timestamp(System.currentTimeMillis());
		this.lastname = lastname;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		modified = new Timestamp(System.currentTimeMillis());
		this.dob = dob;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		modified = new Timestamp(System.currentTimeMillis());
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		modified = new Timestamp(System.currentTimeMillis());
		this.phone = phone;
	}

	public Timestamp getCreated() {
		return created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public Timestamp getModified() {
		return modified;
	}

	public void setModified(Timestamp modified) {
		this.modified = modified;
	}

	public int getClsid_class() {
		return clsid_class;
	}

	public void setClsid_class(int clsid_class) {
		modified = new Timestamp(System.currentTimeMillis());
		this.clsid_class = clsid_class;
	}

	@Override
	public String toString() {
		return "Student [stuid=" + stuid + ", firstname=" + firstname + ", lastname=" + lastname + ", dob=" + dob
				+ ", email=" + email + ", phone=" + phone + ", created=" + created + ", modified=" + modified
				+ ", clsid_class=" + clsid_class + "]";
	}

}
