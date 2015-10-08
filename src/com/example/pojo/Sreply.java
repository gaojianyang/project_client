package com.example.pojo;

public class Sreply {
	private int id;
	private int uid;
	private int head;
	private String uname;
	private String content;
	private String time;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Sreply(int id, int uid,int head,String uname, String content, String time) {
		super();
		this.id = id;
		this.uid = uid;
		this.uname=uname;
		this.head=head;
		this.content = content;
		this.time = time;
	}
	public int getHead() {
		return head;
	}
	public void setHead(int head) {
		this.head = head;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	
	
	

}
