package com.ibm.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.service.UserDaoSevice;
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
	
	@RequestMapping(value = "/updateUser",method = RequestMethod.GET)
	public int update() throws SQLException, IOException{
		int i = UserDaoService.update(6, "李四", "123456@qq.com", "12345", "12345");
		return i;
	}
	
	@RequestMapping(value = "/cancellationUser",method = RequestMethod.GET)
	public int cancellationUser() throws SQLException, IOException{
		int i = UserDaoService.cancellationUser(6);
		return i;
	}
	
	@RequestMapping(value = "/UpdateAuthority",method = RequestMethod.GET)
	public int UpdateAuthority() throws SQLException, IOException{
		int i = UserDaoService.UpdateAuthority(3);
		return i;
	}
}

