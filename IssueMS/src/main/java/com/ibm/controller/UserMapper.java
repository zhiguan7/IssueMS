package com.ibm.controller;

import org.hibernate.annotations.SQLInsert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ibm.tables.User;

public interface UserMapper extends JpaRepository<User, String> {

	@Query("select * from user where username=#{username}")
	public User getUserByUsername(@Param("username") String username);

	@SQLInsert(sql = "insert user2 to user")
	public boolean saveUser(User user2);

}
