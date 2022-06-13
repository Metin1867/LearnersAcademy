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
import dao.SubjectDAO;
import pojo.Subject;
import util.ServletHTMLUtil;

/**
 * Servlet implementation class ClassReportServlet
 */
public class ClassReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClassReportServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ClassReportServlet.doGet(...)");
		if (request.getParameter("refresh")!=null) {
			doPost(request, response);
		} else {
			PrintWriter pw = response.getWriter();
			pw.append("Served at: ").append(request.getContextPath());
			response.setContentType("text/html");
			showEditForm(pw);
		}
	}

	private void showEditForm(PrintWriter pw) {
		pw.append("<h1>Report A class</h1>");
		pw.append(ServletHTMLUtil.startFormPost("ClassReportServlet")); 
		pw.append(ServletHTMLUtil.getNumberInput("Class Identifier", "clsid", ""));
		pw.append(ServletHTMLUtil.getSubmitInput("Run"));
		pw.append(ServletHTMLUtil.getSubmitInput("Cancel"));
		pw.append(ServletHTMLUtil.endForm()); 

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ClassReportServlet.doPost(...)");
		if (request.getParameter("submit")=="Cancel") {
			RequestDispatcher rd = request.getRequestDispatcher("ClassReportServlet");
			rd.forward(request, response);
		} else {
			response.setContentType("text/html");
			int clsid = ServletHTMLUtil.getIntValue(request.getParameter("clsid"));
			PrintWriter pw = response.getWriter();
			pw.append("<h1>Class Report</h1><hr><br>");
			pw.append("<a href='Login'><img src='data/menu3.png' alt='Menu' width='28' height='28'></a>");
			pw.append(" | <a href='ClassReportServlet?refresh&clsid="+clsid+"'><img src='data/refresh.png' alt='Refresh' width='28' height='28'></a>");
			pw.append("<br>");
			printQuery(pw, "SELECT clsid 'Class<br>Identifier', label 'Klassenlabel', Start, End "
						 + "FROM class "
						 + "WHERE clsid="+clsid);
			pw.append("<br>");
			printQuery(pw, "SELECT sbjid 'Subject<br>Identifier', Topic, Start, End, "
						 + "       teaid 'Teacher<br>Identifier', Teacher "
						 + "FROM   all_subjects_all_classes "
						 + "WHERE clsid="+clsid+" "
						 + "ORDER BY topic");
		}
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

}
