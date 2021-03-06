package util;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * DbUtil to manage the database connection
 */
public class DbUtil {
	// options for printResultSet
	static public boolean header=true;
	static public String delimiter=",";
	
	final static String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
	final static String DB_URL = "jdbc:mysql://localhost:3306/academy";
	final static String USER_NAME = "root";
	final static String PASSWORD = "root";
	
	public static Connection getConn() throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER_CLASS);
		Connection con = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
		return con;
	}

	public static void printResultSet(ResultSet rs) throws SQLException {
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

    public static Date jutilToSqlData(java.util.Date jutilDate) {
        Date sqlDate = new Date(jutilDate.getTime());
        return sqlDate;
      }
    
    public static java.util.Date sqlToJUtilData(Date sqlDate) {
        java.util.Date jutilDate = new java.util.Date(sqlDate.getTime());
        return jutilDate;
      }

}
