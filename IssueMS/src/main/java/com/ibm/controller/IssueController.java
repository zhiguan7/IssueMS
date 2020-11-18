package com.ibm.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.ibm.service.IssueDaoService;
import com.ibm.tables.Issue;

@RestController
public class IssueController {
	
	@Autowired
	private IssueDaoService IssueDaoService;

	@RequestMapping(value = "/searchWithPage",method = RequestMethod.GET)
	public List<Issue> helloIssue() throws SQLException, IOException{
		List<Issue> list = IssueDaoService.searchWithPage(1,20);
		return list;
	}

}
