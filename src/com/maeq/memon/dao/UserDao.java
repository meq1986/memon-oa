/**
 * 
 */
package com.maeq.memon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
	
	public String getPassByName(String username) throws SQLException, ClassNotFoundException{
		String password = "";
		Statement statement = null;
		ResultSet result = null;
		
		conn = DBConn.getConnection();
		statement = conn.createStatement();
		String sql = "select password from t_user where username = '" + username +"'";
		result = statement.executeQuery(sql);
		
		if(result.next())
		{
			password = result.getString(1);
		}
		
		DBConn.closeConnection();
		
		return password;
	}
	
	public int insertUser(String username,String password) throws SQLException, ClassNotFoundException
	{
		// 自定义异常 比如 用户名 密码为空 用户名重复等
		Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBConn.getConnection();
            ps = con.prepareStatement("insert into t_user (username,password) values (?,?)");
            ps.setString(1, username);
            ps.setString(2, password);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	DBConn.closeConnection();
        }
        return -1;
	}
	
	public int updateUserById(int id,String username,String password) throws SQLException, ClassNotFoundException
	{
		Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBConn.getConnection();
            ps = con.prepareStatement("update t_user set username = ? ,password=? where id=?");
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setInt(3, id);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	DBConn.closeConnection();
        }
        return -1;
	}
	
	public int deleteUserById(int id) throws SQLException, ClassNotFoundException{
		Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBConn.getConnection();
            ps = con.prepareStatement("delete from t_user where id=?");
            ps.setInt(1, id);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	DBConn.closeConnection();
        }
        return -1;
	}
	
	/**
	 * @param args
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		UserDao userdao = new UserDao();
		String password = userdao.getPassByName("maeq");
		
		try {
			userdao.insertUser("hahaha", "12323123");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		userdao.updateUserById(4, "car", "wawa");
		
		userdao.deleteUserById(6);
		
		System.out.println(password);

	}

}
