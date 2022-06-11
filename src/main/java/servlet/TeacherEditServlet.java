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

import dao.TeacherDAO;
import pojo.Teacher;
import util.ServletHTMLUtil;

/**
 * Servlet implementation class TeacherEditServlet
 */
public class TeacherEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private boolean isNew=true;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeacherEditServlet() {
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
		String teaidStr = request.getParameter("teaid");
		int teaid = -1;
		if (teaidStr!=null) {
			teaid = Integer.valueOf(teaidStr);
			isNew = false;
		}
		Teacher tea = TeacherDAO.getTeacher(teaid);
		showEditForm(pw, tea);
	}

	private void showEditForm(PrintWriter pw, Teacher tea) {
		pw.append("<h1>Teacher Maintenance</h1>");
		if (tea == null)
			tea = new Teacher();
		pw.append(ServletHTMLUtil.startFormPost("TeacherEditServlet")); 
		pw.append(ServletHTMLUtil.getNumberInput("Subject Identifier", "teaid", tea.getTeaid() ));
		pw.append(ServletHTMLUtil.getTextInput("First Name", "firstname", tea.getFirstname() ));
		pw.append(ServletHTMLUtil.getTextInput("Last Name", "lastname", tea.getLastname() ));
		pw.append(ServletHTMLUtil.getTextInput("Birthday", "dob", tea.getDob() ));
		pw.append(ServletHTMLUtil.getTextInput("Email", "email", tea.getEmail() ));
		pw.append(ServletHTMLUtil.getTextInput("Phone", "phone", tea.getPhone()) );
		pw.append(ServletHTMLUtil.getSubmitInput("register"));
		pw.append(ServletHTMLUtil.endForm()); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		pw.append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
		int teaid = Integer.valueOf(request.getParameter("teaid"));
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		Date dob = ServletHTMLUtil.getDate(request.getParameter("dob"));
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		Teacher tea = new Teacher(teaid, firstname, lastname, dob, email, phone, new Timestamp(System.currentTimeMillis()), null);
		System.out.println(tea);
		
		TeacherDAO stuDAO = new TeacherDAO(tea);
		System.out.println("Teacher object will be " + (isNew ? "inserted." : "updated."));
		if (isNew)
			stuDAO.insert();
		else
			stuDAO.update();
		RequestDispatcher rd = request.getRequestDispatcher("TeachersServlet");
		rd.forward(request, response);
		
	}

}
