package testing;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;

import dao.StudentDAO;
import pojo.Student;
import util.DbUtil;

public class TestStudentDAO {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Student student = new Student( -1
										, "StuVorname", "StuNachname", Date.valueOf("1021-8-21")
										, "stu@hotmail.com", "044-781-2434"
										, new Timestamp(System.currentTimeMillis()) , null
										, -1);
		StudentDAO studentDAO = new StudentDAO(student);
		if (studentDAO.delete()>0)
			System.out.println("Student row is deleted");
		else
			System.out.println("Row cannot be deleted, check the source!");

		if (studentDAO.insert()>0)
			System.out.println("Student row is created");
		else
			System.out.println("Row cannot be created, check the source!");
	
		DbUtil.printResultSet(DbUtil.getResultSet("select * from student where stuid = -1"));
		student.setFirstname("Micky");
		student.setLastname("Mouse");
		if (studentDAO.update()>0)
			System.out.println("Student row is updated");
		else
			System.out.println("Row cannot be updated, check the source!");
	
		student = new Student( -2
				, "Stu2Vorname", "Stu2Nachname", Date.valueOf("1188-11-7")
				, "stu@hotmail.com", "042-281-1122"
				, new Timestamp(System.currentTimeMillis()) , null
				, -1);
		if (studentDAO.delete(student)>0)
			System.out.println("Student row is deleted");
		else
			System.out.println("Row cannot be deleted, check the source!");

		if (studentDAO.insert(student)>0)
			System.out.println("Student row is created");
		else
			System.out.println("Row cannot be created, check the source!");

		DbUtil.printResultSet(DbUtil.getResultSet("select * from student where stuid = -2"));
		student.setFirstname("Daisy");
		student.setLastname("Duck");
		if (studentDAO.update(student)>0)
			System.out.println("Student row is updated");
		else
			System.out.println("Row cannot be updated, check the source!");

		System.out.println("Print all entries:");
		for (Student stu : studentDAO.getStudents())
			System.out.println(stu);

		System.out.println("Print one specified entry:");
		System.out.println(studentDAO.getStudent(-2));
		
		studentDAO.delete(student);
		student.setStuid(-1);
		studentDAO.delete(student);
		DbUtil.printResultSet(DbUtil.getResultSet("select * from student"));
	}

}
