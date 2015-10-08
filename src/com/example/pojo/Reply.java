package com.example.pojo;

public class Reply {
	private int id;
	private int uid;
	private String uname;
	private String content;
	private int zan;
	private String time;
	private int head;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUname() {
		return uname;
	}
	public Reply(int id, String uname,int uid, String content, int zan, String time,
			int head) {
		super();
		this.id = id;
		this.uid=uid;
		this.uname = uname;
		this.content = content;
		this.zan = zan;
		this.time = time;
		this.head = head;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getZan() {
		return zan;
	}
	public void setZan(int zan) {
		this.zan = zan;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getHead() {
		return head;
	}
	public void setHead(int head) {
		this.head = head;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
}
