package com.revature.servlets;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jdt.internal.compiler.ast.ThisReference;

@WebServlet("/updateUserServlet2")
public class UpdateUserServlet2 extends HttpServlet {

	private static final long serialVersionUID = -5680588858178701561L;
private static Connection conn; // make sure that driver software is 
	
public void init() throws ServletException {

	System.out.println(this.getServletName() + " instantiated!");
	super.init();
	
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "inna", "123");
	
	System.out.println(" Connected!");
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	} catch (SQLException e) {
		e.printStackTrace();
	}
}
//we also use doPost but we will take different parameters
protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

String email = req.getParameter("email");
String userPass = req.getParameter("userPass");// this is old password'
String userPassNew = req.getParameter("userPassNew");//this is new password
 
	try {
		Statement statement = conn.createStatement();
		int result = statement.executeUpdate("UPDATE ib_user2 SET pass='"+userPassNew+"' WHERE email='"+email+"' AND pass='"+userPass+"'"); // return : how many rows are affected
		
	PrintWriter out = res.getWriter();	
	
	if (result > 0) {
		out.println("<h1>Password updated for " + email +"!</h1>");
	}
	else {
		out.println("<h1>Error updating user...</h1>");
	}
		
			
	} catch (SQLException e) {
		e.printStackTrace();
	}
}


public void destroy() {
	System.out.println(this.getServletName() + " destroyed!");
	super.destroy();
	try {
		conn.close();
		System.out.println(" sConnection closed.");
	} catch (SQLException e) {
		e.printStackTrace();
	}
}


}
