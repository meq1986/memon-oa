/**
 * 
 */
package com.maeq.memon.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Administrator
 *
 */
public class DBConn {

	// 使用 单例 模式
	private static DBConn dbconn;
	private static Connection conn;
	
	
	// 默认构造函数私有化  是实现单例模式的关键
	private DBConn(){}
	
	public static DBConn getInstance(){
		if(dbconn == null)
			dbconn = new DBConn();
		return dbconn;
	}
	
	public static Connection getConnection() throws SQLException{
		
		// 数据库 用户名 密码 url
		String url = "jdbc:mysql://localhost:3306/memonoa";
		String user = "root";
		String password = "Temp@Win2015";
		
		if(conn == null || conn.isClosed()){
			// 载入类
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			// 获得连接
			conn = DriverManager.getConnection(url,user,password);
		}
		
		return conn;
	}
	
	public static void closeConnection() throws SQLException{
		if(conn!=null && !conn.isClosed()){
			conn.close();
		}
	}
	
	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		DBConn.getConnection();
		DBConn.closeConnection();
		
	}

}
