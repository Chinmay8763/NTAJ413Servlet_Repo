package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class TestServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	   
		PrintWriter pw=null;
		ServletConfig cg=null;
		ServletContext sc=null;
		pw=res.getWriter();
		res.setContentType("text/html");
		//get Access ServletContext object
		sc=getServletContext();
		pw.println("<br> db user context parameter value ::"+sc.getInitParameter("dbuser"));
	
		pw.println("<br><br><br> Details using ServletContext object <br><br>");
		pw.println("<br> Server Info ::"+sc.getServerInfo());
		pw.println("<br> Servlet api version ::"+sc.getMajorVersion()+"."+sc.getMinorVersion());
		pw.println("<br> MIME type of input.html is:"+sc.getMimeType("input.html"));
		pw.println("<br> Path of the input.html ::"+sc.getRealPath("input.html"));
		pw.println("<br> Path of the web root folder ::"+sc.getRealPath("/"));
		pw.println("<br> Context path of the web application::"+sc.getContextPath());
		
	}	
	
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

	}

}
