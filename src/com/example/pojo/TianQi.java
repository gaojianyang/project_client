package com.example.pojo;

public class TianQi {
	private String time;
	private String temp;
	private String weather;
	public TianQi() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TianQi(String time, String temp, String weather) {
		super();
		this.time = time;
		this.temp = temp;
		this.weather = weather;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getTemp() {
		return temp;
	}
	public void setTemp(String temp) {
		this.temp = temp;
	}
	public String getWeather() {
		return weather;
	}
	public void setWeather(String weather) {
		this.weather = weather;
	}
}
