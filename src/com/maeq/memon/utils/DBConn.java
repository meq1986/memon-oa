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

	// ʹ�� ���� ģʽ
	private static DBConn dbconn;
	private static Connection conn;
	
	
	// Ĭ�Ϲ��캯��˽�л�  ��ʵ�ֵ���ģʽ�Ĺؼ�
	private DBConn(){}
	
	public static DBConn getInstance(){
		if(dbconn == null)
			dbconn = new DBConn();
		return dbconn;
	}
	
	public static Connection getConnection() throws SQLException{
		
		// ���ݿ� �û��� ���� url
		String url = "jdbc:mysql://localhost:3306/memonoa";
		String user = "root";
		String password = "Temp@Win2015";
		
		if(conn == null || conn.isClosed()){
			// ������
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			// �������
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
