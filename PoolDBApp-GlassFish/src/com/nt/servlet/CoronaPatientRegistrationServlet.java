package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;



public class CoronaPatientRegistrationServlet extends HttpServlet {
   
	private static final String INSERT_CORONA_QUERY="INSERT INTO CORONA_REGISTRATION VALUES(CORONA_PATID_SEQUENCE.NEXTVAL,?,?,?,?,?)";
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=null;
		String patName=null,patAddrs=null,gender=null,stage=null;
		int age=0;
		Connection con=null;
		PreparedStatement ps=null;
		int count=0;
		//get PrintWriter
		pw=res.getWriter();
		//set response content type
		res.setContentType("text/html");
		//read form data
		patName=req.getParameter("patName");
		patAddrs=req.getParameter("patAddrs");
		age=Integer.parseInt(req.getParameter("patAge"));
		gender=req.getParameter("gender");
		stage=req.getParameter("stage");
		try {
			//get Pooled JDBC conn object
			con=getPooledConnection();                      //calling method
			//create JDBC PreapreStatement obj
			ps=con.prepareStatement(INSERT_CORONA_QUERY);
			//set values to Query params
			ps.setString(1, patName);
			ps.setString(2, patAddrs);
			ps.setInt(3, age);
			ps.setString(4, gender);
			ps.setString(5, stage);
			//execute the query
			count=ps.executeUpdate();
			//process the result
			if(count==0)
				pw.println("<h1 style='color:red;text-align:center'>Registration Failed</h1>");
			else
				pw.println("<h1 style='color:green;text-align:center'>Registration Successful</h1>");
		}
		catch(SQLException se) {
			se.printStackTrace();
			pw.println("<h1 style='color:red;text-align:center'>Registration Failed</h1>");
		}
		catch(Exception e) {
			e.printStackTrace();
			pw.println("<h1 style='color:red;text-align:center'>Unknown Problem</h1>");
		}
		finally {
			//close JDBC objects
			try {
				if(ps!=null)
					ps.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(con!=null)
					con.close();    //releases the JDBC connection objects back to jdbc connection pool 
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			pw.println("<h1><a href='register.html'>Home</a></h1>");
			try {
				if(pw!=null)
					pw.close();     
			}
			catch(Exception e) {
				e.printStackTrace();
			}		
		}//finally
	}//doGet
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}//doPost
	
	private Connection getPooledConnection() throws Exception {
		InitialContext ic=null;
		DataSource ds=null;
		Connection con=null;
		//create InitialContext object
		ic=new InitialContext();                        //establish conn between servlet and Jndi registry of Glassfish server 
		//get DataSource object through lookup operation
		ds=(DataSource)ic.lookup("DsJndi");  //search & get DataSource obj from Jndiregistry by submitting name DsJndi
		//get Pooled JDBC con object 
		con=ds.getConnection();
		return con;
	
	}//getPooledConnection()

}//class
