package com.ibm.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ibm.tables.Issue;
import com.ibm.tables.Issue_Image;
import com.ibm.tables.Total_Issue;
import com.ibm.tables.User;

public interface IssueDao {
	// 插入
	public int insert(Issue issue) throws SQLException, IOException;

	// 查询
	public List<Issue> queryAll() throws SQLException, IOException;

	// 通过id删除
	public void delete(Issue issue) throws SQLException, IOException;

	// 更新
	public int update(Issue issue) throws SQLException, IOException;
	
	//模糊查询（按创建时间排序）
    public Total_Issue searchWithFuzzy(Issue issue,String userId,Date createDate2,Date updateDate2,int pageIndex,int pageSize) throws SQLException, IOException;
    
    //模糊查询（按issueId排序）
    public Total_Issue searchWithFuzzy2(Issue issue,String userId,Date createDate2,Date updateDate2,int pageIndex,int pageSize) throws SQLException, IOException;
   
    //分页查询
//    public List<Issue> searchWithPage(int pageIndex,int pageSize) throws SQLException, IOException;

    //退回修改状态
    public boolean backChange(Issue issue) throws SQLException, IOException;
    
    //关闭修改
    public boolean closeChange(Issue issue) throws SQLException, IOException;

    //全局搜索
	public List<Issue> searchWithGlobal(String string)throws Exception;
	
	//上传图片
	public int upLoadIssue_Image(Issue_Image image)throws SQLException;
	
	//获取图片
	public Issue downloadIssue_Image(Issue issue)throws SQLException;
}
