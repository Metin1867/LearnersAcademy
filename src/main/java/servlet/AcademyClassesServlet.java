package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AcademyClassDAO;
import dao.StudentDAO;
import pojo.AcademyClass;
import pojo.ClassSubject;
import pojo.Student;

/**
 * Servlet implementation class AcademyClassesServlet
 */
public class AcademyClassesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AcademyClassesServlet() {
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
		List<AcademyClass> academyClasses = AcademyClassDAO.getAcademyClasss();
		printAcademyClasses(pw, academyClasses);
	}

	private void printAcademyClasses(PrintWriter pw, List<AcademyClass> academyClasses) {
		// Menu Main/Refresh/Insert
		pw.append("<a href='Login'><img src='data/menu3.png' alt='Menu' width='28' height='28'></a>");
		pw.append(" | <a href='AcademyClassesServlet'><img src='data/refresh.png' alt='Refresh' width='28' height='28'></a>");
		pw.append(" | <a href='AcademyClassEditServlet'><img src='data/useradd.png' alt='Add' width='30' height='30'></a>");
		pw.println("<br/><br/>");
		pw.append("<table>");
		pw.append("<tr>");
		// Header
		// clsid; label; start; end; classSubjects
		pw.append("<th>Class<br/>Identifier</th>");
		pw.append("<th>Label</th>");
		pw.append("<th>Start</th>");
		pw.append("<th>End</th>");
		pw.append("<th>Subjects</th>");
		pw.append("<th>actions</th>");
		pw.append("</tr>");
		// Data
		for (AcademyClass cls : academyClasses) {
			pw.append("<tr>").println();
			pw.append("<td>").append(String.valueOf(cls.getClsid())).append("</td>").println();
			pw.append("<td>").append(cls.getLabel().toString()).append("</td>").println();
			pw.append("<td>").append(String.valueOf(cls.getStart())).append("</td>").println();
			pw.append("<td>").append(String.valueOf(cls.getEnd())).append("</td>").println();
			pw.append("<td>").append(cls.getClassSubjects().toString()).append("</td>").println();
			// Menu Edit/Delete Row
			String cmdPars = "clsid="+cls.getClsid();
			pw.append("<td>").append("<a href='AcademyClassEditServlet?"+cmdPars+"'><img src='data/useredit.png' alt='Edit' width='30' height='30'></a>").println();
			cmdPars = cmdPars+"&msg="+cls.getLabel();
			pw.append("|<a href='AcademyClassDeleteServlet?"+cmdPars+"'><img src='data/userdelete.png' alt='Delete' width='30' height='30'></a>");
			pw.append("</td>").println();
			pw.append("</tr>").println();
			System.out.println(cls);
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
