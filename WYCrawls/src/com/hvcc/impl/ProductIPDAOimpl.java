package com.hvcc.impl;



import com.hvcc.dbs.DataBaseConnection;
import com.hvcc.vo.MusicList;
import com.hvcc.vo.SongInfor;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;



public class ProductIPDAOimpl{
	DataBaseConnection dbc = null;
	public ProductIPDAOimpl(DataBaseConnection dbc) {
		this.dbc = dbc;
		// TODO Auto-generated constructor stub
	}
	public SongInfor getSongList (char StringName) throws Exception{
			List<SongInfor> song = new ArrayList<SongInfor>();
			String xString = String.valueOf(StringName);
			String sql = "SELECT id,songName,url FROM songinfor where songName LIKE '"+xString+"%'";
		 //	String sql = "SELECT songName,url FROM songinfor where songName LIKE ?'%' ";
			PreparedStatement pst = dbc.getConnection().prepareStatement(sql);
			ResultSet rs = pst.executeQuery();

			if (!rs.next()) {
				sql = "SELECT id,songName,url FROM songinfor where songName LIKE '%"+xString+"%'";
				pst = dbc.getConnection().prepareStatement(sql);
				rs = pst.executeQuery();
				rs.next();
			}
			System.out.println(sql);
			do{
					int id = rs.getInt(1);
					String songName = rs.getString(2);
					String uilID = rs.getString(3);
					SongInfor vo = new SongInfor(id,songName,uilID);
					song.add(vo);
					
			}while(rs.next());
			
			rs.close();
			pst.close();
			int index=(int)(Math.random()*song.size());
			SongInfor sInfor = song.get(index);
		return sInfor; 
	}
}
