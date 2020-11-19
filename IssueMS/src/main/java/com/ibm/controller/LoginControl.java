package com.ibm.controller;
//

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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

	@RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String login(@RequestBody User user) throws SQLException, IOException {
		int userid = user.getUserId();
		String password = user.getPassword();
		String reString = userDaoSevice.login(userid, password);
		System.out.println(reString);
		return reString;
	}
//	@Autowired
//	private IUserDao userService;
//
//	@GetMapping
//	public String toLogin() {
//		return "admin/login";
//	}
//
//	@PostMapping("/login")
//	public String login(String username, String password, HttpSession session, RedirectAttributes redirectAttributes) {
//		User user = userService.checkUser(username, password);
//		if (user != null) {
//			session.setAttribute("user", user);
//			return "admin/index";
//		} else {
//			redirectAttributes.addFlashAttribute("err", "密码错误");
//			return "redirect:/admin";
//		}
//	}
//
//	@GetMapping("/logout")
//	public String logout(HttpSession session) {
//		session.removeAttribute("user");
//		return "admin/login";
//	}
//
}
