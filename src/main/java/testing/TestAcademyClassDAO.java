package testing;

import java.sql.Date;
import java.sql.SQLException;

import dao.AcademyClassDAO;
import dao.ClassSubjectDAO;
import dao.SubjectDAO;
import pojo.AcademyClass;
import pojo.ClassSubject;
import pojo.Subject;
import util.DbUtil;

public class TestAcademyClassDAO {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		AcademyClass academyClass = new AcademyClass( -11
										, "SommerClass2022"
										, Date.valueOf("2022-08-21")
										, Date.valueOf("2025-07-12"));
		AcademyClassDAO academyClassDAO = new AcademyClassDAO(academyClass);
		if (academyClassDAO.delete()>0)
			System.out.println("AcademyClass row is deleted");
		else
			System.out.println("Row cannot be deleted, check the source!");

		if (academyClassDAO.insert()>0) {
			System.out.println("AcademyClass row is created");
			Subject s = new Subject();
			s.setSbjid(-3000);
			s.setTopic("Mathematik");
			s.setTeaid_expert(-1);
			if (!new SubjectDAO(s).exists())
				new SubjectDAO().insert(s);
			ClassSubject cs = new ClassSubject(academyClassDAO.getClsid(), s.getSbjid());
			new ClassSubjectDAO().insert(cs);
			academyClassDAO.addClassSubject(cs);
		}
		else
			System.out.println("Row cannot be created, check the source!");
		printTables();
		academyClass.setLabel("Wirtschaft-"+academyClass.getLabel());
		if (academyClassDAO.update()>0)
			System.out.println("AcademyClass row is updated");
		else
			System.out.println("Row cannot be updated, check the source!");
	
		academyClass = new AcademyClass( -22
				, "WinterClass2017"
				, Date.valueOf("2017-01-31")
				, Date.valueOf("2021-01-12"));
		if (academyClassDAO.delete(academyClass)>0)
			System.out.println("AcademyClass row is deleted");
		else
			System.out.println("Row cannot be deleted, check the source!");

		if (academyClassDAO.insert(academyClass)>0)
			System.out.println("AcademyClass row is created");
		else
			System.out.println("Row cannot be created, check the source!");

		DbUtil.printResultSet(DbUtil.getResultSet("select * from class where clsid = -22"));
		academyClass.setLabel("Recht-"+academyClass.getLabel());
		if (academyClassDAO.update(academyClass)>0)
			System.out.println("AcademyClass row is updated");
		else
			System.out.println("Row cannot be updated, check the source!");

		System.out.println("Print all entries:");
		for (AcademyClass stu : academyClassDAO.getAcademyClasss())
			System.out.println(stu);

		System.out.println("Print one specified entry:");
		System.out.println(academyClassDAO.getAcademyClass(-22));
		
		AcademyClassDAO.delete(academyClass);
		academyClass.setClsid(-11);
		AcademyClassDAO.delete(academyClass);
		printTables();
	}

	private static void printTables() throws ClassNotFoundException, SQLException {
		String sql="select * from class"; // where clsid = -11";
		System.out.println("--- " + sql);
		DbUtil.printResultSet(DbUtil.getResultSet(sql));
		sql="select * from classsubject";
		System.out.println("--- " + sql);
		DbUtil.printResultSet(DbUtil.getResultSet(sql));
		sql="select * from subject";
		System.out.println("--- " + sql);
		DbUtil.printResultSet(DbUtil.getResultSet(sql));
		
	}

}
