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

	// ���ݿ� �û��� ���� url
	private static String url = "jdbc:mysql://localhost:3306/memonoa";
	private static String user = "root";
	private static String password = "Temp@Win2015";
	
	private static Connection conn;
	
	// ʹ�� ���� ģʽ
	// Ĭ�Ϲ��캯��˽�л�  ��ʵ�ֵ���ģʽ�Ĺؼ�
	private DBConn(){}
	
	// ������ ������ �쳣�����׳����� ��������
	public static Connection getConnection() throws SQLException, ClassNotFoundException{
		
		if(conn == null || conn.isClosed()){
			// ������
			Class.forName("com.mysql.jdbc.Driver");

			// �������
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
