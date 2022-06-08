package testing;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;

import dao.TeacherDAO;
import pojo.Teacher;
import util.DbUtil;

public class TestTeacherDAO {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Teacher teacher = new Teacher( -1
										, "TeaVorname", "TeaNachname", Date.valueOf("1921-02-07")
										, "tea@gmail.com", "041-5813-3243"
										, new Timestamp(System.currentTimeMillis()) , null);
		TeacherDAO teacherDAO = new TeacherDAO(teacher);
		if (teacherDAO.delete()>0)
			System.out.println("Teacher row is deleted");
		else
			System.out.println("Row cannot be deleted, check the source!");

		if (teacherDAO.insert()>0)
			System.out.println("Teacher row is created");
		else
			System.out.println("Row cannot be created, check the source!");
	
		DbUtil.printResultSet(DbUtil.getResultSet("select * from teacher where teaid = -1"));
		teacher.setFirstname("Micky");
		teacher.setLastname("Mouse");
		if (teacherDAO.update()>0)
			System.out.println("Teacher row is updated");
		else
			System.out.println("Row cannot be updated, check the source!");
	
		teacher = new Teacher( -2
				, "Tea2Vorname", "Tea2Nachname", Date.valueOf("1711-10-27")
				, "tea@bluewin.com", "072-181-5152"
				, new Timestamp(System.currentTimeMillis()) , null);
		if (teacherDAO.delete(teacher)>0)
			System.out.println("Teacher row is deleted");
		else
			System.out.println("Row cannot be deleted, check the source!");

		if (teacherDAO.insert(teacher)>0)
			System.out.println("Teacher row is created");
		else
			System.out.println("Row cannot be created, check the source!");

		DbUtil.printResultSet(DbUtil.getResultSet("select * from Teacher where teaid = -2"));
		teacher.setFirstname("Daisy");
		teacher.setLastname("Duck");
		if (teacherDAO.update(teacher)>0)
			System.out.println("Teacher row is updated");
		else
			System.out.println("Row cannot be updated, check the source!");

		System.out.println("Print all entries:");
		for (Teacher stu : teacherDAO.getTeachers())
			System.out.println(stu);

		System.out.println("Print one specified entry:");
		System.out.println(teacherDAO.getTeacher(-2));
		
		TeacherDAO.delete(teacher);
		teacher.setTeaid(-1);
		TeacherDAO.delete(teacher);
		DbUtil.printResultSet(DbUtil.getResultSet("select * from Teacher"));
	}

}
