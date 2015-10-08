package com.example.pojo;

import java.io.Serializable;


public class Message implements Serializable{
	private Integer id;
	private String content;
	private Integer fromid;
	private Integer fromhead;
	private Integer type;
	private Integer invid;
	private String time;
	private String fromname;
	private Integer isread;
	private Integer userid;
	public Message(String content, Integer fromid, Integer fromhead,
			Integer type, Integer invid, String time, String fromname,Integer isread,Integer userid) {
		super();
		this.content = content;
		this.fromid = fromid;
		this.fromhead = fromhead;
		this.type = type;
		this.invid = invid;
		this.time = time;
		this.isread=isread;
		this.fromname = fromname;
		this.userid=userid;
	}
	public Message() {
		super();
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
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getInvid() {
		return invid;
	}
	public void setInvid(Integer invid) {
		this.invid = invid;
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
	public Message(Integer id, String content, Integer fromid,
			Integer fromhead, Integer type, Integer invid, String time,
			String fromname,Integer isread,Integer userid) {
		super();
		this.id = id;
		this.content = content;
		this.fromid = fromid;
		this.fromhead = fromhead;
		this.type = type;
		this.invid = invid;
		this.time = time;
		this.isread=isread;
		this.fromname = fromname;
		this.userid=userid;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public Integer getIsread() {
		return isread;
	}
	public void setIsread(Integer isread) {
		this.isread = isread;
	}

}
