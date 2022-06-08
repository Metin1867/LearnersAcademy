package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pojo.Subject;
import util.DbUtil;

public class SubjectDAO {
	private Subject subject;
	public SubjectDAO() {

	}
	public SubjectDAO(Subject s) {
		subject = s;
	}
	// insert
	public int insert() {
		return insert(subject);
	}
	static public int insert(Subject s) {
		try {
			Connection conn = DbUtil.getConn();
			String sq1="insert into subject values(?,?,?)";
			PreparedStatement ps=conn.prepareStatement(sq1);

			ps.setInt(1, s.getSbjid());
			ps.setString(2, s.getTopic());
			int teaidExpert = s.getTeaid_expert();
			if (teaidExpert == -1)
				ps.setNull(3, java.sql.Types.INTEGER);
			else
				ps.setInt(3, teaidExpert);

			return ps.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return -1;
	}

	// update
	public int update() {
		return update(subject);
	}
	static public int update(Subject s) {
		try {
			Connection conn = DbUtil.getConn();
			String sq1="update subject set "
						+ "topic=?"
						+ ",teaid_expert=?"
						+ " where sbjid=?";
			PreparedStatement ps=conn.prepareStatement(sq1);
			ps.setInt(3, s.getSbjid());
			ps.setString(1, s.getTopic());
			int teaidExpert = s.getTeaid_expert();
			if (teaidExpert == -1)
				ps.setNull(2, java.sql.Types.INTEGER);
			else
				ps.setInt(2, teaidExpert);

			return ps.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return -1;
	}

	// delete
	public int delete() {
		return delete(subject);
	}
	static public int delete(Subject s) {
		try {
			Connection conn = DbUtil.getConn();
			String sq1="delete from subject where sbjid=?";
			PreparedStatement ps=conn.prepareStatement(sq1);
			ps.setInt(1, s.getSbjid());
			return ps.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return -1;
	}

	// retrieve
	// one
	public static Subject getSubject(int sbjid) {
		Subject s=null;
		Connection con;
		try {
			con = DbUtil.getConn();
			String sq1="select * from subject where sbjid=?";
			PreparedStatement ps=con.prepareStatement(sq1);
			ps.setInt(1, sbjid);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				s=getSubject(rs);
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
	public static List<Subject> getSubjects() {
		ArrayList<Subject> list=new ArrayList<>();
		Connection con;
		try {
			con = DbUtil.getConn();
			String sq1="select * from subject";
			PreparedStatement ps=con.prepareStatement(sq1);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				Subject s=getSubject(rs);
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
	private static Subject getSubject(ResultSet rs) throws SQLException {
		Subject s=new Subject();
		s.setSbjid(rs.getInt(1));
		s.setTopic(rs.getString(2));
		int teaidClass = rs.getInt(3);
		if (rs.wasNull())
			s.setTeaid_expert(-1);
		else
			s.setTeaid_expert(teaidClass);
		return s;
	}
	public boolean exists() {
		if (getSubject(this.subject.getSbjid())==null)
			return false;
		return true;
	}

}
