package testing;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;

import dao.SubjectDAO;
import dao.TeacherDAO;
import pojo.Subject;
import pojo.Teacher;
import util.DbUtil;

public class TestSubjectDAO {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Subject subject = new Subject();
		subject.setSbjid(-1);
		subject.setTopic("Math");
		subject.setTeaid_expert(-1);
		
		SubjectDAO subjectDAO = new SubjectDAO(subject);
		if (subjectDAO.delete()>0)
			System.out.println("Subject row is deleted");
		else
			System.out.println("Row cannot be deleted, check the source!");

		if (subjectDAO.insert()>0)
			System.out.println("Subject row is created");
		else
			System.out.println("Row cannot be created, check the source!");
	
		DbUtil.printResultSet(DbUtil.getResultSet("select * from subject where sbjid = -1"));
		subject.setTopic("Mathematik");
		Teacher teacher = new Teacher( -1001
				, "TeaVorname", "TeaNachname", Date.valueOf("1921-02-07")
				, "tea@gmail.com", "041-5813-3243"
				, new Timestamp(System.currentTimeMillis()) , null);
		new TeacherDAO(teacher).insert();
		DbUtil.printResultSet(DbUtil.getResultSet("select * from teacher"));		
		subject.setTeaid_expert(teacher.getTeaid());
		if (subjectDAO.update()>0)
			System.out.println("Subject row is updated");
		else
			System.out.println("Row cannot be updated, check the source!");
	
		subject = new Subject();
		subject.setSbjid(-2);
		subject.setTopic("English");
		subject.setTeaid_expert(-1);
		if (subjectDAO.delete(subject)>0)
			System.out.println("Subject row is deleted");
		else
			System.out.println("Row cannot be deleted, check the source!");

		if (subjectDAO.insert(subject)>0)
			System.out.println("Subject row is created");
		else
			System.out.println("Row cannot be created, check the source!");

		DbUtil.printResultSet(DbUtil.getResultSet("select * from class where clsid = -2"));
		subject.setTopic("Englisch");
		if (subjectDAO.update(subject)>0)
			System.out.println("Subject row is updated");
		else
			System.out.println("Row cannot be updated, check the source!");

		System.out.println("Print all entries:");
		for (Subject stu : subjectDAO.getSubjects())
			System.out.println(stu);

		System.out.println("Print one specified entry:");
		System.out.println(subjectDAO.getSubject(-2));
		
		SubjectDAO.delete(subject);
		subject.setSbjid(-1);
		SubjectDAO.delete(subject);
		DbUtil.printResultSet(DbUtil.getResultSet("select * from class"));
		new TeacherDAO(teacher).delete();
	}

}
