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
		System.out.println("ClassSubjectsEditServlet.doGet(...)");
		PrintWriter pw = response.getWriter();
		pw.append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
		String action = request.getParameter("submit");
		int clsid = Integer.valueOf(request.getParameter("clsid"));
		System.out.println("action: " + action);
		if ("Cancel".equals(action)) {
			RequestDispatcher rd = request.getRequestDispatcher("AcademyClassEditServlet&form&clsid="+clsid);
			rd.forward(request, response);
		}
			
		String sbjidStr = request.getParameter("sbjid");
		int sbjid = -1;
		if (sbjidStr!=null && !"".equals(sbjidStr)) {
			sbjid = Integer.valueOf(sbjidStr);
			isNew = false;
		}
		ClassSubject sbj = ClassSubjectDAO.getClassSubject(clsid, sbjid);
		showEditForm(pw, sbj, clsid);
	}

	private void showEditForm(PrintWriter pw, ClassSubject sbj, int clsid) {
		pw.append("<h1>Class Subject Relationship Maintenance</h1>");
		if (sbj == null) {
			sbj = new ClassSubject();
			sbj.setClsid(clsid);
			System.out.println("Class Subject Maintenance: INSERT Form");
		} else {
			System.out.println("Class Subject Maintenance: UPDATE Form");
		}
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
		System.out.println("ClassSubjectsEditServlet.doPost(...)");
		PrintWriter pw = response.getWriter();
		pw.append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
		String action = request.getParameter("submit");
		int clsid = ServletHTMLUtil.getIntValue(request.getParameter("clsid"));
		System.out.println("action: " + action);
		if (!"Cancel".equals(action)) {
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
			
			ClassSubjectDAO dao = new ClassSubjectDAO(sbj);
			if (isNew) {
				System.out.println("Subject DAO INSERT");
				dao.insert();
			} else {
				System.out.println("Subject DAO UPDATE");
				dao.update();
			}
		}
		System.out.println("Forward to AcademyClassEditServlet?clsid="+clsid);
		RequestDispatcher rd = request.getRequestDispatcher("AcademyClassEditServlet?form&clsid="+clsid);
		rd.forward(request, response);
		
	}

}
