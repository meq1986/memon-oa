/**
 * 
 */
package com.maeq.memon.utils;

/**
 * @author Administrator
 *
 */
public class DBConn {

	// ʹ�� ���� ģʽ
	private static DBConn dbconn;
	
	
	// Ĭ�Ϲ��캯��˽�л�  ��ʵ�ֵ���ģʽ�Ĺؼ�
	private DBConn(){}
	
	public static DBConn getInstance(){
		if(dbconn == null)
			dbconn = new DBConn();
		return dbconn;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("hello, dbconn.");
	}

}
