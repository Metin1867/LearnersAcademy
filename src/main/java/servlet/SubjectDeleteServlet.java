package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SubjectDAO;
import pojo.Subject;

/**
 * Servlet implementation class SubjectDeleteServlet
 */
public class SubjectDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int sbjid;
	String msg;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubjectDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		pw.append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
		if ("yes".equals(request.getParameter("confirmed"))) {
			System.out.println("Delete confirmed");
			Subject sbj = new Subject();
			sbj.setSbjid(sbjid);
			new SubjectDAO(sbj).delete();

			RequestDispatcher rd = request.getRequestDispatcher("SubjectsServlet");
			rd.forward(request, response);
		} else {
			sbjid = Integer.valueOf(request.getParameter("sbjid"));
			msg = request.getParameter("msg");

			pw.append("<h1>Delete Confirmation for the Subject</h1>").println();
			pw.append("<form action='SubjectDeleteServlet?confirmed=yes' method='post'>").println();
			pw.append("<input type='submit' value='Yes'><br>").println();
			pw.append("</form>").println();   
			pw.append("<form action='SubjectsServlet'>").println();
			pw.append("<input type='submit' value='No'><br>").println();
			pw.append("</form>").println();   
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
