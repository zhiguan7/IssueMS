package com.ibm.tables;

import java.util.Date;

public class Issue {

	private Integer issueId;
	private String issueName;
	private String status;
	private Date createDate;
	private String createMan;
	private int level;
	private String type;
	private String beta;
	private String userId;
	private Date updateDate;
	private String updateMan;
	private String step;
	private String solution;
	private Date planDate;
	private Date finalDate;
	
	public Issue() {
	}
	
	public Integer getIssueId() {
		return issueId;
	}
	public void setIssueId(Integer issueId) {
		this.issueId = issueId;
	}
	
	public String getIssueName() {
		return issueName;
	}

	public void setIssueName(String issueName) {
		this.issueName = issueName;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getUpdateMan() {
		return updateMan;
	}
	public void setUpdateMan(String updateMan) {
		this.updateMan = updateMan;
	}
	public String getStep() {
		return step;
	}
	public void setStep(String step) {
		this.step = step;
	}
	public String getSolution() {
		return solution;
	}
	public void setSolution(String solution) {
		this.solution = solution;
	}
	public Date getPlanDate() {
		return planDate;
	}
	public void setPlanDate(Date planDate) {
		this.planDate = planDate;
	}
	public Date getFinalDate() {
		return finalDate;
	}
	public void setFinalDate(Date finalDate) {
		this.finalDate = finalDate;
	}
	public Issue(Integer issueId, String issueName, String status, Date createDate, String createMan, int level,
			String type, String beta, String userId, Date updateDate, String updateMan, String step, String solution,
			Date planDate, Date finalDate) {
		super();
		this.issueId = issueId;
		this.issueName = issueName;
		this.status = status;
		this.createDate = createDate;
		this.createMan = createMan;
		this.level = level;
		this.type = type;
		this.beta = beta;
		this.userId = userId;
		this.updateDate = updateDate;
		this.updateMan = updateMan;
		this.step = step;
		this.solution = solution;
		this.planDate = planDate;
		this.finalDate = finalDate;
	}
	@Override
	public String toString() {
		return "Issue [issueId=" + issueId + ", issueName=" + issueName + ", status=" + status + ", createDate="
				+ createDate + ", createMan=" + createMan + ", level=" + level + ", type=" + type + ", beta=" + beta
				+ ", userId=" + userId + ", updateDate=" + updateDate + ", updateMan=" + updateMan + ", step=" + step
				+ ", solution=" + solution + ", plan_date=" + planDate + ", final_date=" + finalDate + "]";
	}
	
}
