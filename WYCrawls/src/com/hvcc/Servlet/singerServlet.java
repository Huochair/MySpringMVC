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

import com.alibaba.fastjson.JSONObject;

/**
 * Servlet implementation class LoadDataFirstServlet
 */
@WebServlet("/LoadDataFirstServlet")
public class singerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public singerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				// 开始进行数据处理。
				// 连接数据库，并读取到想要的数据
//		System.out.println("-----------------------------------");
				try {
					Class.forName("org.gjt.mm.mysql.Driver");
					Connection conn = DriverManager.getConnection(
							"jdbc:mysql://localhost:3306/wanliwork", "hvcc", "hvcc");
//					String sql = "SELECT userid,COUNT(product_id) FROM product_fav GROUP BY userid";
//					PreparedStatement pst = conn.prepareStatement(sql);
//					ResultSet rs = pst.executeQuery();
//
//					JSONArray array = new JSONArray();
//
//					while (rs.next()) {
//						JSONObject obj = new JSONObject();
//						obj.put("userid", rs.getString(1));
//						obj.put("fav_count", rs.getInt(2));
//						array.add(obj);
//					}
					String sql = "SELECT writer,SUM(comment_number) FROM songinfor GROUP BY writer";
					PreparedStatement pst = conn.prepareStatement(sql);
					ResultSet rs = pst.executeQuery();
					JSONObject array = new JSONObject();
					System.out.println(sql);
					while (rs.next()) {
						String keyString = rs.getString(1);
						int valer = rs.getInt(2);
						if (valer!=0) {
							JSONObject obj = new JSONObject();
							array.put(keyString,valer);
							
						}
					}
					System.out.println("111111111111111111111111111111111111");
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
