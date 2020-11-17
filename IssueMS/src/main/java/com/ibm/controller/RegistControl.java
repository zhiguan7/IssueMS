package com.ibm.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ibm.tables.User;

public class RegistControl {

	@Autowired
	private UserMapper userMapper;

	@RequestMapping(value = "/regist", method = { RequestMethod.POST, RequestMethod.GET })
	@CrossOrigin
	// @ResponseBody
	public Map<String, Object> regist(@RequestParam String username, @RequestParam int userId,
			@RequestParam String email, @RequestParam String password) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
			map.put("msg", "账户密码不能为空");
			return map;
		}
		// 验证用户名
		User user = userMapper.getUserByUsername(username);
		if (user != null) {
			map.put("msg", "该用户已经注册");
			return map;
		}
		User user2 = new User();
		user2.setUserId(userId);
		user2.setEmail(email);
		user2.setUserName(username);
		user2.setPassword(password);

		boolean count = userMapper.saveUser(user2);
		if (count == false) {
			map.put("msg", "失败");
			return map;
		} else {
			map.put("msg", "成功");
		}

//		System.out.println("name:" + user2.getUserName());
//		System.out.println("password:" + user2.getPassword());
//		map.put("msg", "注册成功");
		return map;

	}
}
