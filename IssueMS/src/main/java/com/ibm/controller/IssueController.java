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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.dao.IssueDao;
import com.ibm.service.IssueDaoService;
import com.ibm.tables.Issue;
import com.ibm.tables.Total_Issue;

@RestController
public class IssueController {
	
	@Autowired
	private IssueDao issueDao;


//	/*
//	 * 分页查询
//	 * 
//	 * pageindex:页码
//	 * pagesize:页大小
//	 * 
//	 * */
//	@PostMapping(value = "/PageIssue")
//	public List<Issue> searchPage(@RequestBody Map<String, Integer> page) throws SQLException, IOException{
//		issueDao = new IssueDaoService();
//		List<Issue> list = null;
//		list = issueDao.searchWithPage(page.get("pageindex"),page.get("pagesize"));
//		return list;
//	}
	
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
	@CrossOrigin
	@RequestMapping(value = "/searchIssue",method = RequestMethod.POST)
	public Total_Issue searchFuzzy(@RequestBody Map<String, String> issue) throws Exception{
		issueDao = new IssueDaoService();
		Issue i = new Issue();
		String c1 = issue.get("createDate").replace("Z", " UTC");
		String c2 = issue.get("date2").replace("Z", " UTC");
		String u1 = issue.get("updateDate").replace("Z", " UTC");
		String u2 = issue.get("date4").replace("Z", " UTC");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
//		Date time = format.parse(dateTime1);
//		System.out.println(dateTime1);
		System.out.println(issue.get("issueId"));
		
//		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		i.setIssueId(Integer.parseInt(issue.get("issueId")));
		i.setStatus(issue.get("status"));
		i.setCreateMan(issue.get("createMan"));
		i.setUpdateMan(issue.get("updateMan"));
//		String c1 = issue.get("createDate"),c2 = issue.get("date2") , u1 = issue.get("updateDate") , u2 = issue.get("date4");
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
		System.out.println(i);
		System.out.println(c1);
		System.out.println(c2);
		System.out.println(u1);
		System.out.println(u2);
		Total_Issue tIssue = null;
		tIssue = issueDao.searchWithFuzzy(i,c3,u3,Integer.parseInt(issue.get("pageIndex")),Integer.parseInt(issue.get("pageSize")));
		return tIssue;
	}
	
	//退回修改
	@CrossOrigin
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
	@CrossOrigin
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
	@CrossOrigin
	@PostMapping(path = "/createIssue")
	public int createIssue(@RequestBody Issue issue) throws SQLException, IOException {
		System.out.println(issue);
		issueDao = new  IssueDaoService();
		int i = issueDao.insert(issue);
		return i;
	}
	
	//修改issue-填写解决方案
	@CrossOrigin
	@PostMapping(path = "/updateSolotion")
	public int updateSolotion(@RequestBody Issue issue) throws SQLException, IOException {
		issueDao = new  IssueDaoService();
		Issue issue2 = new Issue();
		issue2.setIssueId(issue.getIssueId());
		issue2.setSolution(issue.getSolution());
		System.out.println(issue2);
		int i = issueDao.update(issue2);
		return i;
	}
}
