package com.example.pojo;

public class Riji {
	
	private Integer id;
	private String riqi;
	private String content;
	private Integer userid;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRiqi() {
		return riqi;
	}
	public void setRiqi(String riqi) {
		this.riqi = riqi;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Riji() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Riji(Integer id, String riqi, String content,Integer userid) {
		super();
		this.id = id;
		this.riqi = riqi;
		this.content = content;
		this.userid=userid;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	

}
