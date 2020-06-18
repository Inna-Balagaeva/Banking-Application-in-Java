package com.revature.servlets;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jdt.internal.compiler.ast.ThisReference;

// @WebServlet("/readUserServlet")
public class ReadUserServlet extends HttpServlet {

	private static final long serialVersionUID = -5680588858178701561L;
private static Connection conn; // make sure that driver software is 
	
public void init() throws ServletException {

	System.out.println(this.getServletName() + "instantiated!");
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
//we also use doGet which is Idempotent
protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

	try {
		Statement statement = conn.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT * FROM ib_user");
// we still use print writer to build a dynamic html		
	PrintWriter out = res.getWriter();	
	
		out.println("<table>");
		out.println("<tr>");
		out.println("<th>");
		out.println("First Name");// return a table in html of fn,ln,email
		out.println("</th>");
		out.println("<th>");
		out.println("Last Name");
		out.println("</th>");
		out.println("<th>");
		out.println("Email");
		out.println("</th>");
		out.println("</tr>");
		
		while(resultSet.next()) {
		  out.println("<tr>");
		
		 out.println("<td>");
		 out.println(resultSet.getString("firstname")); 
		 out.println("<td>");
		 
		 out.println("<td>");
		 out.println(resultSet.getString("lastname")); 
		 out.println("<td>");
		 
		 out.println("<td>");
		 out.println(resultSet.getString("email")); 
		 out.println("<td>");
		 
		 out.println("</tr>");
		}
	
	out.println("</table");
			
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
