//EmployeeSearchServlet.java
package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
//jdbc api
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class EmployeeSearchServlet extends HttpServlet
{

  private static final String GET_EMPLOYEE_DETAILS_BY_NO="SELECT EMPNO,ENAME,JOB,SAL,DEPTNO FROM EMP WHERE EMPNO=?";

  public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
    PrintWriter pw=null;
	int eno=0;
	Connection con=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	ServletContext sc=null;
	ServletConfig cg=null;
	String driver=null,url=null,user=null,pwd=null;
	try{
    //get PrintWriter
	pw=res.getWriter();
	//set response content type
	res.setContentType("text/html");
	//read form data
    eno=Integer.parseInt(req.getParameter("eno"));
    //get Access to ServletConfig object
    sc=getServletContext();
    cg=getServletConfig();
    //read Servlet Init parameters
    driver=sc.getInitParameter("driver");
    url=sc.getInitParameter("url");
    user=sc.getInitParameter("dbuser");
    pwd=sc.getInitParameter("dbpwd");
    
	//register JDBC driver
	Class.forName(driver);
	//Establish the Connection
    con=DriverManager.getConnection(url,user,pwd);
	//create PreparedStatment having pre-compiled query
    ps=con.prepareStatement(GET_EMPLOYEE_DETAILS_BY_NO);	
	//set query param values
	ps.setInt(1,eno);
	//execute the query
	rs=ps.executeQuery();
	//process the ResultSet objects
	if(rs.next()){
		pw.println("<h1> Employee Details are::</h1>");
		pw.println("<b>Emp no::</b>"+rs.getInt(1)+"<br>");
		pw.println("<b>Emp Name::</b>"+rs.getString(2)+"<br>");
		pw.println("<b>Emp JOB::</b>"+rs.getString(3)+"<br>");
		pw.println("<b>Emp Sal::</b>"+rs.getFloat(4)+"<br>");
		pw.println("<b>Emp Deptno::</b>"+rs.getInt(5)+"<br>");
	}
	else{
	    pw.println("<h1 style='color:red'>Employee not found</h1>");
	}
	
	pw.println("<br><b> ServletContext obj class name ::</b>"+sc.getClass());
	pw.println("<br><b> ServletConfig obj class name ::</b>"+cg.getClass());
	
   }//try
   catch(SQLException se){       //known Exception
	   se.printStackTrace();
	   pw.println("<h1 style='color:red'>Internal DB problem</h1>");  
   }
   catch(ClassNotFoundException cnf){
	   cnf.printStackTrace();
	   pw.println("<h1 style='color:red'>Internal Problem</h1>");
   }
   catch(Exception e){
	   e.printStackTrace();
   }
   finally{
	   //close jdbc objects
	   try{
		   if(rs!=null)
			   rs.close();
		   }
		   catch(SQLException se){
			   se.printStackTrace();
		   }
		   try{
		   if(con!=null)
			   con.close();
		   }
		   catch(SQLException se){
			   se.printStackTrace();
		   }
		   //home hyperlink
           pw.println("<br> <br> <a href='input.html'>Back to Home Page</a>");
		   try{
		   if(pw!=null)
			   pw.close();
		   }
		   catch(Exception e){
			   e.printStackTrace();
		   }
   }//finally
}//doGet

  public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
    doGet(req,res);
  }//doPost(-,-)

  }//class