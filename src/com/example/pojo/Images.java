package com.example.pojo;


public class Images {
	private int uid;
	private String name;
	private String title;
	private String uri;
	private String time;
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public Images() {
		super();
	}
	public Images(int uid,String name, String title, String uri,String time) {
		super();
		this.name = name;
		this.title = title;
		this.uri = uri;
		this.uid=uid;
		this.time=time;
	}

}
