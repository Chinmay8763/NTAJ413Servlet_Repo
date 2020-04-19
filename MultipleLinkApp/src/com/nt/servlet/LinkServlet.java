package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LinkServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=null;
		String link=null;
		Locale locales[]=null;
		//get PrintWriter
		pw=res.getWriter();
		//set response content trype
		res.setContentType("text/html");
		//read "s1" req parameter value to know the hyperlink that is clicked
		link=req.getParameter("s1");
		if(link.equalsIgnoreCase("link1")) { //all languages
			//get all available locales
			locales=Locale.getAvailableLocales();
			for(Locale lc:locales) {
				pw.println("<h1 style='color:green;text-align:center'>"+lc.getDisplayLanguage()+"</h1>"+"<br>");
			}
		}//if
		else if(link.equalsIgnoreCase("link2")) {
			//get all available locales
			locales=Locale.getAvailableLocales();
			for(Locale lc:locales) {
				pw.println("<h1 style='color:green;text-align:center'>"+lc.getDisplayCountry()+"</h1>"+"<br>");
			}//for
			}//else
		else {
			pw.println("<h1 style='color:green;text-align:center'>Date and Time "+new Date()+"</h1>"+"<br>");
		}
		//add hyperlink
		pw.println("<h1 style='color:red;text-align:center'><a href='page.html'>Back to HomePage</a></h1>");
		}//doGet(-,-)

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req,res);
	}
}//class
