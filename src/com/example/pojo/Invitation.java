package com.example.pojo;

import net.tsz.afinal.annotation.sqlite.Id;

public class Invitation {

	@Id
	private Integer id;
	private Integer invid;
	private Integer iuid;
	private String title;
	private String uname;
	private Integer head;
	private long zan;
	private String time;
	private String createtime;
	private short type;
	private Integer userid;
	public Invitation(Integer invid,Integer iuid,Integer head,String uname, String title,
			long zan, String time, String createtime, short type,Integer userid) {
		super();
	    this.iuid=iuid;
		this.invid=invid;
		this.head=head;
		this.uname=uname;
		this.title = title;
		this.userid=userid;
		this.zan = zan;
		this.time = time;
		this.createtime = createtime;
		this.type = type;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	public long getZan() {
		return zan;
	}
	public void setZan(long zan) {
		this.zan = zan;
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
	public short getType() {
		return type;
	}
	public void setType(short type) {
		this.type = type;
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
	@Override
	public String toString() {
		return "Invitation [id=" + id + ", title=" + title + ", uname=" + uname
				+ ", head=" + head + ", zan=" + zan + ", time=" + time
				+ ", createtime=" + createtime + ", type=" + type + ", userid="
				+ userid + "]";
	}
	public Invitation() {
		super();
	}
	public Integer getInvid() {
		return invid;
	}
	public void setInvid(Integer invid) {
		this.invid = invid;
	}
	public Integer getIuid() {
		return iuid;
	}
	public void setIuid(Integer iuid) {
		this.iuid = iuid;
	}
	
	
	
	
}
