package com.ibm.test;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ibm.dao.IssueDao;
import com.ibm.dao.IssueDaoimpl;
@Controller
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableJpaRepositories
public class SpringbootApplication {
	
	@RequestMapping(value = "/hello",method = RequestMethod.GET)
	@ResponseBody
	public String helloIssue() throws SQLException, IOException{
		IssueDao issueDao = new IssueDaoimpl();
		issueDao.ueryAll();
		return "hello";
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}

}
