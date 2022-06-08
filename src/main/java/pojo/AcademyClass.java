package pojo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

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
	private int clsid;
	private String label;
	private Date start;
	private Date end;
	private List<ClassSubject> classSubjects = new ArrayList<>();
	
	public AcademyClass() {
			
	}

	public AcademyClass(int clsid, String label, Date start, Date end) {
		this.clsid = clsid;
		this.label = label;
		this.start = start;
		this.end = end;
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

	public void addClassSubject(ClassSubject classSubject) {
		classSubjects.add(classSubject);
		
	}

	public List<ClassSubject> getClassSubjects() {
		return classSubjects;
		
	}

}
