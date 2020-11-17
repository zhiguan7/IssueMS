package com.ibm.test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ibm.dao.IssueDao;
import com.ibm.dao.IssueDaoimpl;
import com.ibm.tables.Issue;

@Controller
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableJpaRepositories
public class SpringbootApplication {
	
	
	@RequestMapping(value = "/hello",method = RequestMethod.GET)
	@ResponseBody
	public List<Issue> helloIssue() throws SQLException, IOException{
		IssueDao issueDao = new IssueDaoimpl();
//		issueDao.ueryAll();
//		Issue i = new Issue();
//		i.setCreateMan("三");
//		List<Issue> list = issueDao.searchWithFuzzy(i);
//		List<Issue> list = issueDao.searchWithPage(1);
//		return list;
		return null;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}

}
