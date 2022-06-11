package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pojo.ClassSubject;
import util.DbUtil;

public class ClassSubjectDAO {
	private ClassSubject classSubject;
	public ClassSubjectDAO() {

	}
	public ClassSubjectDAO(ClassSubject cs) {
		classSubject = cs;
	}
	// insert
	public int insert() {
		return insert(classSubject);
	}
	static public int insert(ClassSubject cs) {
		try {
			Connection conn = DbUtil.getConn();
			String sq1="insert into classsubject values(?,?,?,?,?)";
			PreparedStatement ps=conn.prepareStatement(sq1);

			ps.setInt(1, cs.getClsid());
			ps.setInt(2, cs.getSbjid());
			ps.setDate(3, cs.getStart());
			ps.setDate(4, cs.getEnd());
			int teaidTeacher = cs.getTeaid_teacher();
			if (teaidTeacher == -1)
				ps.setNull(5, java.sql.Types.INTEGER);
			else
				ps.setInt(5, teaidTeacher);

			System.out.println(ps);
			
			return ps.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return -1;
	}

	// update
	public int update() {
		return update(classSubject);
	}
	static public int update(ClassSubject cs) {
		try {
			Connection conn = DbUtil.getConn();
			String sq1="update classsubject set "
						+ "start=?"
						+ ",end=?"
						+ ",teaid_teacher=?"
						+ " where clsid=?"
						+ " and sbjid=?";
			PreparedStatement ps=conn.prepareStatement(sq1);
			ps.setInt(4, cs.getClsid());
			ps.setInt(5, cs.getSbjid());
			ps.setDate(1, cs.getStart());
			ps.setDate(2, cs.getEnd());
			int teaidTeacher = cs.getTeaid_teacher();
			if (teaidTeacher == -1)
				ps.setNull(3, java.sql.Types.INTEGER);
			else
				ps.setInt(3, teaidTeacher);

			return ps.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return -1;
	}

	// delete
	public int delete() {
		return delete(classSubject);
	}
	static public int delete(ClassSubject s) {
		try {
			Connection conn = DbUtil.getConn();
			String sq1="delete from classsubject where clsid=? and sbjid=?";
			PreparedStatement ps=conn.prepareStatement(sq1);
			ps.setInt(1, s.getClsid());
			ps.setInt(2, s.getSbjid());
			return ps.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return -1;
	}

	// retrieve
	// one
	public static ClassSubject getClassSubject(int clsid, int sbjid) {
		ClassSubject s=null;
		Connection con;
		try {
			con = DbUtil.getConn();
			String sq1="select * from classsubject where clsid=? and sbjid=?";
			PreparedStatement ps=con.prepareStatement(sq1);
			ps.setInt(1, clsid);
			ps.setInt(2, sbjid);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				s=getClassSubject(rs);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;		
	}

	// all subjects for one class
	public static List<ClassSubject> getClassSubject(int clsid) {
		ArrayList<ClassSubject> list=new ArrayList<>();
		Connection con;
		try {
			con = DbUtil.getConn();
			String sq1="select * from classSubject where clsid=?";
			PreparedStatement ps=con.prepareStatement(sq1);
			ps.setInt(1, clsid);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				ClassSubject cs=getClassSubject(rs);
				list.add(cs);
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

	// all
	public static List<ClassSubject> getClassSubjects() {
		ArrayList<ClassSubject> list=new ArrayList<>();
		Connection con;
		try {
			con = DbUtil.getConn();
			String sq1="select * from classsubject";
			PreparedStatement ps=con.prepareStatement(sq1);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				ClassSubject cs=getClassSubject(rs);
				list.add(cs);
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
	private static ClassSubject getClassSubject(ResultSet rs) throws SQLException {
		ClassSubject cs=new ClassSubject();
		cs.setClsid(rs.getInt(1));
		cs.setSbjid(rs.getInt(2));
		cs.setStart(rs.getDate(3));
		cs.setEnd(rs.getDate(4));
		int teaidClass = rs.getInt(5);
		if (rs.wasNull())
			cs.setTeaid_teacher(-1);
		else
			cs.setTeaid_teacher(teaidClass);
		return cs;
	}

}
