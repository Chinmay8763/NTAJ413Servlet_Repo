package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/firsturl")
public class FirstServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=null;
		String name=null,fname=null,gender=null;
		HttpSession ses=null;
		//get PrintWriter
		pw=res.getWriter();
		//set response Content-type
		res.setContentType("text/html");
		//read form1/req1 data
		name=req.getParameter("pname");
		fname=req.getParameter("fname");
		gender=req.getParameter("gender");
		//create Session Object and begin the Session
		ses=req.getSession(true);
		//ses.setMaxInactiveInterval(120);   //Session timeout after 2 mins
		//write form1/req1 data to Session obj as session attributes
		ses.setAttribute("name", name);
		ses.setAttribute("fname", fname);
		ses.setAttribute("gender", gender);
		
		//Generate form2 dynamically here
		pw.println(" <h1 style='color:red;text-align:center'> Provide Income Details </h1>");
		pw.println(" <form action='"+res.encodeURL("secondurl")+"' method='POST'> ");
		pw.println("<table border='1' bgcolor='cyan' align='center'>");
		pw.println("<tr><td> Income of this Year </td> <td><input type='text' name='income'> </td></tr>");
		pw.println("<tr> <td> Tax </td> <td> <input type='text' name='tax'> </td></tr>");
		pw.println("<tr> <td> <input type='submit' value='Submit'> </td> <td> <input type='reset' value='cancel'> </td> </tr>");
		pw.println("</table> </form>");
		pw.println("<br> Session id:::"+ses.getId());
		
		//close stream
		pw.close();
		
	}//doGet(-,-)

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}//doPost(-,-)

}//class
