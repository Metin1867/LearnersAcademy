package login;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.SessionUtil;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw=response.getWriter();
		RequestDispatcher rd=request.getRequestDispatcher("index.html");
		rd.include(request, response);
		if (SessionUtil.loggedIn(request)) {
			pw.println("<h1>Backend Admin for Learner's Academy<h1>");
			pw.println("<a href='TeachersServlet'>Teachers</a>");
			pw.println("<a href='SubjectsServlet'>Subjects</a>");
			pw.println("<a href='AcademyClassesServlet'>Classes</a>");
			pw.println("<a href='StudentsServlet'>Students</a><br><br>");
			pw.println("<a href='reports.html'>Reports</a>");
		};
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = request.getParameter("user");
		String pwd=request.getParameter("pwd");
		PrintWriter pw=response.getWriter();
		RequestDispatcher rd=request.getRequestDispatcher("index.html");
		rd.include(request, response);
		if (request.getParameter("notCompanyLike")==null)
			if(user.equals(pwd)) {
				// Cookie
				/*   
				Cookie c = new Cookie("userid", user);
				response.addCookie(c); /*
				*/
				// HttpSession
				//
				HttpSession hs=request.getSession();
				hs.setAttribute("userid", user);   /*
				*/
				pw.println("login successfull");
				pw.println("<h1>Backend Admin for Learner’s Academy<h1>");
				pw.println("<a href='TeachersServlet'>Teachers</a>");
				pw.println("<a href='SubjectsServlet'>Subjects</a>");
				pw.println("<a href='AcademyClassesServlet'>Classes</a>");
				pw.println("<a href='StudentsServlet'>Students</a><br><br>");
				pw.println("<a href='reports.html'>Reports</a>");

			}
			else {
				pw.println("check the credentials");
			}
		else {
			// String user=request.getParameter("user");
			response.sendRedirect("./UserProfile?user="+user);   
		}
	}

}
