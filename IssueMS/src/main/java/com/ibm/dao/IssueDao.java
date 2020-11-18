package com.ibm.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.ibm.tables.Issue;

public interface IssueDao {
	// 插入
	public void insert(Issue issue) throws SQLException, IOException;

	// 查询
	public List<Issue> queryAll() throws SQLException, IOException;

	// 通过id删除
	public void delete(Issue issue) throws SQLException, IOException;

	// 更新
	public void update(Issue issue) throws SQLException, IOException;
	
	//模糊查询
    public List<Issue> searchWithFuzzy(Issue issue) throws SQLException, IOException;
    
    //分页查询
    public List<Issue> searchWithPage(int pageIndex) throws SQLException, IOException;
}
