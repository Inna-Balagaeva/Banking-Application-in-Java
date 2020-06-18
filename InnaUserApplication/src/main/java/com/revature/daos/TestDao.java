package com.revature.daos;

import com.revature.models.Role;
import com.revature.models.User;

public class TestDao {

	public static void main(String[] args) {
//		// find all users:
		UserDao udao = new UserDao();
//		System.out.println(udao.findAll());
//		// find all accounts:
//		AccountDao adao = new AccountDao();
//		System.out.println(adao.findAll());
//		// create user:
//		Role ro = new Role(2, "admin");
//		User u = new User(766, "cd", "123", "aa", "bb", "ff", ro);
//		System.out.println(u + "user created!");
//		int x = udao.create(u);
//		System.out.println("user created: " + u + ". code successful: " + x);
		//findById user:
	    System.out.println(udao.findById(111));

	}
}
