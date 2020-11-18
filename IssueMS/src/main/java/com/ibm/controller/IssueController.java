package com.ibm.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.dao.IssueDao;
import com.ibm.service.IssueDaoService;
import com.ibm.tables.Issue;

@RestController
public class IssueController {
	
	@Autowired
	private IssueDao issueDao;

	@RequestMapping(value = "/Page",method = RequestMethod.GET)
	public List<Issue> searchPage() throws SQLException, IOException{
		issueDao = new IssueDaoService();
		List<Issue> list = null;
		list = issueDao.searchWithPage(1,20);
		return list;
	}
	
	@RequestMapping(value = "/search",method = RequestMethod.GET)
	public List<Issue> searchFuzzy() throws SQLException, IOException{
		issueDao = new IssueDaoService();
		Issue issue = new Issue();
//		issue.setCreateMan("三");
//		issue.setUpdateMan("七");
		issue.setIssueId(1);
		List<Issue> list = null;
		list = issueDao.searchWithFuzzy(issue,null,null);
		return list;
	}
}
