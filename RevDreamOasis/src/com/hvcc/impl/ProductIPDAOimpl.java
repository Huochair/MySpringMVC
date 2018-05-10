package com.hvcc.impl;

import com.hvcc.dbs.DataBaseConnection;
import com.hvcc.vo.userDemo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class ProductIPDAOimpl {
	DataBaseConnection dbc = null;

	public ProductIPDAOimpl(DataBaseConnection dbc) {
		this.dbc = dbc;
		// TODO Auto-generated constructor stub
	}

	public Boolean setUser(userDemo user) {
		System.out.println(verify(user) == 0);
		if (verify(user) == 0) {
			try {
				
				String sql = "INSERT INTO userdemo(username,password) VALUES('"+user.getUserName()+"','"+user.getPassword()+"')";
				PreparedStatement pst = dbc.getConnection().prepareStatement(sql);
				System.out.println(sql);
				pst.executeUpdate();
				System.out.println("加载——————————————————————————————————————————");
				if (user.getRemarks() != null) {
					//UPDATE tbl_name SET 字段名称=值|exp|DEFAULT[WHERE 条件];
					sql="UPDATE userdemo SET remarks='"+user.getRemarks()+"' WHERE username='"+user.getUserName()+"'";
					pst = dbc.getConnection().prepareStatement(sql);
					pst.executeUpdate();
				}
				if (user.getEmail()!=null) {
					sql="UPDATE userdemo SET email= '"+user.getEmail()+"' WHERE username='"+user.getUserName()+"'";
//					sql = "INSERT INTO userdemo(email) VALUES('"+user.getEmail()+"')";
					pst = dbc.getConnection().prepareStatement(sql);
					pst.executeUpdate();
				}
				if (user.getPhone()!=null) {
					sql="UPDATE userdemo SET phone='"+user.getPhone()+"' WHERE username='"+user.getUserName()+"'";
//					sql = "INSERT INTO userdemo(phone) VALUES('"+user.getPhone()+"')";
					pst = dbc.getConnection().prepareStatement(sql);
					pst.executeUpdate();
				}
				pst.close();

			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("数据库加载错误");
				return false;
			}
		}
		return true;
	}
	public int verify(userDemo user) {
		String sql = "SELECT `id` FROM `userDemo` WHERE `username` = ? AND `password` = ?";
		try {
			PreparedStatement pst = dbc.getConnection().prepareStatement(sql);
			pst.setString(1, user.getUserName());
			pst.setString(2, user.getPassword());
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				int id = rs.getInt(1);
				rs.close();
				pst.close();
				return id;
			}
		} catch (SQLException e) {
			System.out.println("连接失败");
		}
		return 0;
	}

	public userDemo getUser(int ID) {
		userDemo vo = null;
		String sql = "SELECT id,username,remarks FROM `userDemo` WHERE `id` = ? ";

		try {
			PreparedStatement pst = dbc.getConnection().prepareStatement(sql);
			pst.setInt(1, ID);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String remarks = rs.getString(3);
				vo = new userDemo(id, name, remarks);
				rs.close();
				pst.close();
			}
		} catch (SQLException e) {
			System.out.println(" 查询错误");
		}
		return vo;
	}

	public userDemo getSongList(char StringName) throws Exception {
		List<userDemo> song = new ArrayList<userDemo>();
		String xString = String.valueOf(StringName);
		String sql = "SELECT id,songName,url FROM songinfor where songName LIKE '" + xString + "%'";
		// String sql = "SELECT songName,url FROM songinfor where songName LIKE
		// ?'%' ";
		PreparedStatement pst = dbc.getConnection().prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		if (!rs.next()) {
			sql = "SELECT id,songName,url FROM songinfor where songName LIKE '%" + xString + "%'";
			System.out.println(sql);
			pst = dbc.getConnection().prepareStatement(sql);
			rs = pst.executeQuery();
		}

		while (rs.next()) {
			int id = rs.getInt(1);
			String songName = rs.getString(2);
			String uilID = rs.getString(3);
			userDemo vo = new userDemo();
			song.add(vo);
		}
		rs.close();
		pst.close();
		int index = (int) (Math.random() * song.size());
		userDemo sInfor = song.get(index);
		return sInfor;
	}
}
