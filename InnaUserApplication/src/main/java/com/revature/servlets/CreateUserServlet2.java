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

import com.revature.daos.UserDao;
import com.revature.models.Role;
import com.revature.models.User;

@WebServlet("/createUserServlet2")
public class CreateUserServlet2 extends HttpServlet {

private static final long serialVersionUID = -5680588858178701561L;
private static Connection conn; 
	
public void init() throws ServletException {
// establish jdbc connection here because it s called once
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
	
// since we are creating a user and changing the database
//we must use Post http method... we do so in with doPost
protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
// we will eventually use a SQL insert statement here
// info that we are posting/inserting to the db comes from Parameters 
	int userId=Integer.parseInt(req.getParameter("userId"));//"" - parameter name from html!!!
	String userName=req.getParameter("userName");
	String userPass = req.getParameter("userPass");
	String firstName=req.getParameter("firstName");
	String lastName=req.getParameter("lastName");
	String email=req.getParameter("email");
	int roleId = Integer.parseInt(req.getParameter("roleId"));

// then we create an insert statement Statement statement = con.createStatement();
	try {
		Statement statement = conn.createStatement();
		
		int result = statement.executeUpdate("INSERT INTO ib_user2 VALUES('"+userId+"', '"+userName+"',"
				+ "'"+userPass+"','"+firstName+"', '"+lastName+"', '"+email+"', '"+roleId+"')" ); // return : how many rows are affected
	// insert statement affects one row, but update and delete can affect more	
		UserDao udao = new UserDao();
		String roleQuery = "SELECT * FROM ib_role where role_id = " + roleId;
		ResultSet roleRs = statement.executeQuery(roleQuery);
		String roleName;
		if (roleRs.next()) {
			roleName = roleRs.getString("role_name");
			roleRs.close();
		}
		else {
			roleName = null;
		}
		Role role = new Role(roleId, roleName);
		User user = new User (userId, userName, userPass, firstName, lastName, email, role);
		udao.create(user);
	PrintWriter out = res.getWriter();	
	
	if (result > 0) {
		out.println("<h1>User Created!</h1>");
	}
	else {
		out.println("<h1>Error creating user...</h1>");
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
