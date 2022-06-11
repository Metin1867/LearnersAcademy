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
import dao.SubjectDAO;
import pojo.Student;
import pojo.Subject;
import util.ServletHTMLUtil;

/**
 * Servlet implementation class SubjectEditServlet
 */
public class SubjectEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private boolean isNew=true;
      
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubjectEditServlet() {
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
		String sbjidStr = request.getParameter("sbjid");
		int sbjid = -1;
		if (sbjidStr!=null) {
			sbjid = Integer.valueOf(sbjidStr);
			isNew = false;
		}
		Subject sbj = SubjectDAO.getSubject(sbjid);
		showEditForm(pw, sbj);
	}

	private void showEditForm(PrintWriter pw, Subject sbj) {
		pw.append("<h1>Subject Maintenance</h1>");
		if (sbj == null)
			sbj = new Subject();
		pw.append(ServletHTMLUtil.startFormPost("SubjectEditServlet")); 
		pw.append(ServletHTMLUtil.getNumberInput("Subject Identifier", "sbjid", sbj.getSbjid()));
		pw.append(ServletHTMLUtil.getTextInput("Topic", "topic", sbj.getTopic()));
		pw.append(ServletHTMLUtil.getNumberInput("Expert Identifier", "expert", sbj.getTeaid_expert()));
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
		int sbjid = ServletHTMLUtil.getIntValue(request.getParameter("sbjid"));
		String topic = request.getParameter("topic");
		int teaid = ServletHTMLUtil.getIntValue(request.getParameter("expert"));
		Subject sbj = new Subject();
		sbj.setSbjid(sbjid);
		sbj.setTopic(topic);
		sbj.setTeaid_expert(teaid);
		System.out.println(sbj);
		
		SubjectDAO sbjDAO = new SubjectDAO(sbj);
		if (isNew)
			sbjDAO.insert();
		else
			sbjDAO.update();
		RequestDispatcher rd = request.getRequestDispatcher("SubjectsServlet");
		rd.forward(request, response);
		
	}

}
