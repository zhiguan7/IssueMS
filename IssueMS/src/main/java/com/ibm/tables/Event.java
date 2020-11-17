package com.ibm.tables;

import java.util.Date;

public class Event {

	private int eventId;
	private int issueId;
	private Date updateDate;
	private String updateMan;
	private String step;
	private String assignor;
	private String solution;
	private Date plan_date;
	private Date final_date;

	public Event() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Event(int eventId, int issueId, Date updateDate, String updateMain, String step, String assignor,
			String solution, Date plan_date, Date final_date) {
		super();
		this.eventId = eventId;
		this.issueId = issueId;
		this.updateDate = updateDate;
		this.updateMan = updateMain;
		this.step = step;
		this.assignor = assignor;
		this.solution = solution;
		this.plan_date = plan_date;
		this.final_date = final_date;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public int getIssueId() {
		return issueId;
	}

	public void setIssueId(int issueId) {
		this.issueId = issueId;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdateMain() {
		return updateMan;
	}

	public void setUpdateMain(String updateMain) {
		this.updateMan = updateMan;
	}

	public String getStep() {
		return step;
	}

	public void setStep(String step) {
		this.step = step;
	}

	public String getAssignor() {
		return assignor;
	}

	public void setAssignor(String assignor) {
		this.assignor = assignor;
	}

	public String getSolution() {
		return solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}

	public Date getPlan_date() {
		return plan_date;
	}

	public void setPlan_date(Date plan_date) {
		this.plan_date = plan_date;
	}

	public Date getFinal_date() {
		return final_date;
	}

	public void setFinal_date(Date final_date) {
		this.final_date = final_date;
	}

}
