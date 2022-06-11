package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AcademyClassDAO;
import pojo.AcademyClass;
import pojo.ClassSubject;
import util.ServletHTMLUtil;

/**
 * Servlet implementation class AcademyClassEditServlet
 */
public class AcademyClassEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private boolean isNew=true;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AcademyClassEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("AcademyClassEditServlet.doGet(...)");
		PrintWriter pw = response.getWriter();
		pw.append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
		String clsidStr = request.getParameter("clsid");
		int clsid = -1;
		if (clsidStr!=null) {
			clsid = Integer.valueOf(clsidStr);
			isNew = false;
		}
		System.out.println("AcademyClassEditServlet.clsid= "+clsid);
		System.out.println();
		AcademyClass cls = AcademyClassDAO.getAcademyClass(clsid);
		showEditForm(pw, cls);
	}

	private void showEditForm(PrintWriter pw, AcademyClass cls) {
		pw.append("<h1>Class Maintenance</h1>");
		if (cls == null)
			cls = new AcademyClass();
		pw.append(ServletHTMLUtil.startFormPost("AcademyClassEditServlet")); 
		pw.append(ServletHTMLUtil.getNumberInput("Identifier", "clsid", cls.getClsid() ));
		pw.append(ServletHTMLUtil.getTextInput("Label", "label", cls.getLabel() ));
		pw.append(ServletHTMLUtil.getTextInput("Start", "start", cls.getStart() ));
		pw.append(ServletHTMLUtil.getTextInput("End", "end", cls.getEnd() ));
		pw.append(ServletHTMLUtil.getSubmitInput("Save"));
		pw.append(ServletHTMLUtil.getSubmitInput("Cancel"));
		pw.append(ServletHTMLUtil.endForm()); 
		pw.append("<br>");
		
		if (cls.getClsid()!=-1)
			showListSubjects(pw, cls);
	}

	private void showListSubjects(PrintWriter pw, AcademyClass cls) {
		List<ClassSubject> subjects = cls.getClassSubjects();
		String cmdPars = "clsid="+cls.getClsid();
		cmdPars = cmdPars+"&sbjid=";
		pw.append("<a href='ClassSubjectsEditServlet?"+cmdPars+"'><img src='data/useradd.png' alt='Add' width='30' height='30'></a>");
		pw.println("<br/><br/>");
		pw.append("<table>");
		pw.append("<tr>");
		// Header
		// [clsid]; sbjid; start; end; teaid_teacher = -1;
		pw.append("<th>Subject<br/>Identifier</th>");
		pw.append("<th>Start</th>");
		pw.append("<th>End</th>");
		pw.append("<th>Teacher<br/>Identifier</th>");
		pw.append("<th>actions</th>");
		pw.append("</tr>");
		// Data
		for (ClassSubject sbj : subjects) {
			pw.append("<tr>").println();
			pw.append("<td>").append(ServletHTMLUtil.getValue(sbj.getSbjid())).append("</td>").println();
			pw.append("<td>").append(ServletHTMLUtil.getValue(sbj.getStart())).append("</td>").println();
			pw.append("<td>").append(ServletHTMLUtil.getValue(sbj.getEnd())).append("</td>").println();
			System.out.println("ClassSubject TeacherStr: " + sbj.getTeaid_teacher());
			System.out.println("ClassSubject Teacher: " + ServletHTMLUtil.getValue(sbj.getTeaid_teacher()));
			pw.append("<td>").append(ServletHTMLUtil.getValue(sbj.getTeaid_teacher())).append("</td>").println();
			// Menu Edit/Delete Row
			cmdPars = "clsid="+sbj.getClsid();
			cmdPars = cmdPars+"&sbjid="+sbj.getSbjid();
			pw.append("<td>").append("<a href='ClassSubjectDeleteServlet?"+cmdPars+"'><img src='data/userdelete.png' alt='Delete' width='30' height='30'></a>");
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
		System.out.println("AcademyClassEditServlet.doPost(...)");
		PrintWriter pw = response.getWriter();
		pw.append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
		int clsid=-1;
		if (request.getParameter("skip")=="") {
			clsid = Integer.valueOf(request.getParameter("clsid"));			
			RequestDispatcher rd = request.getRequestDispatcher("AcademyClassesServlet?clsid="+clsid);
			rd.forward(request, response);
		} else if (request.getParameter("form")=="") {
			/*response.setContentType("text/html");
			String clsidStr = request.getParameter("clsid");
			clsid = -1;
			if (clsidStr!=null) {
				clsid = Integer.valueOf(clsidStr);
				isNew = false;
			}
			AcademyClass cls = AcademyClassDAO.getAcademyClass(clsid);
			showEditForm(pw, cls);*/
			System.out.println("Form ... forward to doGet(...)");
			doGet(request, response);
		} else {
			clsid = ServletHTMLUtil.getIntValue(request.getParameter("clsid"));
			String label = request.getParameter("label");
			Date start = ServletHTMLUtil.getDate(request.getParameter("start"));
			Date end = ServletHTMLUtil.getDate(request.getParameter("end"));
			String classSubjectsStr = request.getParameter("subjects");
			// List<ClassSubject> classSubjects = new ArrayList<>();
			AcademyClass cls = new AcademyClass();
			cls.setClsid(clsid);
			cls.setLabel(label);
			cls.setStart(start);
			cls.setEnd(end);
			System.out.println("Insert/Update Academy Class: "+cls);
			
			AcademyClassDAO clsDAO = new AcademyClassDAO(cls);
			if (isNew)
				clsDAO.insert();
			else
				clsDAO.update();
			RequestDispatcher rd = request.getRequestDispatcher("AcademyClassesServlet?clsid="+clsid);
			rd.forward(request, response);
		}
	}

}
