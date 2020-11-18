package com.ibm.service;

import com.ibm.tables.User;

public interface IUserService {

	User checkUser(String username, String password);

	User findUser(String username);
}
