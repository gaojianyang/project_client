package com.example.pojo;

import java.io.Serializable;


public class User implements Serializable{
	private int id;
	private int uid;
	private long guanzhucount;
	private String name;
	private String gender;
	private Integer head;
	private String college;
	public User() {
		super();
	}
	private String level;
	private String personal;
	private int userid;
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}

	public User(int uid, String name,String gender, Integer head,
			String college, String personal,String level,long guanzhucount,int userid) {
		super();
		this.uid=uid;
		this.name = name;
		this.guanzhucount=guanzhucount;
		this.gender = gender;
		this.head = head;
		this.college = college;
		this.level=level;
		this.personal = personal;
		this.userid=userid;
	}
	public User(int uid, String name,String gender, Integer head,
			String college, String personal,String level,int userid) {
		super();
		this.name = name;
	   this.uid=uid;
		this.gender = gender;
		this.head = head;
		this.college = college;
		this.level=level;
		this.personal = personal;
		this.userid=userid;
	}
	public User(String name,String gender, Integer head,
			String college, String personal) {
		super();
		this.name = name;
	
		this.gender = gender;
		this.head = head;
		this.college = college;
		this.personal = personal;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Integer getHead() {
		return head;
	}
	public void setHead(Integer head) {
		this.head = head;
	}
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	public String getPersonal() {
		return personal;
	}
	public void setPersonal(String personal) {
		this.personal = personal;
	}
	public long getGuanzhucount() {
		return guanzhucount;
	}
	public void setGuanzhucount(long guanzhucount) {
		this.guanzhucount = guanzhucount;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	

}
