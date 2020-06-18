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

@WebServlet("/homeServlet") //should be the same as assigning lower
public class HomeServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private static Connection conn; // make sure that driver software is 
	
	public void init() throws ServletException {

		System.out.println(this.getServletName() + " instantiated!");
		super.init();
		
		}
	//we will get the message that was set in LoginServlet Request
	protected void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
	PrintWriter out = res.getWriter();// dynamically created printwriter obj
	res.setContentType("text/html");
	// we print the message thats the value of the message attribute
	out.println(req.getAttribute("message"));	
	
	req.setAttribute("message", "\nSelect action:");
	out.println(req.getAttribute("message"));
	req.setAttribute("message", "\n Admin: find  accounts by ID/all, find  users by ID/all, update user/account," +
					"\n delete user/account, withdraw, deposit, transfer, submit account? ");
	out.println(req.getAttribute("message"));
	req.setAttribute("message", "\n\nEmployee:find  accounts by ID/all, find  users by ID/all, submit account  ?");
	out.println(req.getAttribute("message"));
	req.setAttribute("message", "\n\nUser: only for own: create/update/delete user/account, withdraw, deposit, transfer ");
	out.println(req.getAttribute("message"));
	req.setAttribute("message", "\n\nPremium User: only for own: create/delete/update user/account(standart or joint),"
					+ "withdraw, deposit, transfer, add users to joint account ?");
	out.println(req.getAttribute("message"));
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

	
	

