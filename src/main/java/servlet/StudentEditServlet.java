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
import util.ServletHTMLUtil;

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
		System.out.println("StudentEditServlet.doGet(...)");
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
		if (stu == null) {
			stu = new Student();
			System.out.println("Student Maintenance: INSERT Form");
		} else {
			System.out.println("Student Maintenance: UPDATE Form");
		}
		pw.append(ServletHTMLUtil.startFormPost("StudentEditServlet")); 
		pw.append(ServletHTMLUtil.getNumberInput("Identifier", "stuid", stu.getStuid() ));
		pw.append(ServletHTMLUtil.getTextInput("First Name", "firstname", stu.getFirstname() ));
		pw.append(ServletHTMLUtil.getTextInput("Last Name", "lastname", stu.getLastname() ));
		pw.append(ServletHTMLUtil.getTextInput("Birthday", "dob", stu.getDob() ));
		pw.append(ServletHTMLUtil.getTextInput("Email", "email", stu.getEmail() ));
		pw.append(ServletHTMLUtil.getTextInput("Phone", "phone", stu.getPhone()) );
		pw.append(ServletHTMLUtil.getNumberInput("Class Identifier", "class", stu.getClsid_class() ));
		pw.append(ServletHTMLUtil.getSubmitInput("register"));
		pw.append(ServletHTMLUtil.endForm()); 
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("StudentEditServlet.doPost(...)");
		PrintWriter pw = response.getWriter();
		pw.append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
		int stuid = ServletHTMLUtil.getIntValue(request.getParameter("stuid"));
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		Date dob = ServletHTMLUtil.getDate(request.getParameter("dob"));
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		int clsid = ServletHTMLUtil.getIntValue(request.getParameter("class"));
		Student stu = new Student(stuid, firstname, lastname, dob, email, phone, new Timestamp(System.currentTimeMillis()), null, clsid);
		// System.out.println("StudentEditServlet.doPost: " + stu);
		
		StudentDAO dao = new StudentDAO(stu);
		if (isNew) {
			System.out.println("Student DAO INSERT");
			dao.insert();
		} else {
			System.out.println("Student DAO UPDATE");
			dao.update();
		}
		
		System.out.println("Redirect to StudentsServlet");
		RequestDispatcher rd = request.getRequestDispatcher("StudentsServlet");
		rd.forward(request, response);
		
	}

}
