/**
 * 
 */
package com.maeq.memon.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.maeq.memon.dao.UserDao;
import com.maeq.memon.model.User;

/**
 * @author maeq
 *
 */
public class UserService {
	
	private UserDao userdao = new UserDao();
	
	public ArrayList<User> getAllUser() throws ClassNotFoundException, SQLException{
		return userdao.getAllUser();
	}
	
	/**
	 * @param args
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		UserService us = new UserService();
		ArrayList<User> all = us.getAllUser();
		
		for(User u : all)
		{
			System.out.println(u.toString());
		}
	}

}
