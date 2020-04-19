package com.net.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CountryServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
		String country=null;
		//read form data
		country=req.getParameter("country");
		//general settings
		PrintWriter pw=res.getWriter();
		res.setContentType("text/html");
		//get Capital based on the country that is selected
		
		if(country.equals("0")) {
			pw.println("<h1 style='color:green;text-align:center'>Capital is Delhi</h1>");
		}
		else if(country.equals("1")) {
			pw.println("<h1 style='color:green;text-align:center'>Capital is Islambad</h1>");
		}
		else if(country.equals("2")) {
			pw.println("<h1 style='color:green;text-align:center'>Capital is Bejing</h1>");
		}
		else if(country.equals("3")) {
			pw.println("<h1 style='color:green;text-align:center'>Capital is WashingtonDC</h1>");
		}
		else if(country.equals("4")){
			pw.println("<h1 style='color:green;text-align:center'>Capital is Muscow</h1>");
		}
		
		pw.println("<center><h4 style='color:red'><a href='page.html'>Back to Homepage</a></h4></center>");
	    pw.close();
	}
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	doGet(req,res);
    }
}
