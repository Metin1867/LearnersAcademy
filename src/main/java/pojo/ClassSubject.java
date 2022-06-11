package pojo;

import java.sql.Date;

/* -----------------------------------------------------
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
); */
public class ClassSubject {
	int clsid;
	int sbjid=-1;
	Date start;
	Date end;
	int teaid_teacher = -1;

	public ClassSubject() {
			
	}

	public ClassSubject(int clsid, int sbjid) {
		this.clsid = clsid;
		this.sbjid = sbjid;
	}

	public int getClsid() {
		return clsid;
	}

	public void setClsid(int clsid) {
		this.clsid = clsid;
	}

	public int getSbjid() {
		return sbjid;
	}

	public void setSbjid(int sbjid) {
		this.sbjid = sbjid;
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

	public int getTeaid_teacher() {
		return teaid_teacher;
	}

	public void setTeaid_teacher(int teaid_teacher) {
		this.teaid_teacher = teaid_teacher;
	}

	@Override
	public String toString() {
		return "ClassSubject [clsid=" + clsid + ", sbjid=" + sbjid + ", start=" + start + ", end=" + end
				+ ", teaid_teacher=" + teaid_teacher + "]";
	}

}
