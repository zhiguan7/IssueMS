package ibm.com.Tables;

import java.util.Date;

public class Event {

	private int event_id;
	private int issue_id;
	private int user_id;
	Date update_date;
	String update_main;
	String step;
	String assignor;
	String solution;
	public int getEvent_id() {
		return event_id;
	}
	public void setEvent_id(int event_id) {
		this.event_id = event_id;
	}
	public int getIssue_id() {
		return issue_id;
	}
	public void setIssue_id(int issue_id) {
		this.issue_id = issue_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public Date getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}
	public String getUpdate_main() {
		return update_main;
	}
	public void setUpdate_main(String update_main) {
		this.update_main = update_main;
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
	Date plan_date;
	Date final_date;
}
