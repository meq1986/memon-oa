/**
 * 
 */
package com.maeq.memon.utils;

/**
 * @author Administrator
 *
 */
public class DBConn {

	// 使用 单例 模式
	private static DBConn dbconn;
	
	
	// 默认构造函数私有化  是实现单例模式的关键
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
