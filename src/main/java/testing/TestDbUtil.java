package testing;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import util.DbUtil;

public class TestDbUtil {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		DbUtil db = new DbUtil();
		Connection conn = db.getConn();
		Statement st = conn.createStatement();
		String sql = "select user(), now(), DATABASE(), VERSION()";
		ResultSet rs = st.executeQuery(sql);
		while(rs.next()) {
			System.out.println("--- row "+rs.getRow()+" ------------------------------");
			for (int i=1; i<=rs.getMetaData().getColumnCount(); i++)
				System.out.println(rs.getMetaData().getColumnLabel(i)
									+ ": " +rs.getString(i));
		}
		st.close();
		conn.close();
	}

}
