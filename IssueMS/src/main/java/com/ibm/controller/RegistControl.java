package com.ibm.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.service.UserDaoSevice;

@RestController
public class RegistControl {

	@Autowired
	private UserDaoSevice userDaoSevice;

	@RequestMapping(value = "/regist", method = RequestMethod.GET)
	public List regist(/* @RequestBody User user */) throws SQLException, IOException {

//		String username = user.getUserName();
//		String password = user.getPassword();
//		String pwd = user.getPassword();
//		String email = user.getEmail();
//		int userid = user.getUserId();
//		String status = user.getStatus();

		List resultString = userDaoSevice.findByName("8");
		if (resultString.isEmpty()) {
			System.out.println("用户不存在");
			return null;
		} else {
			return resultString;
		}

//		return "1";
//		if (!password.equals(pwd)) {
//			System.out.println("密码不一致");
//			return "1";
//		} else {
//			User user2 = new User();
//			user2.setEmail(email);
//			user2.setUserName(username);
//			user2.setPassword(password);
//			user2.setCreateDate(new Date());
//			user2.setUserId(userid);
//			user2.setStatus(status);
//			userDaoSevice.insert(user2);
//			return "2";
//		}

	}

}
