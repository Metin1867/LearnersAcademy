package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StudentDAO;
import pojo.Student;

/**
 * Servlet implementation class StudentDeleteServlet
 */
public class StudentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int stuid;
	String msg;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentDeleteServlet() {
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
			System.out.println("Parameter stuid: "+stuid);
			System.out.println("Parameter msg: "+msg);
			System.out.println("Delete confirmed");
			Student stu = new Student();
			stu.setStuid(stuid);
			new StudentDAO().delete(stu);

			RequestDispatcher rd = request.getRequestDispatcher("StudentsServlet");
			rd.forward(request, response);
		} else {
			stuid = Integer.valueOf(request.getParameter("stuid"));
			msg = request.getParameter("msg");
			System.out.println("Parameter stuid: "+stuid);
			System.out.println("Parameter msg: "+msg);

			pw.append("<h1>Delete Confirmation</h1>").println();
			pw.append("<form action='StudentDeleteServlet?confirmed=yes' method='post'>").println();
			pw.append("<input type='submit' value='Yes'><br>").println();
			pw.append("</form>").println();   
			pw.append("<form action='StudentsServlet'>").println();
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
