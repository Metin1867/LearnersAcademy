package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StudentDAO;
import pojo.Student;
import util.ServletHTMLUtil;

/**
 * Servlet implementation class StudentServlet
 */
public class StudentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		pw.append("Served at: ").append(request.getContextPath()).append("<br>").println();
		response.setContentType("text/html");
		List<Student> students = StudentDAO.getStudents();
		printStudents(pw, students);
	}

	private void printStudents(PrintWriter pw, List<Student> students) {
		// Menu Main/Refresh/Insert
		pw.append("<h1>Student Master List</h1><br>").println();
		pw.append("<a href='Login'><img src='data/menu3.png' alt='Menu' width='28' height='28'></a>");
		pw.append(" | <a href='StudentsServlet'><img src='data/refresh.png' alt='Refresh' width='28' height='28'></a>");
		pw.append(" | <a href='StudentEditServlet'><img src='data/useradd.png' alt='Add' width='30' height='30'></a>");
		pw.println("<br/><br/>");
		pw.append("<table>");
		pw.append("<tr>");
		// Header
		// stuid; firstname; lastname; dob; email; phone; created; modified; clsid_class;
		pw.append("<th>Student<br/>Identifier</th>");
		pw.append("<th>First Name</th>");
		pw.append("<th>Last Name</th>");
		pw.append("<th>Birthday</th>");
		pw.append("<th>Email</th>");
		pw.append("<th>Phone</th>");
		pw.append("<th>Class</th>");
		pw.append("<th>actions</th>");
		pw.append("</tr>");
		// Data
		for (Student stu : students) {
			pw.append("<tr>").println();
			pw.append("<td>").append(ServletHTMLUtil.getValue(stu.getStuid())).append("</td>").println();
			pw.append("<td>").append(ServletHTMLUtil.getValue(stu.getFirstname()).toString()).append("</td>").println();
			pw.append("<td>").append(ServletHTMLUtil.getValue(stu.getLastname())).append("</td>").println();
			pw.append("<td>").append(ServletHTMLUtil.getValue(stu.getDob())).append("</td>").println();
			pw.append("<td>").append(ServletHTMLUtil.getValue(stu.getEmail())).append("</td>").println();
			pw.append("<td>").append(ServletHTMLUtil.getValue(stu.getPhone())).append("</td>").println();
			pw.append("<td>").append(ServletHTMLUtil.getValue(stu.getClsid_class())).append("</td>").println();
			// Menu Edit/Delete Row
			String cmdPars = "stuid="+stu.getStuid();
			pw.append("<td>").append("<a href='StudentEditServlet?"+cmdPars+"'><img src='data/useredit.png' alt='Edit' width='30' height='30'></a>").println();
			cmdPars = cmdPars+"&msg="+stu.getFirstname()+" "+stu.getLastname();
			pw.append("|<a href='StudentDeleteServlet?"+cmdPars+"'><img src='data/userdelete.png' alt='Delete' width='30' height='30'></a>");
			pw.append("</td>").println();
			pw.append("</tr>").println();
			System.out.println(stu);
		}
		pw.append("<table>");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
