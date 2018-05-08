package com.hvcc.vo;

public class MusicList {
	private int id;
	private String uil;
	private String intro;
	public MusicList() {
		super();
	}
	public MusicList(int id, String uil, String intro) {
		super();
		this.id = id;
		this.uil = uil;
		this.intro = intro;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUil() {
		return uil;
	}
	public void setUil(String uil) {
		this.uil = uil;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	@Override
	public String toString() {
		return "MusicList [id=" + id + ", uil=" + uil + ", intro=" + intro + "]";
	}
	
}
