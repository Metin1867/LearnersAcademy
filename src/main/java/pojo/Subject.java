package pojo;

/* -----------------------------------------------------
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
); */
public class Subject {
	int sbjid = -1;
	String topic;
	int teaid_expert = -1;

	public Subject() {
	
	}

	public int getSbjid() {
		return sbjid;
	}

	public void setSbjid(int sbjid) {
		this.sbjid = sbjid;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public int getTeaid_expert() {
		return teaid_expert;
	}

	public void setTeaid_expert(int teaid_expert) {
		this.teaid_expert = teaid_expert;
	}

	@Override
	public String toString() {
		return "Subject [sbjid=" + sbjid + ", topic=" + topic + ", teaid_expert=" + teaid_expert + "]";
	}

}
