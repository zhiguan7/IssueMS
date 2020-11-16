package com.ibm.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ibm.demo.UserDao;
import com.ibm.demo.UserDaoimpl;
import com.ibm.pojo.User;

@Controller
@EnableAutoConfiguration
public class MainController {
	
	@RequestMapping(value = "/hello",method = RequestMethod.GET)
	@ResponseBody
	public String sayHello() throws SQLException, IOException{
		
		UserDao testDao = new UserDaoimpl();
		List<User> list = new ArrayList<User>();
		list.addAll(testDao.ueryAll());
		return list.toString();
		
	}
	public static void main(String[] args) {  
        SpringApplication.run(MainController.class, args);  
    }
}
