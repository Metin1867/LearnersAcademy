package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TeacherDAO;
import pojo.Teacher;

/**
 * Servlet implementation class TeachersServlet
 */
public class TeachersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeachersServlet() {
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
		List<Teacher> teachers = TeacherDAO.getTeachers();
		printTeachers(pw, teachers);
	}

	private void printTeachers(PrintWriter pw, List<Teacher> teachers) {
		// Menu Main/Refresh/Insert
		pw.append("<a href='Login'><img src='data/menu3.png' alt='Menu' width='28' height='28'></a>");
		pw.append(" | <a href='TeachersServlet'><img src='data/refresh.png' alt='Refresh' width='28' height='28'></a>");
		pw.append(" | <a href='TeacherEditServlet'><img src='data/useradd.png' alt='Add' width='30' height='30'></a>");
		pw.println("<br/><br/>");
		pw.append("<table>");
		pw.append("<tr>");
		// Header
		// teaid; firstname; lastname; dob; email; phone; created; modified;
		pw.append("<th>Teacher<br/>Identifier</th>");
		pw.append("<th>First Name</th>");
		pw.append("<th>Last Name</th>");
		pw.append("<th>Birthday</th>");
		pw.append("<th>Email</th>");
		pw.append("<th>Phone</th>");
		pw.append("<th>actions</th>");
		pw.append("</tr>");
		// Data
		for (Teacher tea : teachers) {
			// pw.append(stu.toString()).append("<br>").println();
			pw.append("<tr>").println();
			pw.append("<td>").append(String.valueOf(tea.getTeaid())).append("</td>").println();
			pw.append("<td>").append(tea.getFirstname().toString()).append("</td>").println();
			pw.append("<td>").append(tea.getLastname()).append("</td>").println();
			pw.append("<td>").append(String.valueOf(tea.getDob())).append("</td>").println();
			pw.append("<td>").append(tea.getEmail()).append("</td>").println();
			pw.append("<td>").append(tea.getPhone()).append("</td>").println();
			// Menu Edit/Delete Row
			String cmdPars = "teaid="+tea.getTeaid();
			pw.append("<td>").append("<a href='TeacherEditServlet?"+cmdPars+"'><img src='data/useredit.png' alt='Edit' width='30' height='30'></a>").println();
			cmdPars = cmdPars+"&msg="+tea.getFirstname()+" "+tea.getLastname();
			pw.append("|<a href='TeacherDeleteServlet?"+cmdPars+"'><img src='data/userdelete.png' alt='Delete' width='30' height='30'></a>");
			pw.append("</td>").println();
			pw.append("</tr>").println();
			System.out.println(tea);
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
