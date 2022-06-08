package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pojo.Teacher;
import util.DbUtil;

public class TeacherDAO {
	private Teacher Teacher;
	public TeacherDAO() {

	}
	public TeacherDAO(Teacher t) {
		Teacher = t;
	}
	// insert
	public int insert() {
		return insert(Teacher);
	}
	static public int insert(Teacher t) {
		try {
			Connection conn = DbUtil.getConn();
			String sq1="insert into teacher values(?,?,?,?,?,?,?,?)";
			PreparedStatement ps=conn.prepareStatement(sq1);

			ps.setInt(1, t.getTeaid());
			ps.setString(2, t.getFirstname());
			ps.setString(3, t.getLastname());
			ps.setDate(4, t.getDob());
			ps.setString(5, t.getEmail());
			ps.setString(6, t.getPhone());
			ps.setTimestamp(7, t.getCreated());
			ps.setTimestamp(8, t.getModified());

			return ps.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return -1;
	}

	// update
	public int update() {
		return update(Teacher);
	}
	static public int update(Teacher t) {
		try {
			Connection conn = DbUtil.getConn();
			String sq1="update teacher set "
						+ "firstname=?"
						+ ",lastname=?"
						+ ",dob=?"
						+ ",email=?"
						+ ",phone=?"
						+ ",created=?"
						+ ",modified=?"
						+ " where teaid=?";
			PreparedStatement ps=conn.prepareStatement(sq1);
			ps.setInt(8, t.getTeaid());
			ps.setString(1, t.getFirstname());
			ps.setString(2, t.getLastname());
			ps.setDate(3, t.getDob());
			ps.setString(4, t.getEmail());
			ps.setString(5, t.getPhone());
			ps.setTimestamp(6, t.getCreated());
			ps.setTimestamp(7, t.getModified());
			return ps.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return -1;
	}

	// delete
	public int delete() {
		return delete(Teacher);
	}
	static public int delete(Teacher t) {
		try {
			Connection conn = DbUtil.getConn();
			String sq1="delete from teacher where teaid=?";
			PreparedStatement ps=conn.prepareStatement(sq1);
			ps.setInt(1, t.getTeaid());
			return ps.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return -1;
	}

	// retrieve
	// one
	public Teacher getTeacher(int teaid) {
		Teacher t=null;
		Connection con;
		try {
			con = DbUtil.getConn();
			String sq1="select * from teacher where teaid=?";
			PreparedStatement ps=con.prepareStatement(sq1);
			ps.setInt(1, teaid);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				t= getTeacher(rs);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return t;		
	}

	// all
	public List<Teacher> getTeachers() {
		ArrayList<Teacher> list=new ArrayList<>();
		Connection con;
		try {
			con = DbUtil.getConn();
			String sq1="select * from teacher";
			PreparedStatement ps=con.prepareStatement(sq1);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				Teacher t= getTeacher(rs);
				list.add(t);
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
	
	private pojo.Teacher getTeacher(ResultSet rs) throws SQLException {
		Teacher t=new Teacher();
		t.setTeaid(rs.getInt(1));
		t.setFirstname(rs.getString(2));
		t.setLastname(rs.getString(3));
		t.setDob(rs.getDate(4));
		t.setEmail(rs.getString(5));
		t.setPhone(rs.getString(6));
		t.setCreated(rs.getTimestamp(7));
		t.setModified(rs.getTimestamp(8));
		return t;
	}

}
