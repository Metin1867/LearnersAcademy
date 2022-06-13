package reports;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ReportDAO;
import util.ServletHTMLUtil;

/**
 * Servlet implementation class AllSubjectsClassesServlet
 */
public class AllSubjectsClassesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AllSubjectsClassesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("AllSubjectsClassesServlet.doGet(...)");
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			pw.append("<h1>All Subjects and Classes Report</h1><hr>").println();
			String note = "The report ist sorted,<br>"
						  +"1st by all classes without any subject assigned,<br>"
						  +"2nd block all subjects with assigned class,<br>"
						  +"3rd last block all subjects without assigned class.<br>"
						  +"<hr><br>";
			pw.append(note).println();
			pw.append("<a href='Login'><img src='data/menu3.png' alt='Menu' width='28' height='28'></a>");
			pw.append(" | <a href='AllSubjectsClassesServlet?refresh'><img src='data/refresh.png' alt='Refresh' width='28' height='28'></a>");
			pw.append("<br>");
			printQuery(pw, "SELECT sbjid 'Subject<br>Identifier', Topic, Start, End, "
					 + "       teaid 'Teacher<br>Identifier', Teacher, "
					 + "       clsid 'Class<br>Identifier', classlabel Class "
						 + "FROM   all_subjects_all_classes ");
	}

	private void printQuery(PrintWriter pw, String sql) {
		ReportDAO report = new ReportDAO(sql);
		ResultSet rs = report.getResultSet();
		pw.append("<table>").println();
		pw.append("<tr>").println();
		for (String col : report.getFieldList()) {
			if (!"".equals(col))
				pw.append("<th>").append(col).append("</th>");
		}
		pw.println();
		pw.append("</tr>").println();
		try {
			while(rs.next()) {
				pw.append("<tr>").println();
				for (int c = 1; c <= report.getColumnCount(); c++) {
					pw.append("<td>").append(ServletHTMLUtil.getValue(rs.getString(c))).append("</td>");
				}
				pw.append("</tr>").println();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pw.append("</table>").println();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
