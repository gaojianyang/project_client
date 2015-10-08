package com.example.pojo;

public class Sport {
	
	
	  public Sport() {
		super();
	}
	private Integer id;
	private Integer spoid;
	  private Integer head;
	     private Integer uid;
	     private String title;
	     private String uname;
	     private String content;
	     private String time;
	 	private String createtime;
	     private Integer needperson;
	     private Integer userid;
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
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
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
		public String getCreatetime() {
			return createtime;
		}
		public void setCreatetime(String createtime) {
			this.createtime = createtime;
		}
		public Integer getNeedperson() {
			return needperson;
		}
		public void setNeedperson(Integer needperson) {
			this.needperson = needperson;
		}
		public Sport(Integer spoid,Integer head, Integer uid,String uname, String title, String content,
				String time, String createtime, Integer needperson,Integer userid) {
			super();
			this.spoid = spoid;
			this.head=head;
			this.uid = uid;
			this.uname=uname;
			this.title = title;
			this.content = content;
			this.time = time;
			this.createtime = createtime;
			this.needperson = needperson;
			this.userid=userid;
		}
		public Integer getHead() {
			return head;
		}
		public void setHead(Integer head) {
			this.head = head;
		}
		public String getUname() {
			return uname;
		}
		public void setUname(String uname) {
			this.uname = uname;
		}
		public Integer getUserid() {
			return userid;
		}
		public void setUserid(Integer userid) {
			this.userid = userid;
		}
		public Integer getSpoid() {
			return spoid;
		}
		public void setSpoid(Integer spoid) {
			this.spoid = spoid;
		}
		
		
		
	    
}
