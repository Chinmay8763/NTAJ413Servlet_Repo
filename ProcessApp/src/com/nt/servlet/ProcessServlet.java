package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProcessServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=null;
		String comp=null;
		int val1=0,val2=0;
		//get PrintWriter object
		pw=res.getWriter();
		//set response content type
		res.setContentType("text/html");
		//read s1 req param value to know the component that is used to send the request
		comp=req.getParameter("s1");
		
		//write b.logic
		if(comp.equals("link1")) {//System date
			pw.println("Date and Time"+new Date());
		}
		else if(comp.equals("link2")) {//System properties
			pw.println("System Properties"+System.getProperties());
		}
		else if(comp.equals("add")) {
			//read form data
			val1=Integer.parseInt(req.getParameter("t1"));
			val2=Integer.parseInt(req.getParameter("t2"));
			pw.println("Addition::"+(val1+val2));      //Perform Addition
		}
		else if(comp.equals("sub")) {
			//read form data
			val1=Integer.parseInt(req.getParameter("t1"));
			val2=Integer.parseInt(req.getParameter("t2"));
			pw.println("Subtraction::"+(val1-val2));   //Perform Subtraction
		}
		else if(comp.equals("mul")) {
			//read for data
			val1=Integer.parseInt(req.getParameter("t1"));
			val2=Integer.parseInt(req.getParameter("t2"));
			pw.println("Multiplication::"+(val1*val2));   //Perform Multiplication
		}
		else {
			//read form data
			val1=Integer.parseInt(req.getParameter("t1"));
			val2=Integer.parseInt(req.getParameter("t2"));
			pw.println("Division::"+(val1/val2));           //Perform Division
		}
		//add hyperlink
		pw.println("<a href='form.html'>Home</a>");
	}//doGet(-,-)
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req,res);
	}//doPost(-,-)
}//class
