package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AcademyClassDAO;
import dao.SubjectDAO;
import pojo.AcademyClass;
import pojo.Subject;

/**
 * Servlet implementation class SubjectsServlet
 */
public class SubjectsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubjectsServlet() {
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
		List<Subject> subjects = SubjectDAO.getSubjects();
		printSubjects(pw, subjects);
	}

	private void printSubjects(PrintWriter pw, List<Subject> subjects) {
		// Menu Main/Refresh/Insert
		pw.append("<h1>Subject Master List</h1><br>").println();
		pw.append("<a href='Login'><img src='data/menu3.png' alt='Menu' width='28' height='28'></a>");
		pw.append(" | <a href='SubjectsServlet'><img src='data/refresh.png' alt='Refresh' width='28' height='28'></a>");
		pw.append(" | <a href='SubjectEditServlet'><img src='data/useradd.png' alt='Add' width='30' height='30'></a>");
		pw.println("<br/><br/>");
		pw.append("<table>");
		pw.append("<tr>");
		// Header
		// sbjid; topic; teaid_expert
		pw.append("<th>Subject<br/>Identifier</th>");
		pw.append("<th>Topic</th>");
		pw.append("<th>Subjects</th>");
		pw.append("<th>Expert<br/>Identifier</th>");
		pw.append("</tr>");
		// Data
		for (Subject sbj : subjects) {
			pw.append("<tr>").println();
			pw.append("<td>").append(String.valueOf(sbj.getSbjid())).append("</td>").println();
			pw.append("<td>").append(sbj.getTopic().toString()).append("</td>").println();
			if (sbj.getTeaid_expert() == -1)
				pw.append("<td>").append("</td>").println();
			else
				pw.append("<td>").append(String.valueOf(sbj.getTeaid_expert())).append("</td>").println();
			// Menu Edit/Delete Row
			String cmdPars = "sbjid="+sbj.getSbjid();
			pw.append("<td>").append("<a href='SubjectEditServlet?"+cmdPars+"'><img src='data/useredit.png' alt='Edit' width='30' height='30'></a>").println();
			cmdPars = cmdPars+"&msg="+sbj.getTopic();
			pw.append("|<a href='SubjectDeleteServlet?"+cmdPars+"'><img src='data/userdelete.png' alt='Delete' width='30' height='30'></a>");
			pw.append("</td>").println();
			pw.append("</tr>").println();
			System.out.println(sbj);
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
