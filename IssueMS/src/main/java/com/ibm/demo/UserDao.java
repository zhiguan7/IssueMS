package com.ibm.demo;

import java.io.IOException;
import java.sql.SQLException;

import com.ibm.tables.User;

public interface UserDao {
	// 添加
	static void insert(User user) throws SQLException, IOException {
	}

	// 查询
	static void ueryAll() throws SQLException, IOException {
	}

	// 删除
	static void delete(User user) throws SQLException, IOException {
	}

	// 更新
	static void update(User user) throws SQLException, IOException {
	}
}
