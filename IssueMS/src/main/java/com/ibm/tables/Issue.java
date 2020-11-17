package com.ibm.tables;

import java.util.Date;

public class Issue {

	private int issueId;
	private String issueName;
	private String status;
	private Date createDate;
	private String createMan;
	private int level;
	private String type;
	private String beta;
	private int userId;

	public Issue(String issueName, String status, Date createDate, String createMan, int level,
			String type, String beta, int userId) {
		super();
		this.issueName = issueName;
		this.status = status;
		this.createDate = createDate;
		this.createMan = createMan;
		this.level = level;
		this.type = type;
		this.beta = beta;
		this.userId = userId;
	}

	public Issue() {
		// TODO Auto-generated constructor stub
	}

	public int getIssueId() {
		return issueId;
	}

	public void setIssueId(int issueId) {
		this.issueId = issueId;
	}

	public String getIssueName() {
		return issueName;
	}

	public void setIssueName(String issueName) {
		this.issueName = issueName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreateMan() {
		return createMan;
	}

	public void setCreateMan(String createMan) {
		this.createMan = createMan;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBeta() {
		return beta;
	}

	public void setBeta(String beta) {
		this.beta = beta;
	}

	@Override
	public String toString() {
		return "Issue [issueId=" + issueId + ", issueName=" + issueName + ", status=" + status + ", createDate="
				+ createDate + ", createMan=" + createMan + ", level=" + level + ", type=" + type + ", beta=" + beta
				+ ", userId=" + userId + "]";
	}

}
