package com.ibm.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.dao.IssueDao;
import com.ibm.service.IssueDaoService;
import com.ibm.tables.Issue;

@RestController
public class IssueController {
	
	@Autowired
	private IssueDao issueDao;

	/*
	 * 分页查询
	 * 
	 * pageindex:页码
	 * pagesize:页大小
	 * 
	 * */
	@PostMapping(value = "/PageIssue")
	public List<Issue> searchPage(@RequestBody Map<String, Integer> page) throws SQLException, IOException{
		issueDao = new IssueDaoService();
		List<Issue> list = null;

		list = issueDao.searchWithPage(page.get("pageindex"),page.get("pagesize"));
		return list;
	}
	
	/*
	 * 模糊查询
	 * 
	 * issueid: issue No
	 * status: 状态
	 * createman: 创建人
	 * updateman: 修改人
	    *  创建时间: createdate1 至 createdate2
	    *  修改时间: updatedate1 至 updatedate2
	 *
	 * */
	@RequestMapping(value = "/searchIssue")
	public List<Issue> searchFuzzy(@RequestBody Map<String, String> issue) throws Exception{
		issueDao = new IssueDaoService();
		Issue i = new Issue();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		i.setIssueId(Integer.parseInt(issue.get("issueid")));
		i.setStatus(issue.get("status"));
		i.setCreateMan(issue.get("createman"));
		i.setUpdateMan(issue.get("updateman"));
		String c1 = issue.get("createdate1"),c2 = issue.get("createdate2") , u1 = issue.get("updatedate1") , u2 = issue.get("updatedate2");
		Date c3 = null ,u3 = null;
		if (c1!=null) {
			i.setCreateDate(format.parse(c1));
		}
		if (u1!=null) {
			i.setUpdateDate(format.parse(u1));
		}
		if (c2!=null) {
			c3 = format.parse(c2);
		}
		if (u2!=null) {
			u3 = format.parse(u2);
		}
//		i.setCreateDate(new Date(issue.get("createdate1")));
//		i.setUpdateDate(new Date(issue.get("updatedate1")));
		List<Issue> list = null;
		list = issueDao.searchWithFuzzy(i,c3,u3);
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
