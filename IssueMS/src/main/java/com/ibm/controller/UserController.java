package com.ibm.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.dao.UserDao;
import com.ibm.service.UserDaoSevice;
import com.ibm.tables.Issue;
import com.ibm.tables.User;

@RestController
public class UserController {
	
	@Autowired
	private UserDaoSevice UserDaoService;
	
	@RequestMapping(value = "/queryAll",method = RequestMethod.GET)
	public List<User> queryAll()  throws SQLException, IOException{
		List<User> list = UserDaoService.queryAll();
		return list;
	}
	
	
	@PostMapping("/updateUser")
	public int update(@RequestBody Map<String, String> page) throws SQLException, IOException{
		int i = UserDaoService.update(Integer.parseInt(page.get("userId")), page.get("userName")
				, page.get("email"), page.get("pwd1"), page.get("pwd2"));
		return i;
	}
	
	@PostMapping("/cancellationUser")
	public int cancellationUser(@RequestBody Map<String, Integer> page) throws SQLException, IOException{
		int i = UserDaoService.cancellationUser(page.get("userId"));
		return i;
	}
	
	@PostMapping("/UpdateAuthority")
	public int UpdateAuthority(@RequestBody Map<String, Integer> page) throws SQLException, IOException{
		int i = UserDaoService.UpdateAuthority(page.get("userId"));
		return i;
	}
	
	@PostMapping("/UsearchWithPage")
	public List<User> UsearchWithPage(@RequestBody Map<String, Integer> page) throws SQLException, IOException{
		UserDaoService = new UserDaoSevice();
		List<User> list = null;
		list = UserDaoService.UsearchWithPage(page.get("pageIndex"),page.get("pageSize"));
		return list;
	}
	
	@PostMapping(value = "/searchUser")
	public List<User> search(@RequestBody Map<String, String> user) throws SQLException, IOException{
		List<User> list = null;		
		list = UserDaoService.searchWithFuzzy(Integer.parseInt(user.get("userId")),user.get("userName"));
		return list;
	}
}

