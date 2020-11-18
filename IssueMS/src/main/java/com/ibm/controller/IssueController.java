package com.ibm.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.ibm.dao.IssueDao;
import com.ibm.tables.Issue;

@RestController
public class IssueController {
	
	@Autowired
	private IssueDao issueDao;

	@RequestMapping(value = "/hello",method = RequestMethod.GET)
	public List<Issue> helloIssue() throws SQLException, IOException{
		List<Issue> list = issueDao.queryAll();
//		Issue i = new Issue();
//		i.setCreateMan("三");
//		List<Issue> list = issueDao.searchWithFuzzy(i);
//		List<Issue> list = issueDao.searchWithPage(1);
//		return list;
		return list;
	}

}
