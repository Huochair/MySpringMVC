package com.hvcc.dbs;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataBaseConnection {
	private static final String DBDRIVER = "org.gjt.mm.mysql.Driver";
	private static final String DBURL = "jdbc:mysql://10.60.72.36:3306/wanliwork";
	private static final String DBUSER = "hvcc";
	private static final String DBPASSWORD = "hvcc";
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
