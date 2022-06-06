package pojo;

import java.sql.Date;

/* -----------------------------------------------------
-- Table academy.class
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS class (
		  clsid INT NOT NULL AUTO_INCREMENT,
		  label VARCHAR(45) NOT NULL,
		  start DATE NOT NULL,
		  end DATE NOT NULL,
		  PRIMARY KEY (clsid)
); */
public class AcademyClass {
	int clsid;
	String label;
	Date start;
	Date end;

	public AcademyClass() {
			
	}

	public int getClsid() {
		return clsid;
	}

	public void setClsid(int clsid) {
		this.clsid = clsid;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	@Override
	public String toString() {
		return "AcademyClass [clsid=" + clsid + ", label=" + label + ", start=" + start + ", end=" + end + "]";
	}

}
