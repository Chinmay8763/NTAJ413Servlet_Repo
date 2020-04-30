package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/secondurl")
public class SecondServlet extends HttpServlet {
	private static final String INSERT_COOKIE_PERSON_QUERY="INSERT INTO COOKIE_PERSON_INFO VALUES(COOKIE_PID.NEXTVAL,?,?,?,?,?)";
	@Resource(name="DsJndi")
	private DataSource ds;
	
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=null;
		float income=0.0f,tax=0.0f;
		Cookie cookies[]=null;
		String pname=null,fname=null,gender=null;
		Connection con=null;
		PreparedStatement ps=null;
		int count=0;
		//get PrintWriter
		pw=res.getWriter();
		//set ContentType
		res.setContentType("text/html");
		//read form2/req2 data
		income=Float.parseFloat(req.getParameter("income"));
		tax=Float.parseFloat(req.getParameter("tax"));
		//read form1/req1 data as cookies value (Session tracking)
		cookies=req.getCookies();
		if(cookies!=null) {
			pname=cookies[0].getValue();
			fname=cookies[1].getValue();
			gender=cookies[2].getValue();
		}
		
		//write form1/req1 data to DB table as record
		try {
			//get Pooled JDBC Connection
			con=ds.getConnection();
			//create PreparedStatement object
			ps=con.prepareStatement(INSERT_COOKIE_PERSON_QUERY);
			//set values to QueryParams
			ps.setString(1, pname);
			ps.setString(2, fname);
			ps.setString(3, gender);
			ps.setFloat(4, income);
            ps.setFloat(5, tax);
            //execute the query
            count=ps.executeUpdate();
            //process the result
            if(count==0)
            	pw.println("<h1 style='color:red;text-align:center'>Registration Failed </h1>");
            else
            	pw.println("<h1 style='color:green;text-align:center'>Registration Succeeded </h1>");
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
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
					con.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
		}//finally
		
		//Diplay form1/req1 and form2/req2 data dynamically
		pw.println("<br> <b> <h2> Form1/Req1 Data:::"+pname+"....."+fname+"....."+gender+" </h2> </b>");
		pw.println("<br> <b><h2> Form2/Req2 Data:::"+income+"....."+tax+" </h2></b>");
		
		//hyperlink
		pw.println("<br> <a href='input.html'> HomePage </a>");
		
		//close stream
		pw.close();
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
