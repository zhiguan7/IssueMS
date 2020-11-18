package com.ibm.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.dao.LoginRegistDao;
import com.ibm.tables.User;

public class UserService implements IUserService {

	@Autowired
	private LoginRegistDao loginRegistDao;

	@Override
	public User checkUser(String username, String password) {
		return loginRegistDao.findByUserNameAndPassword(username, password);
	}

	@Override
	public User findUser(String username) {
		return loginRegistDao.findByUserName(username);
	}
}
