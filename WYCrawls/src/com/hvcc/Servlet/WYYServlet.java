package com.hvcc.Servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hvcc.dbs.DataBaseConnection;
import com.hvcc.impl.ProductIPDAOimpl;
import com.hvcc.vo.SongInfor;

/**
 * Servlet implementation class WYYServlet
 */
@WebServlet("/WYYServlet")
public class WYYServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static DataBaseConnection dbc = new DataBaseConnection();
	private static ProductIPDAOimpl dao= new ProductIPDAOimpl(dbc);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WYYServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//http://localhost:8080/WangyiyunCrawls/index.jsp
		List<SongInfor> sInfors = new ArrayList<SongInfor>();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		String src = request.getParameter("IWannaSay");
		System.out.println(src);
		char[] srcs = src.toCharArray();
		for (char c : srcs) {
					SongInfor xList;
					try {
						xList = dao.getSongList(c);
						sInfors.add(xList);
					} catch (Exception e) {
					}
		}
		System.out.println(sInfors.toString());
		HttpSession session = request.getSession();
		session.setAttribute("sInfor1", sInfors);
		session.setAttribute("src", src);
		response.sendRedirect("songList/MyJsp.jsp");
	}

}
