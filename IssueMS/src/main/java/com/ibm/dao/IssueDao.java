package com.ibm.dao;

import java.io.IOException;
import java.sql.SQLException;
import com.ibm.tables.Issue;

public interface IssueDao {
	// 插入
	public void insert(Issue issue) throws SQLException, IOException;

	// 查询
	public void ueryAll() throws SQLException, IOException;

	// 通过id删除
	public void delete(Issue issue) throws SQLException, IOException;

	// 更新
	public void update(Issue issue) throws SQLException, IOException;
	
	//模糊查询
    public void fuzzySearch(Issue issue) throws SQLException, IOException;
}
