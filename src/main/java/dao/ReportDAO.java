package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pojo.Student;
import util.DbUtil;

public class ReportDAO {
	private String sql;
	ResultSet rs;
	public boolean valid = false;
	private List<String> fieldList;
	private List<Integer> fieldDataTypeList;
	
	public ReportDAO(String sql) {
		this.sql = sql;
		try {
			rs = DbUtil.getResultSet(sql);
			valid = true;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*	public static void printResultSet(ResultSet rs) throws SQLException {
		if (header) {
			for (int i=1; i<=rs.getMetaData().getColumnCount(); i++) {
				System.out.print(rs.getMetaData().getColumnLabel(i));
				System.out.print(delimiter);
			}
			System.out.println();
		}
		while(rs.next()) {
			for (int i=1; i<=rs.getMetaData().getColumnCount(); i++) {
				System.out.print(rs.getString(i));
				System.out.print(delimiter);
			}
			System.out.println();
		}
		
	}

	public static ResultSet getResultSet(String sql) throws ClassNotFoundException, SQLException {
		Connection conn = getConn();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		return rs;
	}
*/

	public List<String> getFieldList() {
		if (fieldList != null) {
			;
		} else {
			fieldList=new ArrayList<>();
			try {
				for (int i=1; i<=rs.getMetaData().getColumnCount(); i++) {
					fieldList.add(rs.getMetaData().getColumnLabel(i));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return fieldList;		
	}

	public List<Integer> getFieldDataTypeList() {
		if (fieldDataTypeList != null) {
			;
		} else {
			fieldDataTypeList = new ArrayList<>();
			try {
				for (int i=1; i<=rs.getMetaData().getColumnCount(); i++) {
					fieldDataTypeList.add(rs.getMetaData().getColumnType(i));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return fieldDataTypeList;		
	}

	public ResultSet getResultSet() {
		if (rs != null) {
			;
		} else {
			Connection con;
			try {
				con = DbUtil.getConn();
				PreparedStatement ps=con.prepareStatement(sql);
				rs=ps.executeQuery();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return rs;		
	}

	public int getColumnCount() {
		try {
			return rs.getMetaData().getColumnCount();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return -1;
	}

}
