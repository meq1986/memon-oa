/**
 * 
 */
package com.maeq.memon.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author maeq
 *
 */
public class DBConn {

	// 数据库 用户名 密码 url
	private static String url = "jdbc:mysql://localhost:3306/memonoa";
	private static String user = "root";
	private static String password = "Temp@Win2015";
	
	private static Connection conn;
	
	// 使用 单例 模式
	// 默认构造函数私有化  是实现单例模式的关键
	private DBConn(){}
	
	// 工具类 公共类 异常采用抛出策略 本身不处理
	public static Connection getConnection() throws SQLException, ClassNotFoundException{
		
		if(conn == null || conn.isClosed()){
			// 载入类
			Class.forName("com.mysql.jdbc.Driver");

			// 获得连接
			conn = DriverManager.getConnection(url,user,password);
			//System.out.println("DriverManager.getConnection(url,user,password)");
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
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		DBConn.getConnection();
		DBConn.closeConnection();
		
	}

}
