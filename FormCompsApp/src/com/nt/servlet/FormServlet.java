package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class FormServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
		String name=null,gender=null,ms=null,addrs=null,qlfy=null,clr=null;
		String[] crs=null;
		String[] hb=null;
		int age=0;
		PrintWriter pw=null;
		//get PrintWriter object
		pw=res.getWriter();
		//set response content type
		res.setContentType("text/html");
		//read form data
		name=req.getParameter("tname");
		age=Integer.parseInt(req.getParameter("tage"));
		gender=req.getParameter("gender");
		ms=req.getParameter("ms");
		addrs=req.getParameter("taddrs");
		qlfy=req.getParameter("qlfy");
	    crs=req.getParameterValues("crs");
		hb=req.getParameterValues("hb");
		clr=req.getParameter("clr");
		
		//write request processing logics
        if(gender.equalsIgnoreCase("M"))
        {
        	if(age<=5)
        		pw.println(name+"U r baby boy");
        	else if(age<=12)
        		pw.println(name+"U r small boy");
        	else if(age<=19)
        		pw.println(name+"U r teenage boy");
        	else if(age<=35)
        		pw.println(name+"U r young man");
        	else if(age<=50)
        		pw.println(name+"U r middle aged man");
        	else
        		pw.println(name+"U r Budda");
        }//if
        else if(gender.equalsIgnoreCase("F"))
        {
        	if(age<=5)
        		pw.println(name+"U r baby girl");
        	else if(age<=12)
        		pw.println(name+"U r small girl");
        	else if(age<=19)
        		pw.println(name+"U r teenage girl");
        	else if(age<=35)
        		pw.println(name+"U r young woman");
        	else if(age<=50)
        		pw.println(name+"U r middle aged woman");
        	else
        		pw.println(name+"U r old woman");
        }
        
        pw.println("<br>name="+name);
        pw.println("<br>age="+age);
        pw.println("<br>Gender="+gender);
        pw.println("<br>Marital Status="+ms);
        pw.println("<br>Address="+addrs);
        pw.println("<br>Qualification="+qlfy);
        pw.println("<br> Courses:"+Arrays.toString(crs));
        pw.println("<br> Hobies:"+Arrays.toString(hb));
        pw.println("<br> Color:"+clr);
        
        
        }//doGet
	
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
		doGet(req,res);
	}
}//class
