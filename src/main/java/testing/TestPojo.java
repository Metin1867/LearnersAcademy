package testing;

import java.sql.Date;

import pojo.*;

public class TestPojo {
	public static void main(String[] args) {
		/*for(Date each : getValidTestResults())
		System.out.println(each);
		*/

		Student student1 = new Student();
		student1.setStuid(1001);
		student1.setFirstname("Neva");
		student1.setLastname("Acikalin");
		// Date format yyyy-[m]m-[d]d
		Date dob = Date.valueOf("2002-12-24");
		student1.setDob(dob);
		student1.setEmail("neva@hm.com");
		student1.setPhone("079-555-2345");
		System.out.println(student1);
		
		Teacher teacher1 = new Teacher();
		teacher1.setTeaid(8001);
		teacher1.setFirstname("Metin");
		teacher1.setLastname("Acikalin");
		// Date format yyyy-[m]m-[d]d
		dob = Date.valueOf("1967-8-1");
		// teacher1.setDob(dob);
		teacher1.setEmail("metin@dozent.com");
		teacher1.setPhone("076-111-8989");
		System.out.println(teacher1);
		
		AcademyClass aclass1 = new AcademyClass();
		aclass1.setClsid(5001);
		aclass1.setLabel("WI-So2021-07");
		aclass1.setStart(Date.valueOf("2021-8-21"));
		aclass1.setEnd(Date.valueOf("2024-7-12"));
		System.out.println(aclass1);
		student1.setClsid_class(aclass1.getClsid());
		System.out.println(student1);
		
		Subject subject1 = new Subject();
		subject1.setSbjid(7001);
		subject1.setTopic("Math");
		subject1.setTeaid_expert(teacher1.getTeaid());
		System.out.println(subject1);
		Subject subject2 = new Subject();
		subject2.setSbjid(7002);
		subject2.setTopic("Deutsch");
		System.out.println(subject2);
		Subject subject3 = new Subject();
		subject3.setSbjid(7003);
		subject3.setTopic("English");
		System.out.println(subject3);
		
		ClassSubject classSubject1 = new ClassSubject(aclass1.getClsid(), subject1.getSbjid());
		classSubject1.setTeaid_teacher(teacher1.getTeaid());
		System.out.println(classSubject1);
		ClassSubject classSubject2 = new ClassSubject(aclass1.getClsid(), subject2.getSbjid());
		System.out.println(classSubject2);
		ClassSubject classSubject3 = new ClassSubject(aclass1.getClsid(), subject3.getSbjid());
		System.out.println(classSubject3);
	}

	public static Date[] getValidTestResults() {
	  return new Date[] {
	    Date.valueOf("1970-01-01"), Date.valueOf("1990-10-14"), Date.valueOf("2013-08-12"),
	    Date.valueOf("2040-05-12"), Date.valueOf("2040-05-12"), Date.valueOf("1970-01-01")
	  };
	}
}
