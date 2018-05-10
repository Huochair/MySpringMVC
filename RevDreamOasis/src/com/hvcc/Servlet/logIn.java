package com.hvcc.Servlet;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.View;

import com.hvcc.dbs.DataBaseConnection;
import com.hvcc.impl.ProductIPDAOimpl;
import com.hvcc.vo.userDemo;

/**
 * Servlet implementation class logIn
 */
@WebServlet("/login")
public class logIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DataBaseConnection dbc = new DataBaseConnection();
	private ProductIPDAOimpl dao= new ProductIPDAOimpl(dbc);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public logIn() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Enumeration enu=request.getParameterNames();
		while(enu.hasMoreElements()){
		String paraName=(String)enu.nextElement();
		System.out.println(paraName+": "+request.getParameter(paraName));
		} 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Enumeration enu=request.getParameterNames();
		while(enu.hasMoreElements()){
		String paraName=(String)enu.nextElement();
		System.out.println(paraName+": "+request.getParameter(paraName));
		}
		userDemo user = new userDemo(request.getParameter("username"),request.getParameter("password"));
		System.out.println(user.toString());
		System.out.println(dao.verify(user)!=0);
		if (dao.verify(user)!=0) {
			response.sendRedirect("index.jsp");
		}else {		
			response.sendRedirect("login.jsp");
		}
	}
}
