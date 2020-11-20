package com.ibm.tables;

public class Statistics {
	
	private int userId;
	private String userName;
	private int cNum; //创建issue数
	private int rNum; //收到issue数
	private int aNum; //已修改issue数
	private float rate; //完成率
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getcNum() {
		return cNum;
	}
	public void setcNum(int cNum) {
		this.cNum = cNum;
	}
	public int getrNum() {
		return rNum;
	}
	public void setrNum(int rNum) {
		this.rNum = rNum;
	}
	public int getaNum() {
		return aNum;
	}
	public void setaNum(int aNum) {
		this.aNum = aNum;
	}
	public float getRate() {
		return rate;
	}
	public void setRate(float rate2) {
		this.rate = rate2;
	}
}
