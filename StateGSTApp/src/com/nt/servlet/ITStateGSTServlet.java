package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/sgsturl")
public class ITStateGSTServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String pname=null,company=null,ptype=null;
		float cost=0.0f;
		PrintWriter pw=null;
		float sgst=0.0f;
		ServletContext sc1=null,sc2=null;
		RequestDispatcher rd=null;
		//read form data
		pw=res.getWriter();
		res.setContentType("text/html");
		//read form data
		pname=req.getParameter("pname");
		company=req.getParameter("company");
		ptype=req.getParameter("ptype");
		cost=Float.parseFloat(req.getParameter("cost"));
		//calculate State GST
		if(ptype.equalsIgnoreCase("product"))
			sgst=cost*0.12f;
		else if(ptype.equalsIgnoreCase("service"))
			sgst=cost*0.1f;
		else if(ptype.equalsIgnoreCase("startup"))
			sgst=cost*0.03f;
		
		//display details
		pw.println("<h1 style='color:red;text-align:center'>GST Info</h1>");
		pw.println(" <br> <b> Project Name::"+pname+"</b>");
		pw.println(" <br> <b> Project Company::"+company+"</b>");
		pw.println(" <br> <b> Project Type::"+ptype+"</b>");
		pw.println(" <br> <b> Cost::"+cost+"</b>");
		pw.println(" <br> <b> <i>State GST::"+sgst+"</i></b>");
		
		
		//Communicate with Destination servlet of CentralGSTApp using Cross Context Communication
		   //get ServletContext object of current web application
		sc1=getServletContext();
		  //get ForeignContext object of CentrlGSTApp
		sc2=sc1.getContext("/CentralGSTApp");
		  //get RequestDispatcher object pointing to ITCentralGSTServlet of CentralGSTApp
		rd=sc2.getRequestDispatcher("/cgsturl");
		rd.include(req,res);
		
		//add hyperlink
		pw.println("<br> <br> <a href='input.html'>Home</a>");
		
		//close PrintWriter
		pw.close();
		
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
