package login;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Profile
 */
public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Profile() {
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
		// HttpSession
		/*
		Cookie c[]=request.getCookies();
		if(c!=null) {
			pw.print("we1come to " + c[0].getValue());
		} /*
		*/
		// HttpSession
		//
		HttpSession hs=request.getSession();
		String user = (String) hs.getAttribute("userid");
		if(user!=null) {
			pw.print("welcome to " + user);
		} /*
		*/
		else {
			// a bug in Chrome show some thing wrong!
			pw.print("hey login first");
		}   
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
