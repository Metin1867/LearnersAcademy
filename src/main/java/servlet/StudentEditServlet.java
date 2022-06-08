package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Timestamp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StudentDAO;
import pojo.Student;

/**
 * Servlet implementation class StudentEditServlet
 */
public class StudentEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private boolean isNew=true;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentEditServlet() {
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
		String stuidStr = request.getParameter("stuid");
		int stuid = -1;
		if (stuidStr!=null) {
			stuid = Integer.valueOf(stuidStr);
			isNew = false;
		}
		Student stu = StudentDAO.getStudent(stuid);
		showEditForm(pw, stu);
	}

	private void showEditForm(PrintWriter pw, Student stu) {
		pw.append("<h1>Student Maintenance</h1>");
		if (stu == null)
			stu = new Student();
		pw.append("<form action='StudentEditServlet' method='post'>");
		pw.append("Identifier <input type='number' name='stuid' value='"+stu.getStuid()+"'><br/>");
		pw.append("First Name <input type='text' name='firstname' value='"+stu.getFirstname()+"'><br/>");  
		pw.append("Last Name <input type='text' name='lastname'  value='"+stu.getLastname()+"'><br/>");  
		pw.append("Birthday <input type='date' name='dob'  value='"+stu.getDob()+"'><br/>");  
		pw.append("Email <input type='email' name='email' value='"+stu.getEmail()+"'><br/>");  
		pw.append("Phone <input type='text' name='phone' value='"+stu.getPhone()+"'><br/>");  
		if (stu.getClsid_class() == -1)
			pw.append("Student Class<input type='number' name='class' value='-1'><br/>");  
		else
			pw.append("Student Class<input type='number' name='class' value='"+stu.getClsid_class()+"'><br/>");  
		pw.append("<input type='submit' name='register'><br/>");
		pw.append("</form>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		pw.append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
		int stuid = Integer.valueOf(request.getParameter("stuid"));
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		Date dob = Date.valueOf(request.getParameter("dob"));
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		int clsid;
		if (request.getParameter("class") == "")
			clsid = -1;
		else
			clsid = Integer.valueOf(request.getParameter("class"));
		Student stu = new Student(stuid, firstname, lastname, dob, email, phone, new Timestamp(System.currentTimeMillis()), null, clsid);
		System.out.println(stu);
		
		StudentDAO stuDAO = new StudentDAO(stu);
		if (isNew)
			stuDAO.insert();
		else
			stuDAO.update();
		RequestDispatcher rd = request.getRequestDispatcher("StudentsServlet");
		rd.forward(request, response);
		
	}

}
