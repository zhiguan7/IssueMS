package com.ibm.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.ibm.tables.Total_Statistics;
import com.ibm.tables.Total_User;
import com.ibm.tables.User;

public interface UserDao {
	// 插入
	public void insert(User user) throws SQLException, IOException;

	// 通过id删除
	public void delete(User user) throws SQLException, IOException;

	// 用户修改个人信息
	public int update(int userId, String userName, String email, String pwd1, String pwd2)throws SQLException, IOException;

	// 查询所有
	public Total_User queryAll() throws SQLException, IOException;

	// 超级Admin注销用户
	public int cancellationUser(int userid) throws SQLException, IOException;

	// 超级Admin修改权限
	public int UpdateAuthority(int userid) throws SQLException, IOException;
	
	//用户分页查询
//	public List<User> UsearchWithPage(int pageIndex,int pageSize) throws SQLException, IOException;

	
	public String login(int userId, String password) throws SQLException, IOException;

	
	public List findByName(String username) throws SQLException, IOException;

	
	public void saveUser(User user) throws SQLException, IOException;

	//issue报表的模糊查询
	public Total_Statistics searchWithFuzzy(int id, String name,int pageIndex,int pageSize) throws SQLException, IOException;
	
	//超级Admin用户页面的模糊查询并实现分页
	public Total_User AdminFuzzyquery(int id,String name,int pageIndex,int pageSize) throws SQLException, IOException;
	
}
