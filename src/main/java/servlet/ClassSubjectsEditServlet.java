package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ClassSubjectDAO;
import pojo.ClassSubject;
import pojo.Student;
import util.ServletHTMLUtil;

/**
 * Servlet implementation class ClassSubjectsEditServlet
 */
public class ClassSubjectsEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private boolean isNew=true;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClassSubjectsEditServlet() {
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
		String action = request.getParameter("submit");
		int clsid = Integer.valueOf(request.getParameter("clsid"));
		if ("Cancel".equals(action)) {
			RequestDispatcher rd = request.getRequestDispatcher("AcademyClassEditServlet&form&clsid="+clsid);
			rd.forward(request, response);
		}
			
		String sbjidStr = request.getParameter("sbjid");
		int sbjid = -1;
		if (!(sbjidStr==null || "".equals(sbjidStr))) {
			sbjid = Integer.valueOf(sbjidStr);
			isNew = false;
		}
		ClassSubject sbj = ClassSubjectDAO.getClassSubject(clsid, sbjid);
		if (sbj==null) {
			sbj = new ClassSubject();
			sbj.setClsid(clsid);
			sbj.setSbjid(sbjid);
		}
		showEditForm(pw, sbj);
	}

	private void showEditForm(PrintWriter pw, ClassSubject sbj) {
		pw.append("<h1>Class Subject Relationship Maintenance</h1>");
		if (sbj == null)
			sbj = new ClassSubject();
		pw.append(ServletHTMLUtil.startForm("ClassSubjectsEditServlet", "post")); 
		pw.append(ServletHTMLUtil.getNumberInputReadOnly("Class Identifier", "clsid", sbj.getClsid()));
		pw.append(ServletHTMLUtil.getNumberInput("Subject Identifier", "sbjid", sbj.getSbjid()));
		pw.append(ServletHTMLUtil.getTextInput("Start", "start", sbj.getStart()));
		pw.append(ServletHTMLUtil.getTextInput("End", "end", sbj.getEnd()));
		pw.append(ServletHTMLUtil.getNumberInput("Teacher Identifier", "teacher", sbj.getTeaid_teacher()));
		pw.append(ServletHTMLUtil.getSubmitInput("Save"));
		pw.append(ServletHTMLUtil.getSubmitInput("Cancel"));
		pw.append(ServletHTMLUtil.endForm()); 
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		pw.append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
		int clsid = ServletHTMLUtil.getIntValue(request.getParameter("clsid"));
		int sbjid = ServletHTMLUtil.getIntValue(request.getParameter("sbjid"));
		Date start = ServletHTMLUtil.getDate(request.getParameter("start"));
		Date end = ServletHTMLUtil.getDate(request.getParameter("end"));
		int teaid = ServletHTMLUtil.getIntValue(request.getParameter("teacher"));
		ClassSubject sbj = new ClassSubject();
		sbj.setClsid(clsid);
		sbj.setSbjid(sbjid);
		sbj.setStart(start);
		sbj.setEnd(end);
		sbj.setTeaid_teacher(teaid);
		System.out.println(sbj);
		
		ClassSubjectDAO sbjDAO = new ClassSubjectDAO(sbj);
		if (isNew)
			sbjDAO.insert();
		else
			sbjDAO.update();
		System.out.println("Forward to AcademyClassEditServlet?clsid="+clsid);
		RequestDispatcher rd = request.getRequestDispatcher("AcademyClassEditServlet?form&clsid="+clsid);
		rd.forward(request, response);
		
	}

}
