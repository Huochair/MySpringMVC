package com.hvcc.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * Servlet implementation class LoadDataServlet
 */
@WebServlet("/LoadDataServlet")
public class LoadDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoadDataServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/wanliwork", "hvcc", "hvcc");
			String sql = "select `classes`,`sum_number` from fenci ";
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			
			JSONArray array = new JSONArray();
			System.out.println(sql);
			while (rs.next()) {
				String keyString = rs.getString(1);
				int valer = rs.getInt(2);
				JSONObject obj = new JSONObject();
				obj.put("classes", keyString);
				obj.put("fav_count", valer);
				array.add(obj);
			}
			rs.close();
			pst.close();
			conn.close();
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			System.out.println(array);
			out.print(array);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
