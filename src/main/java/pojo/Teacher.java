package pojo;

import java.sql.Date;
import java.sql.Timestamp;

/* -----------------------------------------------------
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
); */
public class Teacher {
	int teaid;
	String firstname;
	String lastname;
	Date dob;
	String email;
	String phone;
	Timestamp created;
	Timestamp modified;

	public Teacher() {
		created = new Timestamp(System.currentTimeMillis());;
			
	}

	public Teacher(int teaid, String firstname, String lastname, Date dob, String email, String phone,
			Timestamp created, Timestamp modified) {
		this.teaid = teaid;
		this.firstname = firstname;
		this.lastname = lastname;
		this.dob = dob;
		this.email = email;
		this.phone = phone;
		this.created = created;
		this.modified = modified;
	}

	public int getTeaid() {
		return teaid;
	}

	public void setTeaid(int teaid) {
		// modified = new Timestamp(System.currentTimeMillis());;
		this.teaid = teaid;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		modified = new Timestamp(System.currentTimeMillis());;
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		modified = new Timestamp(System.currentTimeMillis());;
		this.lastname = lastname;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		modified = new Timestamp(System.currentTimeMillis());;
		this.dob = dob;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		modified = new Timestamp(System.currentTimeMillis());;
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		modified = new Timestamp(System.currentTimeMillis());;
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

	@Override
	public String toString() {
		return "Teacher [teaid=" + teaid + ", firstname=" + firstname + ", lastname=" + lastname + ", dob=" + dob
				+ ", email=" + email + ", phone=" + phone + ", created=" + created + ", modified=" + modified + "]";
	}

}
