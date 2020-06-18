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

@WebServlet("/deleteUserServlet")
public class DeleteUserServlet extends HttpServlet {

	private static final long serialVersionUID = -5680588858178701561L;
private static Connection conn; // make sure that driver software is 
	
public void init() throws ServletException {

	System.out.println(this.getServletName() + " instantiated!");
	super.init();
	
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "inna", "123");
	
	System.out.println("Connected!");
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	} catch (SQLException e) {
		e.printStackTrace();
	}
}
//we also use doPost but we will take different parameters
protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

String email = req.getParameter("email");

try {
		Statement statement = conn.createStatement();
		int result = statement.executeUpdate("DELETE FROM ib_user WHERE email= '"+email+"'"); 
	PrintWriter out = res.getWriter();	
	
	if (result > 0) {
		out.println("<h1>User Deleted!</h1>");
	}
	else {
		out.println("<h1>Unable to find User in the database</h1>");
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
		System.out.println(" Connection closed.");
	} catch (SQLException e) {
		e.printStackTrace();
	}
}


}
