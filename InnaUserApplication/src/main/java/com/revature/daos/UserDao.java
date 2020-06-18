package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Role;
import com.revature.models.User;
import com.revature.utils.ConnectionFactory;

public class UserDao implements DAO<User, Integer> {

	@Override
	public List<User> findAll() {

		List<User> users = new ArrayList<User>();
		try {
			Connection conn = ConnectionFactory.getConnection();
			if (conn != null) {
				System.out.println("Congrats you're connected!");
			} else {
				System.out.println("Connection failed");
			}

			String sql = "SELECT * FROM ib_user2 LEFT JOIN ib_role ON ib_user2.ref_role_id = ib_role.role_id";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs != null) {
				System.out.println("RS not null");
			}
			while (rs.next()) {
				int id = rs.getInt("user_id");
				String username = rs.getString("username");
				String password = rs.getString("pass");
				String firstName = rs.getString("firstname");
				String lastName = rs.getString("lastname");
				String email = rs.getString("email");
				int roleId = rs.getInt("ref_role_id");
				String roleName = rs.getString("role_name");// from table Role

				Role role = new Role(roleId, roleName);
				User u = new User(id, username, password, firstName, lastName, email, role);
				users.add(u);
			} // while
			conn.close();
		} catch (SQLException e) {// If something goes wrong, return an empty list
			e.printStackTrace();
			// return new ArrayList<>();
		}
		return users;
	}

	@Override
	public User findById(Integer id) {
		try (Connection conn = ConnectionFactory.getConnection()) {
			String sql = "SELECT * FROM ib_user2 LEFT JOIN ib_role ON ib_user2.ref_role_id = ib_role.role_id WHERE user_id = ?";
			PreparedStatement preps = conn.prepareStatement(sql);
			preps.setInt(1, id);
			ResultSet rs = preps.executeQuery(sql);

			while (rs.next()) {
				int userid = rs.getInt("user_id");
				String username = rs.getString("username");
				String password = rs.getString("pass");
				String firstName = rs.getString("firstname");
				String lastName = rs.getString("lastname");
				String email = rs.getString("email");
				int roleId = rs.getInt("ref_role_id");
				String roleName = rs.getString("role_name");// from table Role

				Role role = new Role(roleId, roleName);
				return new User(userid, username, password, firstName, lastName, email, role);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;		
	}

	@Override
	public int create(User u) {

		try (Connection conn = ConnectionFactory.getConnection()) {

			String sql = "INSERT INTO ib_user2  VALUES (?, ?, ?, ?, ?, ?, ?)";

			PreparedStatement preps = conn.prepareStatement(sql);

			preps.setInt(1, u.getUserId());
			preps.setString(2, u.getUserName()); // replace 1st ? mark with u.getUsername()
			preps.setString(3, u.getUserPass()); // replace 2nd ? mark with u.getPassword()
			preps.setString(4, u.getFirstName()); // replace 3rd ? mark with u.getFirstName()
			preps.setString(5, u.getLastName());
			preps.setString(6, u.getEmail());
			preps.setInt(7, u.getRole().getRoleId());

			if (preps.executeUpdate() != 0)
				return 0;
			else
				return 1;

		} catch (SQLException e) {
			// Step 5: Perform any exception handling in an appropriate means
			e.printStackTrace();
			return 1;
		}

	}

	@Override
	public int update(User obj) {

		return 0;
	}

}