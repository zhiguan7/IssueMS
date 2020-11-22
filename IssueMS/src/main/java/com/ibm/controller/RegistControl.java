package com.ibm.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.service.UserDaoSevice;
import com.ibm.tables.User;

@RestController
public class RegistControl {

	@Autowired
	private UserDaoSevice userDaoSevice;
	@CrossOrigin
	@RequestMapping(value = "/regist", method = { RequestMethod.GET, RequestMethod.POST })
	public String regist(@RequestBody User user /*
												 * @RequestParam String username, @RequestParam String password,
												 * 
												 * @RequestParam String pwd, @RequestParam String email, @RequestParam
												 * int userid
												 */) throws SQLException, IOException {
		System.out.println(user);

		String username = user.getUserName();
		String password = user.getPassword();
		String email = user.getEmail();
		String userid = user.getUserId();
//		String status = user.getStatus();

		List resultString = userDaoSevice.findByName(username);
		if (!resultString.isEmpty()) {
			System.out.println("用户已存在");
			return "0";
		}
//		else {
//			return "0";
//		}
//		if (!password.equals(pwd)) {
//			System.out.println("密码错误");
//			return "1";
//		} 

		User user2 = new User();
		user2.setEmail(email);
		user2.setUserName(username);
		user2.setPassword(password);
		user2.setCreateDate(new Date());
		user2.setUserId(userid);
		user2.setStatus("激活");
		user2.setIdentity("普通用户");
		user2.toString();
		try {
			userDaoSevice.saveUser(user2);
		}catch (Exception e) {
			// TODO: handle exception
			return "0";
		}
		
		System.out.println(user2);
		return "1";

	}

}
