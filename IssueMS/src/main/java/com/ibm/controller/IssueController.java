package com.ibm.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	//分页
	@RequestMapping(value = "/Page",method = RequestMethod.GET)
	public List<Issue> searchPage(/* 前端传参  */ ) throws SQLException, IOException{
		issueDao = new IssueDaoService();
		List<Issue> list = null;
		list = issueDao.searchWithPage(1,20);
		return list;
	}
	
	//模糊查询
	@RequestMapping(value = "/searchIssue",method = RequestMethod.GET)
	public List<Issue> searchFuzzy(/* 前端传参  */) throws SQLException, IOException{
		issueDao = new IssueDaoService();
		Issue issue = new Issue();
//		issue.setCreateMan("三");
//		issue.setUpdateMan("七");
//		issue.setIssueId(1);
		List<Issue> list = null;
//		list = issueDao.searchWithFuzzy(issue,null,null);
		return list;
	}
	
	//退回修改
	@RequestMapping(value = "/back",method = RequestMethod.GET)
	public boolean Back() throws SQLException, IOException{
		issueDao = new IssueDaoService();
		Issue issue = new Issue();
		issue.setIssueId(1);
		boolean flag = false;
		flag = issueDao.backChange(issue);
		return flag;
	}
	
	//关闭issue
	@RequestMapping(value = "/finish",method = RequestMethod.GET)
	public boolean finish() throws SQLException, IOException{
		issueDao = new IssueDaoService();
		Issue issue = new Issue();
		issue.setIssueId(1);
		boolean flag = false;
		flag = issueDao.closeChange(issue);
		return flag;
	}
	
	//创建issue
	@PostMapping(path = "/createIssue")
	public String createIssue(@RequestBody Issue issue) throws SQLException, IOException {
		issueDao = new  IssueDaoService();
		issueDao.insert(issue);
		return "创建issue成功";
	}
	
}
