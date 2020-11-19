package com.ibm.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.ibm.tables.User;

public interface UserDao {
	// 插入
	public void insert(User user) throws SQLException, IOException;

	// 通过id删除
	public void delete(User user) throws SQLException, IOException;

	// 更新
	public int update(int userId, String userName, String email, String pwd1, String pwd2)
			throws SQLException, IOException;

	// 查询所有
	public List<User> queryAll() throws SQLException, IOException;

	// 超级Admin注销用户
	public int cancellationUser(int userid) throws SQLException, IOException;

	// 超级Admin修改权限
	public int UpdateAuthority(int userid) throws SQLException, IOException;


	public String login(int userId, String password) throws SQLException, IOException;

	public List findByName(String username);

	public List<User> searchWithFuzzy(int id , String name ) throws SQLException, IOException;

	
}
