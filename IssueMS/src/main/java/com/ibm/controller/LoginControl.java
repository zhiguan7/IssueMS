package com.ibm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ibm.tables.User;

public class LoginControl {
	@Autowired
	private UserMapper userMapper;

	@RequestMapping(value = "/login", method = { RequestMethod.POST, RequestMethod.GET })
//	@ResponseBody
	public String login(HttpSession session, HttpServletRequest request) {
		// 获取请求数据
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		session.setAttribute("username", username);
		session.setAttribute("password", password);
		// 验证用户名是否存在
		User user = userMapper.getUserByUsername(username);
		if (user == null) {
			System.out.println("该用户未注册");
			return "redirect:/login";
		}
		if (!user.getPassword().equals(password)) {
			System.out.println("密码错误");
			return "redirect:/login";
		} else {
			System.out.println("登陆成功");
			return "redirect:/index";
		}
	}

	@RequestMapping(value = "/index", method = { RequestMethod.POST, RequestMethod.GET })
	public String index() {
		return "/index";
	}

}
