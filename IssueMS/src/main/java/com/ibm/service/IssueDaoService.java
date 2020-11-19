package com.ibm.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import com.ibm.dao.IssueDao;
import com.ibm.tables.Issue;
import com.ibm.tables.Total_Issue;
import com.ibm.tables.User;
import com.sun.org.apache.bcel.internal.generic.NEW;

@Service
public class IssueDaoService implements IssueDao {

	private final static int PAGE_SIZE = 20;
	
	
	private static SessionFactory factory;
	
	static {
		factory = new Configuration().configure().buildSessionFactory();
	}
	
	public void insert(Issue issue) throws SQLException, IOException {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(issue);
		tx.commit();
		session.close();
	}

	public List<Issue> queryAll() throws SQLException, IOException {
		List<Issue> issues = factory.openSession().createQuery("FROM Issue").list();
		return issues;
	}

	public void delete(Issue issue) throws SQLException, IOException {
		// TODO Auto-generated method stub
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		session.delete(issue);
		tx.commit();
		session.close();
	}

	public void update(Issue issue) throws SQLException, IOException {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		session.update(issue);
		tx.commit();
		session.close();
	}
	
	
	public Total_Issue searchWithFuzzy(Issue issue,Date createDate2,Date updateDate2,int pageIndex,int pageSize) throws SQLException, IOException {

		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		Criteria criteria = session.createCriteria(Issue.class);
				
		if(issue.getIssueId()!=0) {
			criteria.add(Restrictions.and(Restrictions.eq("issueId", issue.getIssueId())));
		}
		if(issue.getStatus()!=null) {
			criteria.add(Restrictions.and(Restrictions.eq("status", issue.getStatus())));
		}
		if(issue.getCreateMan()!=null) {
			criteria.add(Restrictions.and(Restrictions.like("createMan",issue.getCreateMan() ,MatchMode.ANYWHERE)));
		}
		if(issue.getUpdateMan()!=null) {
			criteria.add(Restrictions.and(Restrictions.like("updateMan",issue.getUpdateMan() ,MatchMode.ANYWHERE)));
		}
		if(issue.getCreateDate()!=null&&createDate2!=null) {
			criteria.add(Restrictions.and(Restrictions.between("createDate", issue.getCreateDate(), createDate2)));
		}
		if(issue.getUpdateDate()!=null&&updateDate2!=null) {
			criteria.add(Restrictions.and(Restrictions.between("updateDate", issue.getUpdateDate(), updateDate2)));
		}
		
		List<Issue> list = criteria.list();//总
		Total_Issue tIssue = new Total_Issue();
		int row = list.size();
		tIssue.setTotal(row);
		if(row/pageSize == pageIndex) {
			list = list.subList((pageIndex-1)*pageSize, row);
		}else {
			list = list.subList((pageIndex-1)*pageSize, pageIndex*pageSize);
		}
		tIssue.setIssue(list);
		tx.commit();
		session.close();
		return tIssue;
	}

//	@Override
//	public List<Issue> searchWithPage(int pageIndex,int pageSize) throws SQLException, IOException {
//		// TODO Auto-generated method stub
//		
//		Session session = factory.openSession();
//		Transaction tx = session.beginTransaction();
//		Criteria criteria = session.createCriteria(Issue.class);
//		
//		criteria.setFirstResult((pageIndex-1)*pageSize); //需要修改
//		criteria.setMaxResults(pageSize);
//		List<Issue> list = criteria.list();
//	
//		tx.commit();
//		session.close();
//		return list;
//	}

	@Override
	public boolean backChange(Issue issue) {
		// TODO Auto-generated method stub
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query = session.createQuery("update Issue set status = '待解决'  where issue_id = :id");
			query.setParameter("id", issue.getIssueId());
			query.executeUpdate(); 
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} 
	    session.close();
		return true;
	}

	@Override
	public boolean closeChange(Issue issue){
		// TODO Auto-generated method stub
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query = session.createQuery("update Issue set status = '关闭'  where issue_id = :id");
			query.setParameter("id", issue.getIssueId());
			query.executeUpdate();
			Query query1 = session.createQuery("update Issue set final_date = :time  where issue_id = :id");
			query1.setParameter("id", issue.getIssueId());
			query1.setParameter("time", new Date(System.currentTimeMillis()));
			query1.executeUpdate();
			session.getTransaction().commit(); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	    session.close();
		return true;
	}
}
