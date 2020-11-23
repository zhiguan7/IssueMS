package com.ibm.controller;
//

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.service.UserDaoSevice;
import com.ibm.tables.User;

@RestController
public class LoginControl {

	@Autowired
	private UserDaoSevice userDaoSevice;

//	@RequestMapping(value = "/toLogin")
//	@ResponseBody
//	public String toLogin() {
//		return "/login";
//	}
	@CrossOrigin
	@RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
//	@ResponseBody
	public User login(@RequestBody User user) throws SQLException, IOException {
//		System.out.println(user);
		String userid = user.getUserId();
		String password = user.getPassword();
		User user1 = userDaoSevice.login(userid, password);
//		System.out.println(user1);
		return user1;
	}

}
