package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class TestServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	   
		PrintWriter pw=null;
		ServletConfig cg=null;
		pw=res.getWriter();
		res.setContentType("text/html");
		//get Access ServletConfig object
		cg=getServletConfig();
		pw.println("<br> db user init parameter value ::"+cg.getInitParameter("dbuser"));
	
	}	
	
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

	}

}
