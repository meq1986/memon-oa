/**
 * 
 */
package com.maeq.memon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.maeq.memon.model.User;
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
	
	public int insertUser(String username,String password,String email, String mobilephone, String createdate, int status) throws SQLException, ClassNotFoundException
	{
		// 自定义异常 比如 用户名 密码为空 用户名重复等
		Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBConn.getConnection();
            ps = con.prepareStatement("insert into t_user (username,password,email,mobilephone,createdate,status) values (?,?,?,?,?,?)");
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, email);
            ps.setString(4, mobilephone);
            ps.setString(5, createdate);
            ps.setInt(6, status);
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
	
	// 类型选取ArrayList，不是LinkList
	// 因为这里取数据，主要在于展示，没有插入，删除操作
	// 获取全部用户  慎用 数据量级大的时候 会影响性能
	public ArrayList<User> getAllUser() throws ClassNotFoundException, SQLException{
		ArrayList<User> res = new ArrayList<User>();
		
		Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBConn.getConnection();
            ps = con.prepareStatement("select * from t_user");
            rs = ps.executeQuery();
            
            while(rs.next()){
            	res.add(new User(rs.getInt("id"),rs.getString("username"),
            			rs.getString("password"),rs.getString("email"),
            			rs.getString("mobilephone"),rs.getString("createdate"),
            			rs.getInt("status")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	DBConn.closeConnection();
        }
        return res;
	}
	
	/**
	 * @param args
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		UserDao userdao = new UserDao();
		
		ArrayList<User> all = userdao.getAllUser();
		
		for(User u : all)
		{
			System.out.println(u.toString());
		}

	}

}
