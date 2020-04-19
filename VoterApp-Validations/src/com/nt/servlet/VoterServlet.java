package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VoterServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=null;
		String name=null,tage=null,vstatus=null;
		int age=0;
		//get PrintWriter
		pw=res.getWriter();
		//set response content type
		res.setContentType("text/html");
		//read form data from the form components of form page
		name=req.getParameter("pname");
		tage=req.getParameter("page");
		
		vstatus=req.getParameter("vflag");//get ClientSide validation status
		if(vstatus.equals("no"))//if ClientSide validations are not done
		{
			//Server form validation logic
			if(name.equals("") || name==null || name.length()==0) //required rule
			{
				pw.println("<font color=red> Person name is mandatory</font>");
				return;
			}
			if(tage.equals("") || tage==null || tage.length()==0)
			{
				pw.println("<font color=red> Person age is mandatory</font>");
				return;
			}
			else //to check wheather age is numeric value or not
			{
				try {
					//convert given age number to numeric value.
					age=Integer.parseInt(tage);
				}
				catch(NumberFormatException nfe)
				{
					pw.println("<font color=red>Age must be numeric value</font>");
					return;
				}
			}//else
			System.out.println("Server side validations are completed");
		}//if
		else {
			//when client side validations are done
			age=Integer.parseInt(tage);
		}
		
		//write request processing logic/B.logic
		if(age>=18)
			pw.println("<h1><font color='green'>"+name+"You are eligible to vote</fornt></h1>");
		else
			pw.println("<h1><font color='red'>"+name+"You are not eligible to vote</fornt></h1>");
		//add graphical hyperlink
		pw.println("<br><br><a href='input.html' alt='Back to Homepage'><img src='home.png' width='100' height='100'></a>");
		
		//close stream
		pw.close();
		}//doGet(-,-)

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	{
    		System.out.println("VoterServlet:doPost(-,-)");
    		doGet(req,res);
    	}
    }//doPost(-,-)
}//class
