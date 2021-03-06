package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ClassSubjectDAO;
import pojo.ClassSubject;

/**
 * Servlet implementation class ClassSubjectDeleteServlet
 */
public class ClassSubjectDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int clsid;
	int sbjid;
	String msg;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClassSubjectDeleteServlet() {
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
			ClassSubject csj = new ClassSubject();
			csj.setClsid(clsid);
			csj.setSbjid(sbjid);
			new ClassSubjectDAO(csj).delete();

			RequestDispatcher rd = request.getRequestDispatcher("AcademyClassEditServlet?form&clsid="+clsid);
			rd.forward(request, response);
		} else {
			clsid = Integer.valueOf(request.getParameter("clsid"));
			sbjid = Integer.valueOf(request.getParameter("sbjid"));
			msg = request.getParameter("msg");

			pw.append("<h1>Delete Confirmation for the Class Subject</h1>").println();
			pw.append("<form action='ClassSubjectDeleteServlet?confirmed=yes' method='post'>").println();
			pw.append("<input type='submit' value='Yes'><br>").println();
			pw.append("</form>").println();   
			pw.append("<form action='AcademyClassEditServlet?clsid="+clsid+"'>").println();
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
