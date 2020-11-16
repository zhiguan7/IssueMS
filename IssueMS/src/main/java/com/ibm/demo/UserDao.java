package com.ibm.demo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.ibm.pojo.User;

public interface UserDao {
	// ���
	public void insert(User user) throws SQLException, IOException;

	// ��ѯ
	public List<User> ueryAll() throws SQLException, IOException;

	// ɾ��
	public void delete(User user) throws SQLException, IOException;

	// ����
	public void update(User user) throws SQLException, IOException;
}
