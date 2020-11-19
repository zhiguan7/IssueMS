package com.ibm.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.ibm.tables.User;

public interface UserDao {
	// 插入
	public void insert(User user) throws SQLException, IOException;

	// 查询所有
	public void ueryAll() throws SQLException, IOException;

	// 通过id删除
	public void delete(User user) throws SQLException, IOException;

	// 更新
	public void update(User user) throws SQLException, IOException;

	public String login(int userId, String password) throws SQLException, IOException;

	public List findByName(String username);
}
