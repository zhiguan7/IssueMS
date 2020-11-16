package com.ibm.demo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.ibm.pojo.User;

public interface UserDao {
	// 添加
	public void insert(User user) throws SQLException, IOException;

	// 查询
	public List<User> ueryAll() throws SQLException, IOException;

	// 删除
	public void delete(User user) throws SQLException, IOException;

	// 更新
	public void update(User user) throws SQLException, IOException;
}
