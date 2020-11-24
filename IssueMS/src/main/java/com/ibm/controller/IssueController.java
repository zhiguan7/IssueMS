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
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		i.setIssueId(Integer.parseInt(issue.get("issueId")));
		i.setStatus(issue.get("status"));
		i.setCreateMan(issue.get("createMan"));
		i.setUpdateMan(issue.get("updateMan"));
		String c1 = issue.get("createDate"),c2 = issue.get("date2") , u1 = issue.get("updateDate"), u2 = issue.get("date4");
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
		Total_Issue tIssue = null;
		tIssue = issueDao.searchWithFuzzy(i,issue.get("userId"),c3,u3,Integer.parseInt(issue.get("pageIndex")),Integer.parseInt(issue.get("pageSize")));
		return tIssue;
}
	
	//全局搜索
	@CrossOrigin
	@RequestMapping(value = "/search",method = RequestMethod.POST)
	public List<Issue> searchGlobal(@RequestBody Map<String, String> receive){
		List<Issue> list = null;
		try {
			if(receive.get("send").equals("")||receive.get("send").trim().isEmpty()) {
				return null;
			}else {
				list = issueDao.searchWithGlobal(receive.get("send").trim());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
		return list;
}
	
	//退回修改
	@CrossOrigin
	@RequestMapping(value = "/back",method = RequestMethod.POST)
	public boolean Back(@RequestBody Map<String, Integer> i) throws SQLException, IOException{
		issueDao = new IssueDaoService();
		Issue issue = new Issue();
		issue.setIssueId(i.get("issueId"));
		boolean flag = false;
		flag = issueDao.backChange(issue);
		return flag;
	}
	
	//关闭issue
	@CrossOrigin
	@RequestMapping(value = "/finish",method = RequestMethod.POST)
	public boolean finish(@RequestBody Map<String, Integer> i) throws SQLException, IOException{
		issueDao = new IssueDaoService();
		Issue issue = new Issue();
		issue.setIssueId(i.get("issueId"));
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
