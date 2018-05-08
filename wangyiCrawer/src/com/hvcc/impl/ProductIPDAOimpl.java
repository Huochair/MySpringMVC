package com.hvcc.impl;



import com.hvcc.MR.reader88;
import com.hvcc.dbs.DataBaseConnection;
import com.hvcc.vo.MusicList;
import com.hvcc.vo.SongInfor;
import com.hvcc.vo.infor;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Pattern;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class ProductIPDAOimpl{
	DataBaseConnection dbc = null;
	public ProductIPDAOimpl(DataBaseConnection dbc) {
		this.dbc = dbc;
		// TODO Auto-generated constructor stub
	}
	//添加
	public void setUrl(MusicList vo) throws Exception {
		try {
			dbc.getConnection().setAutoCommit(false);
			String sql = "SELECT `id` FROM `musicplaylist` WHERE `uil` = ? AND `intro` = ?";
			PreparedStatement pst = dbc.getConnection().prepareStatement(sql);
			//SELECT `id` FROM `musicplaylist` WHERE `uil` = 'http://music.163.com/#/playlist?id=2181606783' AND `intro` = '一想到你啊，我就觉得世界如此美妙';
			pst.setString(1,vo.getUil());
			pst.setString(2,vo.getIntro());
			ResultSet rs = pst.executeQuery();
			if(!rs.next()){
				sql = "INSERT INTO musicplaylist(uil,intro,status) VALUES(?,?,?)";
				pst = dbc.getConnection().prepareStatement(sql);
				pst.setString(1,vo.getUil());
				pst.setString(2,vo.getIntro());
				pst.setInt(3,0);
				pst.executeUpdate();
			}
			rs.close();
			pst.close();
			dbc.getConnection().commit();
		}  catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			dbc.getConnection().rollback();
		}
	}
	public void setMusic(SongInfor vo) throws Exception {
		try {
			dbc.getConnection().setAutoCommit(false);
			String sql = "SELECT `id` FROM `songinfor` WHERE id = ?";
			PreparedStatement pst = dbc.getConnection().prepareStatement(sql);
			//SELECT `id` FROM `musicplaylist` WHERE `uil` = 'http://music.163.com/#/playlist?id=2181606783' AND `intro` = '一想到你啊，我就觉得世界如此美妙';
			pst.setInt(1,vo.getId());
			ResultSet rs = pst.executeQuery();
			if(!rs.next()){
				sql = "INSERT INTO songinfor(id,songName,url,intro,classes) VALUES(?,?,?,?,?)";
				pst = dbc.getConnection().prepareStatement(sql);
				pst.setInt(1, vo.getId());
				pst.setString(2,vo.getSongName());
				pst.setString(3,vo.getUrl());
				pst.setString(4, vo.getIntro());
				pst.setString(5, vo.getClasse());
				pst.executeUpdate();
			}
			rs.close();
			pst.close();
			dbc.getConnection().commit();
		}  catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			dbc.getConnection().rollback();
		}
	}
	
	public MusicList getUrl() throws SQLException {
		MusicList vo = null;
		try {
			//设置统一的事务处理
			dbc.getConnection().setAutoCommit(false);
			String sql = "SELECT id,uil,intro FROM musicplaylist WHERE status = 0 ORDER BY id LIMIT 1";
			PreparedStatement pst = dbc.getConnection().prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				int id = rs.getInt(1);
				String uil = rs.getString(2);
				String intro = rs.getString(3);
				vo = new MusicList(id,uil,intro);
				System.out.println(id);
				//修改这条数据的状态：变为1
				sql = "UPDATE musicplaylist SET status = 1 WHERE id = ?";
				pst = dbc.getConnection().prepareStatement(sql);
				pst.setInt(1, id);
				pst.executeUpdate();
			}
			rs.close();
			pst.close();
			dbc.getConnection().commit();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			dbc.getConnection().rollback();
		}
		return vo;
	}
	
	
	public SongInfor getMusicURL() throws Exception {
			SongInfor vo = null;
			try {
				dbc.getConnection().setAutoCommit(false);
				String sql = "SELECT id,url,songName,intro,classes FROM songinfor WHERE status = 0 ORDER BY id LIMIT 1";
				PreparedStatement pst = dbc.getConnection().prepareStatement(sql);
				ResultSet rs = pst.executeQuery();
				if (rs.next()) {
					int id = rs.getInt(1);
					String songName = rs.getString(2);
					String url = rs.getString(3);
					String intro = rs.getString(4);
					String classe = rs.getString(5);
					vo = new SongInfor(id,songName,url,intro,classe);
					sql = "UPDATE songinfor SET status = 1 WHERE id = ?";
					pst = dbc.getConnection().prepareStatement(sql);
					pst.setInt(1, id);
					pst.executeUpdate();
				}
				rs.close();
				pst.close();
				dbc.getConnection().commit();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return vo;
	}
	
	public void getMusicURLTo(SongInfor vo) throws Exception {
		try {
			dbc.getConnection().setAutoCommit(false);
			
				String sql = "DELETE FROM songinfor WHERE id = ?";
				PreparedStatement pst = dbc.getConnection().prepareStatement(sql);
				pst.setInt(1, vo.getId());
				pst.executeUpdate();
			
			pst.close();
			dbc.getConnection().commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
	
	public void setComment(SongInfor vo) throws Exception{
		try {
			dbc.getConnection().setAutoCommit(false);
			String sql = "UPDATE songinfor SET `comment_number` = ?,`writer` = ? WHERE id = ?";
			PreparedStatement pst = dbc.getConnection().prepareStatement(sql);
			pst = dbc.getConnection().prepareStatement(sql);
			pst.setInt(1, vo.getCommentNumber());
			pst.setString(2, vo.getWriter());
			pst.setInt(3, vo.getId());
			pst.executeUpdate();
			pst.close();
			dbc.getConnection().commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void adddata() throws Exception {
		String  sql = "INSERT INTO fenci (classes,sum_number) VALUES (?,?)";
		PreparedStatement pst = dbc.getConnection().prepareStatement(sql);
		pst.setString(1,reader88.classes );
		pst.setInt(2, reader88.sum);
		pst.executeUpdate();
	}
		

		public List<infor> findAll() throws Exception {
			String sql = "SELECT classes,comment_number FROM songinfor";
			PreparedStatement pst = dbc.getConnection().prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			List<infor> all = new ArrayList<infor>();
			while (rs.next()) {
				String classes =rs.getString(1);
				int  commentnumber= rs.getInt(2);
				infor vo = new infor(classes,commentnumber);
				all.add(vo);
			}
			rs.close();
			pst.close();
			return all;
		}
}
