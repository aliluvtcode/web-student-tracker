package com.luv2code.web.jdbc;



import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

//import com.mysql.jdbc.Connection;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Define datasource/connection pool for resource injection„Â„ „Â„
	@Resource(name="jdbc/company")// the name here excatly the same name in the xml (jdbc/web_student_tracker")
	private DataSource dataSource;
	
	
	
	
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
//	      try { here is to check if the driver are working or not nigga
//	  		Class.forName("com.mysql.jdbc.Driver");
//	  		System.out.println("everything works");
//	  	} catch (ClassNotFoundException e) {
//	  	System.out.println("worke4df"+e);
//	  	}
	// step 1: set up the printWriter
		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");
		
	// step 2: Get the connection to the database 
		Connection myConn;
		Statement myStmt;
		ResultSet myRs;
		try {
			myConn= dataSource.getConnection();
			
	// step 3: Create a SQL statements
		String sql="select * from dept";
		myStmt= myConn.createStatement();
		
	// step 4: Execute SQL query 
		myRs=myStmt.executeQuery(sql);
		
	// step 5: Process the result set 	
		while(myRs.next()) {
			String email=myRs.getString(1);
			String my = myRs.getString(2);
			String mys = myRs.getString(3);
			out.println(email + " "+my+" "+mys);
		}
		}catch(Exception exc) {
			
			exc.printStackTrace();
		}
		
	}

}
