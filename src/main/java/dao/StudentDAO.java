package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pojo.Student;
import util.DbUtil;

public class StudentDAO {
	private Student student;
	public StudentDAO() {

	}
	public StudentDAO(Student s) {
		student = s;
	}
	// insert
	public int insert() {
		return insert(student);
	}
	static public int insert(Student s) {
		try {
			Connection conn = DbUtil.getConn();
			String sq1="insert into student values(?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps=conn.prepareStatement(sq1);

			ps.setInt(1, s.getStuid());
			ps.setString(2, s.getFirstname());
			ps.setString(3, s.getLastname());
			ps.setDate(4, s.getDob());
			ps.setString(5, s.getEmail());
			ps.setString(6, s.getPhone());
			ps.setTimestamp(7, s.getCreated());
			ps.setTimestamp(8, s.getModified());
			int clsid_class = s.getClsid_class();
			if (clsid_class == -1)
				ps.setNull(9, java.sql.Types.INTEGER);
			else
				ps.setInt(9, clsid_class);

			return ps.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return -1;
	}

	// update
	public int update() {
		return update(student);
	}
	static public int update(Student s) {
		try {
			Connection conn = DbUtil.getConn();
			String sq1="update student set "
						+ "firstname=?"
						+ ",lastname=?"
						+ ",dob=?"
						+ ",email=?"
						+ ",phone=?"
						+ ",created=?"
						+ ",modified=?"
						+ " where stuid=?";
			PreparedStatement ps=conn.prepareStatement(sq1);
			ps.setInt(8, s.getStuid());
			ps.setString(1, s.getFirstname());
			ps.setString(2, s.getLastname());
			ps.setDate(3, s.getDob());
			ps.setString(4, s.getEmail());
			ps.setString(5, s.getPhone());
			ps.setTimestamp(6, s.getCreated());
			ps.setTimestamp(7, s.getModified());
			return ps.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return -1;
	}

	// delete
	public int delete() {
		return delete(student);
	}
	static public int delete(Student s) {
		try {
			Connection conn = DbUtil.getConn();
			String sq1="delete from student where stuid=?";
			PreparedStatement ps=conn.prepareStatement(sq1);
			ps.setInt(1, s.getStuid());
			return ps.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return -1;
	}

	// retrieve
	// one
	public Student getStudent(int stuid) {
		Student s=null;
		Connection con;
		try {
			con = DbUtil.getConn();
			String sq1="select * from student where stuid=?";
			PreparedStatement ps=con.prepareStatement(sq1);
			ps.setInt(1, stuid);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				s=getStudent(rs);
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

	// all
	public List<Student> getStudents() {
		ArrayList<Student> list=new ArrayList<>();
		Connection con;
		try {
			con = DbUtil.getConn();
			String sq1="select * from student";
			PreparedStatement ps=con.prepareStatement(sq1);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				Student s=getStudent(rs);
				list.add(s);
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
	private Student getStudent(ResultSet rs) throws SQLException {
		Student s=new Student();
		s.setStuid(rs.getInt(1));
		s.setFirstname(rs.getString(2));
		s.setLastname(rs.getString(3));
		s.setDob(rs.getDate(4));
		s.setEmail(rs.getString(5));
		s.setPhone(rs.getString(6));
		s.setCreated(rs.getTimestamp(7));
		s.setModified(rs.getTimestamp(8));
		int clsid_class = rs.getInt(9);
		if (rs.wasNull())
			s.setClsid_class(-1);
		else
			s.setClsid_class(clsid_class);
		return s;
	}

}
