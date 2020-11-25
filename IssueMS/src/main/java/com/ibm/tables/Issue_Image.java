package com.ibm.tables;

import java.io.File;

public class Issue_Image {
	private int issueId;
	private File image;

	public File getImage() {
		return image;
	}

	public void setImage(File image) {
		this.image = image;
	}

	public int getIssueId() {
		return issueId;
	}

	public void setIssueId(int issueId) {
		this.issueId = issueId;
	}

	public Issue_Image() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Issue_Image(File image,int issueId) {
		super();
		this.image = image;
		this.issueId = issueId;
	}
	
	
}
