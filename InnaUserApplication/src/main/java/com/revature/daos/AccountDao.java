//you can use a join in the sql statement if you want 
//to set the role name and role id at the same time 

package com.revature.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Account;
import com.revature.models.AccountStatus;
import com.revature.models.AccountType;
import com.revature.models.Role;
import com.revature.models.User;
import com.revature.utils.ConnectionFactory;

// THE DAO : Data Access Object
// It's a structural design pattern that allows us to isolate the application/
// a.k.a business logic from the persistence layer (relational database) by using
// an API.....

// The JDBC is the API that gives us Classes and Objects to interact with our persistance layer.
// we use a DAO implementation to perfrom CRUD operations against the DB
// and assign properties to the Object instantiated for this class

// we are creating a concrete DAO to demo functionality and the 5 basic steps of JDBC connection
public class AccountDao implements DAO<Account, Integer> {
	@Override
	public List<Account> findAll() {
		List<Account> accounts = new ArrayList<Account>();
		try {
			Connection conn = ConnectionFactory.getConnection();
			if (conn != null) {
				System.out.println("Congrats you're connected!");
			} else {
				System.out.println("Connection failed");
			}
///SELECT * FROM ib_account LEFT JOIN ib_role ON ib_user2.ref_role_id = ib_role.role_id
			
			String sql = "SELECT * FROM ib_account A JOIN ib_account_type T ON A.ref_account_type_id = T.account_type_id" +
			" JOIN ib_account_status S ON A.ref_account_status_id = S.account_status_id";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs != null) {
				System.out.println("RS not null");
			}
			while (rs.next()) {
				int id = rs.getInt(1);
				double balance = rs.getDouble(2);
				int refUserId = rs.getInt(3);
				int accountTypeId = rs.getInt("ref_account_type_id");
				String accountTypeName = rs.getString("account_type_name");// from table account_type
				int accountStatusId = rs.getInt("ref_account_status_id");
				String accountStatusName = rs.getString("account_status_name");// from table account_status

				AccountType accType = new AccountType(accountTypeId, accountTypeName);
				AccountStatus accStatus = new AccountStatus(accountStatusId, accountStatusName);
				Account a = new Account(id, balance, refUserId, accType, accStatus);
				accounts.add(a);
			} // while
			conn.close();
		} catch (SQLException e) {// If something goes wrong, return an empty list
			e.printStackTrace();
			// return new ArrayList<>();
		}
		return accounts;
	}
	
	
	@Override
	public int create(Account obj) {

		return 0;
	}



	@Override
	public Account findById(Integer id) {

		
		return null;
	}

	@Override
	public int update(Account obj) {

		return 0;
	}

	
	public int withdraw(Account obj) {
	
		return 0;
	}
	
	public int deposit (Account obj) {
	
		return 0;
	}
	
}
