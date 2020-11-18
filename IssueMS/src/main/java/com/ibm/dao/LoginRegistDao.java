package com.ibm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ibm.tables.User;

public interface LoginRegistDao extends JpaRepository<User, Long> {

	@Query(value = "select * from user where username=?1 and password=?2", nativeQuery = true)
	User findByUserNameAndPassword(String username, String password);

	@Query(value = "select * from user where username=?1", nativeQuery = true)
	User findByUserName(String username);

}
