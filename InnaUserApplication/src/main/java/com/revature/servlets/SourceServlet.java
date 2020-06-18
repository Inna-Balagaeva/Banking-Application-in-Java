package com.revature.servlets;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
// we are creating a basic HTML dynamic page with a HYPERLINK that goes to another servlet with our
//stored Username value

public class SourceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Cookie[] cookies = request.getCookies();
		for (int i=0; i<cookies.length;i++) {
			System.out.println(cookies[i].getName());
			System.out.println(cookies[i].getValue());
			
		}
		response.addCookie(new Cookie("MySecurityToken", "ABCD123"));
		
		String username = request.getParameter("userName");//we got a parameter from html page
		
		HttpSession session = request.getSession();
		session.setAttribute("user", username);// this is quotes or no quotes
		
		// now we will print our dynamic HTML response
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<a href='targetServlet'> Click here to see the username that you entered </a>");
	}
}