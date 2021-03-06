package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/loginServlet2")
public class LoginServlet2 extends HttpServlet{

	private static final long serialVersionUID = 1L;
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

	String email = req.getParameter("email");//"" - names of parameters, without - new variables
	String password = req.getParameter("password"); 
 
		try {
			Statement statement = conn.createStatement();// pass- name of column
			ResultSet resultSet = statement.executeQuery("SELECT * FROM ib_user2 WHERE email='"+email+"' AND pass='"+password+"'");
					
		 // we want to send to the work of creating a response to our HomeServlet
	//the RD is set up:
	RequestDispatcher rd = req.getRequestDispatcher("homeServlet");
		if (resultSet.next()) {
			req.setAttribute("message", "Access granted! Welcome to the HomeServlet "+ email);		
			rd.forward(req,res);// we havent created the home servlet	
// we are forwarding the req and resp object to the HomeServlet
// the home servlet can retrieve username and password and it can write the response back to the client
		} else {
			rd=req.getRequestDispatcher("login.html");//else, we send the user BACK to the login page
		//	req.setAttribute("message", "Your email or password are incorrect. Try again.");		
			rd.include(req, res);
			
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

	
	

