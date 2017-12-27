package com.maeq.memon.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.maeq.memon.dao.UserDao;

public class LoginServlet extends HttpServlet {
	
	private static final Logger LOGGER = Logger.getLogger(LoginServlet.class);
	
	private static UserDao userdao = new UserDao();
	
	/**
	 * Constructor of the object.
	 */
	public LoginServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 获取request 参数 用户名 密码
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		System.out.println("email = " + email);
		System.out.println("password = " + password);
		
		LOGGER.info("email = " + email + ",password = " + password);
		
		String flag = "SUCCESS";
		
		if(!email.equals("admin") || !password.equals("admin"))
		{
			flag = "FAIL";
		}
		
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method. login is " + flag);
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 获取request 参数 用户名 密码
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		//boolean ok = isConnect();
		
		LOGGER.info("email = " + email + ",password = " + password);
		System.out.println("email = " + email);
		System.out.println("password = " + password);
		
		String db_pass = "";
		try {
			db_pass = userdao.getPassByName(email);
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("db_pass = " + db_pass);
		
		String flag = "SUCCESS";
		
		if(!password.equals(db_pass))
		{
			flag = "FAIL";
		}
		
/*		if(!email.equals("admin") || !password.equals("admin"))
		{
			flag = "FAIL";
		}*/
		
		
		if(flag.equals("SUCCESS")){
			System.out.println("flag.equals(SUCCESS)");
			response.sendRedirect("/memon-oa/production/index.html");
			return;
		}
		
/*		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method. login is " + flag );
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();*/
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

	private boolean isConnect()
	{
		boolean ok = false;
		
		String url = "jdbc:mysql://localhost:3306/memonoa";
		String user = "root";
		String password = "Temp@Win2015";
		
		Connection conn = null;
		Statement statement = null;
		ResultSet result = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			conn = DriverManager.getConnection(url,user,password);
			
			if(!conn.isClosed()){
				ok = true;
			}
			
			statement = conn.createStatement();
			result = statement.executeQuery("SELECT * FROM T_USER");
			if(!result.wasNull())
			{
				ok = true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ok;
	}
	
}
