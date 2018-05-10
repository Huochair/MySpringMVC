package com.hvcc.dbs;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataBaseConnection {
	private static final String DBDRIVER = "org.gjt.mm.mysql.Driver";
	private static final String DBURL = "jdbc:mysql://localhost:3306/oasis";
	private static final String DBUSER = "root";
	private static final String DBPASSWORD = "root";
	private Connection conn;
	public DataBaseConnection() {
		// TODO Auto-generated constructor stub
	}
	public Connection getConnection() {
		try {
			if (conn==null||conn.isClosed()) {
				Class.forName(DBDRIVER);
				conn = DriverManager.getConnection(DBURL,DBUSER,DBPASSWORD);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return conn;
	}
	public void close() {
		try {
			if (conn!=null) {
				conn.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
}
