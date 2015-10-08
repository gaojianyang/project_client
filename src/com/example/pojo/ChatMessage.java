package com.example.pojo;

import net.tsz.afinal.annotation.sqlite.Id;

public class ChatMessage {
	public ChatMessage() {
		super();
	}
	@Id
	private Integer id;
	private String content;
	private Integer fromid;
	private Integer fromhead;
	private Integer toid;
	private String time;
	private String fromname;
	private Integer userid;
	
	
	public ChatMessage(String content, Integer fromid, Integer fromhead,
			 String time,Integer toid,String fromname,Integer userid) {
		super();
		this.content = content;
		this.fromid = fromid;
		this.fromhead = fromhead;
		this.time = time;
		this.toid=toid;
		this.fromname = fromname;
		this.userid=userid;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getFromid() {
		return fromid;
	}
	public void setFromid(Integer fromid) {
		this.fromid = fromid;
	}
	public Integer getFromhead() {
		return fromhead;
	}
	public void setFromhead(Integer fromhead) {
		this.fromhead = fromhead;
	}
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getFromname() {
		return fromname;
	}
	public void setFromname(String fromname) {
		this.fromname = fromname;
	}
	public Integer getToid() {
		return toid;
	}
	public void setToid(Integer toid) {
		this.toid = toid;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}

}
