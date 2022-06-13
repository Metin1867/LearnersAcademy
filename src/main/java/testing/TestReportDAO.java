package testing;

import java.sql.ResultSet;
import java.sql.SQLException;

import dao.ReportDAO;
import util.DbUtil;

public class TestReportDAO {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		ReportDAO report = new ReportDAO("SELECT * FROM class");
		ResultSet rs = report.getResultSet();
		for (String col : report.getFieldList()) {
			System.out.print(col);
			System.out.print("\t");
		}
		System.out.println();
		while(rs.next()) {
			for (int c = 1; c < report.getColumnCount(); c++) {
				System.out.print(rs.getString(c));
				System.out.print("\t");
			}
			System.out.println();
		}
	}

}
