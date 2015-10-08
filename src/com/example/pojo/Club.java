package com.example.pojo;

import java.io.Serializable;




public class Club implements Serializable{
	
	private Integer id;
	private Integer cid;
	private Integer uid;
	private String name;
	private Integer number;
	public Club() {
		super();
	}
	private String introduce;
	private Integer head;
	private String time;
	private Integer invid;
	private Integer userid;
	
	public Club(Integer cid, Integer uid, String name, Integer number,
			String introduce, Integer head, String time, Integer invid,Integer userid) {
		super();
		this.setCid(cid);
		this.uid = uid;
		this.name = name;
		this.number = number;
		this.introduce = introduce;
		this.head = head;
		this.time = time;
		this.invid = invid;
		this.userid=userid;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public Integer getHead() {
		return head;
	}
	public void setHead(Integer head) {
		this.head = head;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Integer getInvid() {
		return invid;
	}
	public void setInvid(Integer invid) {
		this.invid = invid;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
}
