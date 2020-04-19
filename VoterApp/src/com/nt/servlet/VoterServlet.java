package com.nt.servlet;

import javax.servlet.http.HttpServlet;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class VoterServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
		PrintWriter pw=null;
		String name=null,tage=null;
		int age=0;
		//general settings
		pw=res.getWriter();
		res.setContentType("text/html");

		//get request parameter values(form data)
		name=req.getParameter("pname");
		tage=req.getParameter("page");
		age=Integer.parseInt(tage);//Types conversion from string to integer

		//request processing logic
		if(age>=18){
			pw.println("<h1 style='color:green;text-align:center'>Congrats!! Mr./Ms.\t"+name+"\tyou are Eligible for Vote</h1>");
		}
		else{
			pw.println("<h1 style='color:red;text-align:center'>Mr./Ms.\t"+name+"\tyou are not Eligible for Vote</h1>");
		}
		//add hyperlink
		pw.println("<center><a href='input.html'><img src='home.png'></a></center>");
		//close stream
		pw.close();
	}//doGet(-,-)
}//class(-,-)
