package com.hvcc.vo;

public class SongInfor {
	private int id;
	private String songName;
	private String url;
	private String writer;
	private String classe;
	private String intro;
	private int commentNumber;
	
	
	public SongInfor() {
		super();
	}
	public SongInfor(int id, String songName, String url) {
		super();
		this.id = id;
		this.songName = songName;
		this.url = url;
	}
	public SongInfor(int id, String url, String songName,String intro,String classe) {
		super();
		this.id = id;
		this.url = url;
		this.songName = songName;
		this.intro = intro;
		this.classe = classe;
	}
	
	public int getCommentNumber() {
		return commentNumber;
	}
	public void setCommentNumber(int commentNumber) {
		this.commentNumber = commentNumber;
	}
	public void setClasse(String classe) {
		this.classe = classe;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSongName() {
		return songName;
	}
	public void setSongName(String songName) {
		this.songName = songName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getClasse() {
		return classe;
	}
	@Override
	public String toString() {
		return "SongInfor [id=" + id + ", songName=" + songName + ", url=" + url + ", writer=" + writer + ", classe="
				+ classe + ", intro=" + intro + ", commentNumber=" + commentNumber + "]";
	}
	
}
