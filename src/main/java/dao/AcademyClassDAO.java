package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pojo.AcademyClass;
import pojo.ClassSubject;
import pojo.Subject;
import util.DbUtil;

public class AcademyClassDAO {
	private AcademyClass academyClass;
	public AcademyClassDAO() {

	}
	public AcademyClassDAO(AcademyClass s) {
		academyClass = s;
	}
	// insert
	public int insert() {
		return insert(academyClass);
	}
	public int getClsid() {
		return academyClass.getClsid();
	}
	static public int insert(AcademyClass c) {
		int returnValue = -1;
		try {
			Connection conn = DbUtil.getConn();
			String sq1="insert into class values(?,?,?,?)";
			PreparedStatement ps=conn.prepareStatement(sq1);

			ps.setInt(1, c.getClsid());
			ps.setString(2, c.getLabel());
			ps.setDate(3, c.getStart());
			ps.setDate(4, c.getEnd());

			returnValue = ps.executeUpdate();
			
			for (ClassSubject cs : c.getClassSubjects()) {
				new ClassSubjectDAO(cs).insert();
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return returnValue;
	}

	// update
	public int update() {
		return update(academyClass);
	}
	static public int update(AcademyClass c) {
		int returnValue = -1;
		try {
			Connection conn = DbUtil.getConn();
			String sq1="update class set "
						+ "label=?"
						+ ",start=?"
						+ ",end=?"
						+ " where clsid=?";
			PreparedStatement ps=conn.prepareStatement(sq1);
			ps.setInt(4, c.getClsid());
			ps.setString(1, c.getLabel());
			ps.setDate(2, c.getStart());
			ps.setDate(3, c.getEnd());
			returnValue = ps.executeUpdate();
			
			List<ClassSubject> existing = new ArrayList<>();
			for (ClassSubject cs : c.getClassSubjects()) {
				ClassSubject searched = ClassSubjectDAO.getClassSubject(cs.getClsid(), cs.getSbjid());
				if (searched==null) {
					System.out.println("TRACE: INSERT a new ClassSubject");
					new ClassSubjectDAO(cs).insert();
					existing.add(cs);
				} else {
					System.out.println("TRACE: UPDATE an existing ClassSubject");
					new ClassSubjectDAO(cs).update();
					existing.add(cs);
				}
			}
			List<ClassSubject> onTableClassSubject = ClassSubjectDAO.getClassSubject(c.getClsid());
			for (ClassSubject cs : onTableClassSubject) {
				if(!existsClassSubject(cs, existing)) {
					System.out.println("TRACE: DELETE an existing ClassSubject");
					new ClassSubjectDAO(cs).delete();
				}
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return returnValue;
	}

	private static boolean existsClassSubject(ClassSubject cs, List<ClassSubject> existing) {
		for (ClassSubject existingOne : existing) {
			if (existingOne.getClsid() == cs.getClsid() && existingOne.getSbjid() == cs.getSbjid())
				return true;
		}
		return false;
	}
	
	// delete
	public int delete() {
		return delete(academyClass);
	}
	static public int delete(AcademyClass c) {
		try {
			List<ClassSubject> onTableClassSubject = ClassSubjectDAO.getClassSubject(c.getClsid());
			for (ClassSubject cs : onTableClassSubject) {
				new ClassSubjectDAO(cs).delete();
			}
			
			Connection conn = DbUtil.getConn();
			String sq1="delete from class where clsid=?";
			PreparedStatement ps=conn.prepareStatement(sq1);
			ps.setInt(1, c.getClsid());
			return ps.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return -1;
	}

	// retrieve
	// one
	public AcademyClass getAcademyClass(int clsid) {
		AcademyClass c=null;
		Connection con;
		try {
			con = DbUtil.getConn();
			String sq1="select * from class where clsid=?";
			PreparedStatement ps=con.prepareStatement(sq1);
			ps.setInt(1, clsid);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				c=getAcademyClass(rs);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;		
	}

	// all
	public List<AcademyClass> getAcademyClasss() {
		ArrayList<AcademyClass> list=new ArrayList<>();
		Connection con;
		try {
			con = DbUtil.getConn();
			String sq1="select * from class";
			PreparedStatement ps=con.prepareStatement(sq1);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				AcademyClass c=getAcademyClass(rs);
				list.add(c);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;		
	}
	private AcademyClass getAcademyClass(ResultSet rs) throws SQLException {
		AcademyClass c=new AcademyClass();
		c.setClsid(rs.getInt(1));
		c.setLabel(rs.getString(2));
		c.setStart(rs.getDate(3));
		c.setEnd(rs.getDate(4));
		return c;
	}
	public void addClassSubject(ClassSubject classSubject) {
		if (classSubject.getClsid() == academyClass.getClsid()) {
			academyClass.addClassSubject(classSubject);
		} else {
			throw new RuntimeException("Wrong Academy Class!");
		}
	}

}
