package com.ibm.dao;

import com.ibm.tables.User;

public interface IUserDao {

	public User checkUser(String username, String password);

	public User findUser(String username);
}
