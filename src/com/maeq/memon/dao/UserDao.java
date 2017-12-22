/**
 * 
 */
package com.maeq.memon.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.maeq.memon.utils.DBConn;

/**
 * @author Administrator
 *
 */
public class UserDao {
	private Connection conn;
	
	public String getPassByName(String username) throws SQLException{
		String password = "";
		Statement statement = null;
		ResultSet result = null;
		
		conn = DBConn.getConnection();
		statement = conn.createStatement();
		String sql = "select password from t_user where username = '" + username +"'";
		result = statement.executeQuery(sql);
		
		while(result.next())
		{
			password = result.getString(1);
		}
		
		DBConn.closeConnection();
		
		return password;
	}
	
	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		UserDao userdao = new UserDao();
		String password = userdao.getPassByName("maeq");
		
		System.out.println(password);

	}

}
