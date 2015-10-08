package com.example.pojo;

public class LoginConfig {

	private int id;
	private int head;
	private String name;
	private String pass;
	private String school;
	private String level;
	private String personal;
	private String gender;
	public LoginConfig( int id,int head, String name, String gender,String pass,
			String school, String personal,String level, boolean isfirst) {
		super();
		this.id = id;
		this.head=head;
		this.level=level;
		this.name = name;
		this.gender=gender;
		this.pass = pass;
		this.school = school;
		this.personal = personal;
		this.isfirst = isfirst;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getPersonal() {
		return personal;
	}
	public void setPersonal(String personal) {
		this.personal = personal;
	}
	private boolean isfirst;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public LoginConfig() {
		super();
	}
	public boolean isIsfirst() {
		return isfirst;
	}
	public void setIsfirst(boolean isfirst) {
		this.isfirst = isfirst;
	}
	public LoginConfig(String url, String name, String pass, boolean isfirst) {
		super();
		this.name = name;
		this.pass = pass;
		this.isfirst = isfirst;
	}
	public int getHead() {
		return head;
	}
	public void setHead(int head) {
		this.head = head;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}

		

	}
