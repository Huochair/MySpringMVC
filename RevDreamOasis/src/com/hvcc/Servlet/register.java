package com.hvcc.Servlet;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hvcc.dbs.DataBaseConnection;
import com.hvcc.impl.ProductIPDAOimpl;
import com.hvcc.vo.userDemo;

/**
 * Servlet implementation class register
 */
@WebServlet("/register")
public class register extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private DataBaseConnection dbc = new DataBaseConnection();
	private ProductIPDAOimpl dao= new ProductIPDAOimpl(dbc);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Enumeration enu=request.getParameterNames();
		Map< String, String> map = new HashMap<String, String>();
		userDemo user = new userDemo();
		try {
			while(enu.hasMoreElements()){
				String paraName=(String)enu.nextElement();
				map.put(paraName, request.getParameter(paraName));
				} 
			 for(Map.Entry<String, String> entry : map.entrySet()){  
			        String key = entry.getKey();  
			        String value = entry.getValue();  
			        System.out.println(key + ":\t" + value);  
			    }  
			 user.setUserName(map.get("username")); 
			 user.setPassword(map.get("password"));
			 user.setEmail(map.get("email"));
			 user.setPhone(map.get("phone"));
			 dao.setUser(user);
			 response.sendRedirect("login.jsp");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			response.sendRedirect("register.jsp");
		}
	}

}
