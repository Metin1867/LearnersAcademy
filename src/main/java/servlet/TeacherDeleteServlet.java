package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TeacherDAO;
import pojo.Teacher;

/**
 * Servlet implementation class StudentDeleteServlet
 */
public class TeacherDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int teaid;
	String msg;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeacherDeleteServlet() {
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
		System.out.println("Parameter confirmed"+request.getParameter("confirmed"));
		if ("yes".equals(request.getParameter("confirmed"))) {
			System.out.println("Parameter teaid: "+teaid);
			System.out.println("Parameter msg: "+msg);
			System.out.println("Delete confirmed");
			Teacher tea = new Teacher();
			tea.setTeaid(teaid);
			new TeacherDAO(tea).delete();

			RequestDispatcher rd = request.getRequestDispatcher("TeachersServlet");
			rd.forward(request, response);
		} else {
			teaid = Integer.valueOf(request.getParameter("teaid"));
			msg = request.getParameter("msg");
			System.out.println("Parameter teaid: "+teaid);
			System.out.println("Parameter msg: "+msg);

			pw.append("<h1>Delete Confirmation</h1>").println();
			pw.append("<form action='TeacherDeleteServlet?confirmed=yes' method='post'>").println();
			pw.append("<input type='submit' value='Yes'><br>").println();
			pw.append("</form>").println();   
			pw.append("<form action='TeachersServlet'>").println();
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
