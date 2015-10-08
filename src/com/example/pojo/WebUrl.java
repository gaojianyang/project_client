package com.example.pojo;

public class WebUrl {
	private int head;
	private String name;
	private String content;
	public WebUrl(int head, String name, String content) {
		super();
		this.head = head;
		this.name = name;
		this.content = content;
	}
	public int getHead() {
		return head;
	}
	public void setHead(int head) {
		this.head = head;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

}
