package com.nt.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class MarriageServlet extends HttpServlet{
	@Override
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
		PrintWriter pw=null;
		String name=null,tage=null,gen=null;
		int age=0;
		//general settings
		pw=res.getWriter();
		res.setContentType("text/html");

		//get request parameter values(read form data)
		name=req.getParameter("pname");
		tage=req.getParameter("page");
		age=Integer.parseInt(tage);//Types conversion from string to integer
		gen=req.getParameter("gender");

		//write b.logic/request processing logic
	if(gen.equalsIgnoreCase("M")) 	//for Male	
		if(age>=21){
			pw.println("<h1 style='color:green;text-align:center'>Congrats!! Mr.\t"+name+"\tyou are Eligible for Marriage</h1>");
		}
		else {
			pw.println("<h1 style='color:red;text-align:center'>Mr.\t"+name+"\tyou are not Eligible for marriage</h1>");
		}
	else { //for Female
		if(age>=18) {
			pw.println("<h1 style='color:pink;text-align:center'>Congrats Miss.\t"+name+"\tyou are eligible for Marriage</h1>");
		}
		else {
			pw.println("<h1 style='color:red;text-align:center'>Miss.\t"+name+"\tyou are not eligible for Marriage</h1>");
		     }
		}
	
		//add hyperlink
		pw.println("<center><a href='input.html'><img src='home.png' height='50' width='50'></a></center>");
		//close stream
		pw.close();
	}//doPost(-,-)
}//class(-,-)
